# Variables de compilación
CC = gcc
CFLAGS = -g -Wall

# Regla principal (se ejecuta por defecto con make)
# El ejecutable 'minishell' depende de todos estos archivos .o y de la biblioteca .a

minishell: libmemoria.o entrada_minishell.o ejecutar.o minishell.o redirecciones.o libshell.a
	# Compila todos los objetos junto con la biblioteca libshell.a
	# -L.\ : indica que busque bibliotecas en el directorio actual y -lshell : enlaza la biblioteca libshell.a
	$(CC) $(CFLAGS) libmemoria.o entrada_minishell.o ejecutar.o redirecciones.o minishell.o -L./ -lshell -o minishell
	#Borramos los objetos  intermedios automáticamente para no dejar archivos basura.
	rm -f *.o

# Reglas de compilación de cada archivo fuente a objeto
# La opción -c compila el .c a .o sin enlazarlo
# Archivos objeto que se enlazarán directamente con minishell
entrada_minishell.o: entrada_minishell.c entrada_minishell.h
	$(CC) $(CFLAGS) -c entrada_minishell.c

libmemoria.o: libmemoria.c libmemoria.h
	$(CC) $(CFLAGS) -c libmemoria.c

ejecutar.o: ejecutar.c ejecutar.h parser.h libmemoria.h redirecciones.h
	$(CC) $(CFLAGS) -c ejecutar.c

minishell.o: minishell.c internas.h ejecutar.h entrada_minishell.h
	$(CC) $(CFLAGS) -c minishell.c

redirecciones.o: redirecciones.c redirecciones.h
	$(CC) $(CFLAGS) -c redirecciones.c


# Archivos objeto que irán DENTRO de la biblioteca libshell.a
parser.o: parser.c parser.h
	$(CC) $(CFLAGS) -c parser.c

internas.o: internas.c internas.h
	$(CC) $(CFLAGS) -c internas.c

# Generación de la biblioteca estática libshell.a
libshell.a: parser.o internas.o
	# ar -rs crea o actualiza la biblioteca
	ar -rs libshell.a parser.o internas.o

# Limpieza de archivos generados
.PHONY: clean
clean:
	# Borra el ejecutable, todos los objetos .o y la biblioteca .a
	rm -f minishell *.o libshell.a
