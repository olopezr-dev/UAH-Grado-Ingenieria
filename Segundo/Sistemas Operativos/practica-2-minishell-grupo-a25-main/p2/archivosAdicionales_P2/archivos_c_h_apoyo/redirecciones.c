#include <stdio.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <fcntl.h>


/* Funcion que abre el archivo situado en la posicion indice_entrada+1
de la orden args y elimina de ella la redireccion completa, es decir,
abre el archivo para lectura y modifica el array de argumentos.*/

void redirec_entrada(char **args, int indice_entrada, int *entrada){
    // El nombre del archivo está en la posición siguiente al símbolo '<'
    char *nombre_fichero = args[indice_entrada + 1];

    /* Abrimos el archivo solo para lectura (O_RONLY)
       Si el archivo no existe, open devolverá -1.
       O_RDONLY: Read Only */
    *entrada = open(nombre_fichero, O_RDONLY);

    if (*entrada == -1) {
        perror("Error al abrir fichero de entrada");
        return; // El error se gestionará en ejecutar.c
    }

    /* Para que execvp no se confunda con el '<' y el nombre del archvo,
       ponemos un NULL en la posición del símbolo '<'. Así la orden "termina"
       antes de la redirección para el sistema.
       Ejemplo: ls < in.txt --> ls NULL in.txt*/
    args[indice_entrada] = NULL;
}

/* Funcion que abre el archivo situado en la posicion indice_salida+1
   de la orden args y elimina de ella la redireccion completa, es decir, abre
   o crea el archivo para escritura.*/

void redirec_salida(char **args, int indice_salida, int *salida) {
    char *nombre_fichero = args[indice_salida + 1];

    /* Configuracion de flags para salida:
       O_WRONLY: Solo escritura
       O_CREAT: Crear si no existe
       O_TRUNC: Borrar contenido si ya existe
       0666: Permisos de lectura/escritura para todos (modificado por umask)*/
    *salida = open(nombre_fichero, O_WRONLY | O_CREAT | O_TRUNC, 0666);

    if (*salida == -1) {
        perror("Error al abrir fichero de salida");
        return;
    }

    // Igual que antes, "cortamos" la orden poniendo NULL donde estaba '>'
    args[indice_salida] = NULL;
}

