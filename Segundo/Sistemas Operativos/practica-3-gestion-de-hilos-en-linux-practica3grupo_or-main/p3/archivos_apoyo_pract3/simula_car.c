#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

#define N_COCHES 8

typedef struct {
    int id;
    char *nombre;
} coche_t;

coche_t Coches[N_COCHES];

/* -------------------------------------------------
   BLOQUE A COMPLETAR: CODIGO 0
   Espacio para variables globales adicionales si fueran necesarias
   ------------------------------------------------- */

// Arreglo de punteros para guardar el orden
coche_t *Clasificacion[N_COCHES];

// Contador para saber por qué posición vamos
//'volatile' para evitar optimizaciones del compilador en variables compartidas
volatile int coches_llegados = 0;

// Mutex específico para la meta
pthread_mutex_t mutex_meta = PTHREAD_MUTEX_INITIALIZER;

// --- Fin bloque 0 ---

pthread_mutex_t mutex_output = PTHREAD_MUTEX_INITIALIZER;

void *funcion_coche(void *arg)
{
    coche_t *pcoche = (coche_t *)arg;

    // Inicializar semilla distinta para cada hilo
    unsigned int semilla = pcoche->id + (unsigned int)time(NULL);
    int retardo = rand_r(&semilla) % 10 + 1;

    // Mensaje de salida del coche
    pthread_mutex_lock(&mutex_output);
    printf("Salida de %s %d\n", pcoche->nombre, pcoche->id);
    fflush(stdout);
    pthread_mutex_unlock(&mutex_output);

    // Simulacion de la carrera mediante retardo aleatorio
    sleep(retardo);

    // Mensaje de llegada del coche
    pthread_mutex_lock(&mutex_output);
    printf("Llegada de %s %d\n", pcoche->nombre, pcoche->id);
    pthread_mutex_unlock(&mutex_output);

    /* -------------------------------------------------
       BLOQUE A COMPLETAR: CODIGO 4
       Espacio para registrar la posicion o clasificacion
       ------------------------------------------------- */

    // Protegemos SOLO la escritura en memoria con el mutex especifico
    pthread_mutex_lock(&mutex_meta);

    // 2. Registro de la posición (CODIGO 4)
    Clasificacion[coches_llegados] = pcoche;
    coches_llegados++;

    pthread_mutex_unlock(&mutex_meta);

    // --- Fin bloque 4 ---

    /* -------------------------------------------------
       BLOQUE A COMPLETAR: CODIGO 6 (opcional)
       Espacio para devolver informacion al hilo principal
       ------------------------------------------------- */

    // Devolvemos el puntero al coche actual para que lo recoja el main
    pthread_exit((void *)pcoche);

    return NULL;

    // --- Fin bloque 6 ---
}

int main(void)
{
    pthread_t hilosCoches[N_COCHES];

    printf("Se inicia proceso de creacion de hilos...\n\n");
    printf("SALIDA DE COCHES\n");

    int i;

    /* -------------------------------------------------
       BLOQUE A COMPLETAR: CODIGO 1
       Espacio para inicializar estructuras y crear hilos
       ------------------------------------------------- */

    for (i = 0; i < N_COCHES; i++) {
        // 1. Inicialización del ID
        Coches[i].id = i;

        // 2. Reserva de memoria para el nombre (malloc)
        Coches[i].nombre = (char *)malloc(20 * sizeof(char));
        if (Coches[i].nombre == NULL) {
            perror("Error reservando memoria");
            exit(EXIT_FAILURE);
        }

        // 3. Asignación del nombre
        sprintf(Coches[i].nombre, "Coche %d", i);

        // 4. Creación del hilo
        // Pasamos la dirección de la estructura específica (&Coches[i])
        if (pthread_create(&hilosCoches[i], NULL, funcion_coche, (void *)&Coches[i]) != 0) {
            perror("Error creando hilo");
            exit(EXIT_FAILURE);
        }
    }

    // --- Fin bloque 1 ---

    printf("Proceso de creacion de hilos terminado\n\n");

    /* -------------------------------------------------
       BLOQUE A COMPLETAR: CODIGO 3
       Espacio para esperar a la finalizacion de los hilos
       ------------------------------------------------- */

    // Variable para recoger el puntero que devuelve el hilo
    void *retorno_hilo;

    for (i = 0; i < N_COCHES; i++) {
        // Pasamos la direccion de 'retorno_hilo' para capturar lo que envia pthread_exit
        if (pthread_join(hilosCoches[i], &retorno_hilo) != 0) {
            perror("Error esperando al hilo (join)");
            exit(EXIT_FAILURE);
        }
    }

    // --- Fin bloque 3 ---

    printf("Todos los coches han LLEGADO A LA META\n");

    /* -------------------------------------------------
       BLOQUE A COMPLETAR: CODIGO 5
       Espacio para mostrar la clasificacion final
       ------------------------------------------------- */

    printf("\n=============================\n");
    printf("     CLASIFICACIÓN FINAL     \n");
    printf("=============================\n");

    for (i = 0; i < N_COCHES; i++) {
        // Imprimimos el array 'Clasificacion' que rellenamos en el Bloque 4
        printf("Posición %d: %s (ID: %d)\n",
               i + 1,
               Clasificacion[i]->nombre,
               Clasificacion[i]->id);
    }
    printf("=============================\n");

    // Limpieza de memoria
    for (i = 0; i < N_COCHES; i++) {
        free(Coches[i].nombre);
    }

    // --- Fin bloque 5 ---

    // Liberar recursos del mutex de salida
    pthread_mutex_destroy(&mutex_output);

    // Liberar el mutex de la meta
    pthread_mutex_destroy(&mutex_meta);

    return 0;
}
