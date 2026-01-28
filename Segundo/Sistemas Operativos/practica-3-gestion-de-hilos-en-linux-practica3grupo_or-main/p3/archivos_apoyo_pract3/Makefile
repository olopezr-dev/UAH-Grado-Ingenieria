# Makefile
# Compilador y flags necesarios
CC = gcc
CFLAGS = -Wall -pthread
TARGET = simula_car
SRC = simula_car.c

# Objetivo por defecto: compilar el programa
all: $(TARGET)

$(TARGET): $(SRC)
	$(CC) $(CFLAGS) -o $(TARGET) $(SRC)

# Objetivo clean para limpiar ejecutables
clean:
	rm -f $(TARGET)

.PHONY: all clean
