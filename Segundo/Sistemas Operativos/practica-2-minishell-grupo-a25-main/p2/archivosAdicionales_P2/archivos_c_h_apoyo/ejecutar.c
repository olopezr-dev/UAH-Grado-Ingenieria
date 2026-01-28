#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>

#include "parser.h"
#include "ejecutar.h"
#include "libmemoria.h"
#include "redirecciones.h"

int **crear_pipes(int nordenes) {

   int **pipes = NULL;
   int i;

   if (nordenes < 2)
	return NULL; // No se necesitan pipes en una orden simple

   /* Reserva de memoria para tuberias
      Si hay N órdenes, necesitamos N-1 tuberías para conectarlas */
   pipes = (int **)malloc(sizeof(int *) * (nordenes - 1 ));

   for (i = 0; i < (nordenes - 1); i++) {
	/* Creacion del pipe, cada pipe necesita espacio para 2 enteros, 
	pipes[i][0] extremo de lectura y pipes [i][0] de escritura
	La llamada al sistema pipe() rellena dicho espacio*/
	pipes[i] = (int *)malloc(sizeof(int) * 2);

	if (pipe(pipes[i]) < 0) {
	   perror("Error creando pipe");
	   exit(EXIT_FAILURE);
	}
   }
   return pipes;
}

pid_t ejecutar_orden(const char *orden, int entrada, int salida, int *pbackgr) {
   char **args;
   pid_t pid;
   int indice_ent = -1, indice_sal = -1; // Para Fase 6

   // Parsear la orden para separar argumentos y detectar redirecciones
    if ((args = parser_orden(orden, &indice_ent, &indice_sal, pbackgr)) == NULL) {
        return (-1);
    }

    // Controlar si la línea estaba vacía
    if (args[0] == NULL) {
        free_argumentos(args);
        return (-1);
    }

    // FASE 6: PREPARACION DE REDIRECCIONES (Proceso padre)
    int fd_entrada = -1;
    int fd_salida = -1;

    // Tratamos redirección de entrada '<'
    if (indice_ent != -1) {
        redirec_entrada(args, indice_ent, &fd_entrada);

        if (fd_entrada == -1) {
            free_argumentos(args);
            return (-1);
        }
    }

    // Tratamos redirección de salida '>'
    if (indice_sal != -1) {
        redirec_salida(args, indice_sal, &fd_salida);

        if (fd_salida == -1) {
            if (fd_entrada != -1) close(fd_entrada);
            free_argumentos(args);
            return (-1);
        }
    }

    // CREACION DEL PROCESO HIJO
    pid = fork();

    if (pid < 0) {
        perror("Error en fork");

	// Limpieza de emergencia en caso de error
        if (fd_entrada != -1) close(fd_entrada);
        if (fd_salida != -1) close(fd_salida);
        free_argumentos(args);
        return (-1);
    }
    else if (pid == 0) {
        // CONFIGURACIÓN DEL HIJO

	/* Redireccion de tuberias
	Si 'entrada' no es 0 (STDIN), significa que esta orden lee de un
	pipe anterior. Usamos dup2 para duplicar el descriptor del pipe
	en la entrada estándar.*/
	if (entrada != 0) {
	    if (dup2(entrada, STDIN_FILENO) == -1) {
		perror("Error dup2 entrada pipe");
		exit(EXIT_FAILURE);
	    }
	    close(entrada); // Cerramos el original tras duplicarlo
	}

	/* Si 'salida' no es 1 (STDOUT), significa que esta orden escribe
	en un pipe siguiente. Usamos dup2 para duplicar el descriptor del
	pipe en la salida anterior*/
	if (salida != 1) {
	    if (dup2(salida, STDOUT_FILENO) == -1) {
		perror("Error dup2 salida pipe");
		exit(EXIT_FAILURE);
	    }
	    close(salida); // Igual que antes, cerramos el original tras duplicarlo
	}

	// Redirección de ENTRADA
        if (fd_entrada != -1) {
            dup2(fd_entrada, STDIN_FILENO);
            close(fd_entrada);
        }

        // Redirección de SALIDA
        if (fd_salida != -1) {
            dup2(fd_salida, STDOUT_FILENO);
            close(fd_salida);
        }

        // Ejecutar la orden (execvp usará los nuevos STDIN/STDOUT)
        execvp(args[0], args);

        perror("Error al ejecutar el comando");
        exit(EXIT_FAILURE);
    }
    else {
        /* LIMPIEZA EN EL PADRE
	El padre NO usa los archivos, solo los abrió para el hijo*/
        if (fd_entrada != -1) close(fd_entrada);
        if (fd_salida != -1) close(fd_salida);

	/* Cierre de pipes en el padre
	Es fundamental cerrar los descriptores de lectura/escritura de los
	pipes en el padre. Si no se cierran, los hijos nunca detectaran el
	fin de fichero y podrian quedarse bloqueados, esperando datos que
	nunca llegarán.*/
	if (entrada != 0) close(entrada);
	if (salida != 1) close(salida);

	// Liberar memoria de los argumentos
	free_argumentos(args);
	return pid;
    }
}

void ejecutar_linea_ordenes(const char *orden) {
    char **ordenes;
    int nordenes;
    int **pipes = NULL;
    pid_t *pids = NULL;
    int backgr = 0;
    int i;

    // 1. Obtener órdenes separadas por pipes
    ordenes = parser_pipes(orden, &nordenes);

    // 2. Crear tuberías necesarias
    pipes = crear_pipes(nordenes);

    // 3. Reservar memoria para los PIDs de los hijos
    pids = (pid_t *)malloc(sizeof(pid_t) * nordenes);

    /* 4. Bucle de ejecución de la secuencia
	Recorremos todas las órdenes conectando sus entradas y salidas */
    for (i = 0; i < nordenes; i++) {
        int fd_in = 0;  // Entrada estándar por defecto (teclado)
        int fd_out = 1; // Salida estándar por defecto (pantalla)

        /* Si no es la primera orden, la entrada viene del pipe anterior
           (El extremo de LECTURA del pipe i-1) */
        if (i > 0) {
            fd_in = pipes[i-1][0];
        }

        /* Si no es la última orden, la salida va al pipe actual
          (El extremo de ESCRITURA del pipe i) */
        if (i < nordenes - 1) {
            fd_out = pipes[i][1];
        }

        // Ejecutar la orden pasando los descriptores correspondientes
        pids[i] = ejecutar_orden(ordenes[i], fd_in, fd_out, &backgr);
    }

    // 5. Gestión de espera de procesos
    if (!backgr) {
        /* Espera en primer plano
	   El padre debe esperar a que terminen todos los procesos de la tuberia
	   para devolver el control al usuario correctamente */
        for (i = 0; i < nordenes; i++) {
            if (pids[i] > 0) {
                waitpid(pids[i], NULL, 0);
            }
        }
    }
    else {
        // Segundo plano
        for (i = 0; i < nordenes; i++) {
            if (pids[i] > 0) {
                printf("[%d] ", pids[i]);
            }
        }
        printf("\n");
    }

    // 6. Liberar memoria dinámica
    free(pids);

    /*  Liberación de estructuras
	Es necesario liberar la memoria del array del doble de pipes y del array de strings
	para evitar fugas de memoria.*/
    free_ordenes_pipes(ordenes, pipes, nordenes);
}
