#ifndef EJECUTAR_H
#define EJECUTAR_H

#include <unistd.h> // Para el tipo pid_t

/* Prototipos de las funciones que están DEFINIDAS en ejecutar.c
  y que pueden ser LLAMADAS desde otros archivos (como minishell.c) */

/* Para la fase 7, se añaden lo parámetros 'entrada' y 'salida' para pasar los
   descriptores de archivo correspondientes a las tuberías*/

pid_t ejecutar_orden(const char *orden, int entrada, int salida, int *pbackgr);
void ejecutar_linea_ordenes(const char *orden);

#endif
