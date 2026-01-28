/* Descripción:
   Implementación del bucle principal (FASE 1) de la minishell.
   Este fichero contiene la función main() para iniciar el intérprete.
   Lee los comandos del usuario y los prepara para su ejecución.*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <signal.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>

// Incluimos las cabeceras de nuetsros propios módulos con comillas dobles
#include "internas.h"
#include "entrada_minishell.h"
#include "ejecutar.h"

//Manejador para la señal SIGCHLD. Limpia procesos hijos terminados (zombies).
void manejador_SIGCHLD(int sig){
    /* Usamos waitpid con -1 (cualquier hijo) y WNOHANG (no bloquear)
    en un bucle, para limpiar a TODOS los hijos que hayan terminado,
    ya que varias señales SIGCHLD pueden agruparse en una sola*/
    while (waitpid(-1, NULL, WNOHANG) > 0);
}

int main(int argc, char *argv[]){
    /* Buf es una variable tipo array, se usa para almacenar la linea que el usuario escriba en la terminal.
    BUFSIZ es una constante estándar que define un tamaño de buffer seguro para que sea eficiente*/
    char buf[BUFSIZ];

    /* Este es el bucle principal de ejecución. Se crea un bucle infinito que mantiene el programa en ejecución.
    Se romperá cuando se cumpla la condición de exit*/
    struct sigaction act;

    // Asignamos la función manejadora que acabamos de definir
    act.sa_handler = manejador_SIGCHLD;

    // Inicializamos la máscara de señales (buena práctica)
    sigemptyset(&act.sa_mask);

    // Sin flags especiales.
    act.sa_flags = 0;

    // Instalamos el manejador
    if (sigaction(SIGCHLD, &act, NULL) < 0) {
        perror("Error al instalar el manejador de SIGCHLD");
        exit(1);    // Salimos si no podemos instalar el manejador
    }
    // Fin del bloque para FASE 3

    //Este es el bucle principal de ejecucion. Se crea un bucle infinito que mantiene el programa en ejecución. Se romperá cuando se compla la condición de exit.

   while (1) {

	// Llamamos a la función para mostrar el prompt minishell
   	imprimir_prompt();

	// Leemos la línea de la entrada estándar y la guardamos en la variable buf.
	leer_linea_ordenes(buf);

	// Comprobamos si se escribe exit. Usamos strcmp que devuelve 0 si dos cadenas de texto son idénticas.
	if (strcmp(buf, "exit") == 0) {
		return 0; // Salimos del bucle y terminamos el programa main.
	}

	char *buf_ptr = buf; //Un puntero auxiliar que strsep() modificará
	char *comando;      // Aquí guardaremos cada trozo (ej: "ls", "pwd")

	while ((comando = strsep(&buf_ptr, ";")) != NULL) {

	    /* Aplicamos la lógica original de FASE 1 a cada trozo (comando) individualmente.
	       parser_orden() (que usan las funciones de abajo) es lo bastante robusto para ignorar los espacios en blanco (ej: "  ls -l  "). */

	    if (es_ord_interna(comando) == 1) {
		    ejecutar_ord_interna(comando);
	    } else {
		    // ejecutar_linea_ordenes() ya comprueba si el comando está vacío (ej: si escribimos 'ls ;; pwd') y lo maneja correctamente.
		    ejecutar_linea_ordenes(comando);
	    }
	} // Fin del bucle while(strsep)
   }
   return 0;
}
