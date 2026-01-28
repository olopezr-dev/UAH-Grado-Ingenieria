#include <stdlib.h>

#include "libmemoria.h"

void free_argumentos(char **args){
   int i = 0;

   while(args[i])
      free(args[i++]);
   free(args);
}
/* Liberacion de memoria de tuberias
   Libera tanto el array de cadenas(órdenes) como el array de enteros (descriptores)
   Se debe liberar cada subarray pipes[i] antes de liberar el array principal pipes. */
void free_ordenes_pipes(char **ordenes, int **fds, int nordenes){
   int i = 0;

   for (i = 0; i < nordenes; i++) {
      free(ordenes[i]); // Liberar cadena de orden

      // Liberar estructuctura de pipe si existiese ( ya que hay n ordenes - 1 pipes )
      if (i < (nordenes - 1))
         free(fds[i]);
   }

   free(ordenes);
   free(fds); // Verificacion de seguridad extra
}
