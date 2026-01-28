import psycopg2
from psycopg2 import Error
import psycopg2.errors # Importamos los errores específicos
import sys

# Configuración de conexión
DB_HOST = "localhost"
DB_PORT = "3333"
DB_NAME = "f1_db" 

# Credenciales
CREDENCIALES = {
    "1": {"rol": "Administrador", "user": "admin_f1",    "pass": "admin123"},
    "2": {"rol": "Gestor",        "user": "gestor_f1",   "pass": "gestor123"},
    "3": {"rol": "Analista",      "user": "analista_f1", "pass": "analista123"},
    "4": {"rol": "Invitado",      "user": "invitado_f1", "pass": "invitado123"}
}

def mostrar_menu():
    print("\n" + "="*45)
    print("   SELECCIÓN DE USUARIO - F1 DATABASE")
    print("="*45)
    print("1. Administrador (Control total)")
    print("2. Gestor        (Lectura y Escritura)")
    print("3. Analista      (Solo Lectura)")
    print("4. Invitado      (Lectura restringida)")
    print("="*45)
    opcion = input("Elige una opción (1-4): ")
    if opcion in CREDENCIALES:
        return CREDENCIALES[opcion]
    else:
        print("Opción no válida. Saliendo...")
        sys.exit()

def conectar_bd(usuario, clave):
    try:
        connection = psycopg2.connect(
            user=usuario, password=clave, host=DB_HOST, port=DB_PORT, database=DB_NAME
        )
        print(f"\n[INFO] Conexión establecida como: {usuario}")
        return connection
    except (Exception, Error) as error:
        print("[ERROR] Fallo al conectar:", error)
        return None

def ejecutar_consulta_lectura(connection):
    print("\n--- 1. PROBANDO LECTURA (GPs por País) ---")
    cursor = connection.cursor()
    sql_query = """
    SELECT c.country, COUNT(gp.raceId) as total
    FROM pl1final.Circuito c JOIN pl1final.GranPremio gp ON c.circuitId = gp.circuitId
    GROUP BY c.country ORDER BY total DESC LIMIT 3;
    """
    try:
        cursor.execute(sql_query)
        print(f"{'PAIS':<20} | {'NUMERO GPs'}")
        print("-" * 35)
        for fila in cursor.fetchall():
            print(f"{fila[0]:<20} | {fila[1]}")
    except (Exception, Error) as error:
        print("[ERROR] Lectura fallida:", error)
    finally:
        cursor.close()

def ejecutar_insercion(connection, rol_nombre):
    print(f"\n--- 2. PROBANDO ESCRITURA (Insertar con '{rol_nombre}') ---")
    cursor = connection.cursor()
    nombre_gp = "Gran Premio de Python 2025"
    anio = 2025
    
    try:
        # Verificar/Crear Temporada
        cursor.execute("SELECT year FROM pl1final.Temporada WHERE year = %s;", (anio,))
        if cursor.fetchone() is None:
            print(f"[INFO] Creando Temporada {anio}...")
            cursor.execute("INSERT INTO pl1final.Temporada (year, url) VALUES (%s, 'http://test.com');", (anio,))
        
        # Verificar/Crear GP
        cursor.execute("SELECT raceId FROM pl1final.GranPremio WHERE name = %s AND year = %s;", (nombre_gp, anio))
        if cursor.fetchone():
            print(f"[AVISO] El GP ya existe. No se duplica.")
        else:
            print("[INFO] Insertando Gran Premio...")
            cursor.execute("SELECT MAX(raceId) FROM pl1final.GranPremio;")
            max_id = cursor.fetchone()[0]
            new_id = (max_id + 1) if max_id is not None else 1
            
            cursor.execute("""
            INSERT INTO pl1final.GranPremio (raceId, year, round, circuitId, name, date, url)
            VALUES (%s, %s, 1, 1, %s, '2025-05-20', 'http://python-gp.com');
            """, (new_id, anio, nombre_gp))
            
            connection.commit()
            print(f"[EXITO] Datos insertados correctamente.")

    except psycopg2.errors.InsufficientPrivilege:
        print(f"[BLOQUEADO] El usuario '{rol_nombre}' NO tiene permisos de inserción.")
        connection.rollback()
    except (Exception, Error) as error:
        print("[ERROR] Error en inserción:", error)
        connection.rollback()
    finally:
        cursor.close()

def visualizar_cambios(connection, titulo_fase):
    """
    Muestra el estado actual de la tabla GranPremio para el año 2025.
    Recibe un 'titulo_fase' para saber en qué momento estamos imprimiendo.
    """
    print(f"\n--- {titulo_fase} ---")
    cursor = connection.cursor()
    anio_busqueda = 2025
    
    query = """
    SELECT raceId, name, date 
    FROM pl1final.GranPremio 
    WHERE year = %s 
    ORDER BY raceId DESC;
    """
    try:
        cursor.execute(query, (anio_busqueda,))
        registros = cursor.fetchall()
        
        if not registros:
            print(f"[INFO] ESTADO BD: No existen registros del año {anio_busqueda}.")
        else:
            print(f"[INFO] ESTADO BD: Se encontraron registros del año {anio_busqueda}:")
            print(f"{'ID':<10} | {'NOMBRE DEL GP':<30} | {'FECHA'}")
            print("-" * 55)
            for row in registros:
                print(f"{str(row[0]):<10} | {row[1]:<30} | {row[2]}")
                
    except (Exception, Error) as error:
         print("[ERROR] No se pudo visualizar la tabla:", error)
    finally:
        cursor.close()

def limpiar_datos(connection, rol_nombre):
    input("\nPresiona [ENTER] para borrar los datos y terminar (Limpieza)...")
    
    print(f"\n--- 3. LIMPIEZA DE DATOS (Borrar con '{rol_nombre}') ---")
    cursor = connection.cursor()
    nombre_gp = "Gran Premio de Python 2025"
    anio = 2025

    try:
        # 1. Borrar GP
        cursor.execute("DELETE FROM pl1final.GranPremio WHERE name = %s AND year = %s;", (nombre_gp, anio))
        filas_gp = cursor.rowcount
        
        # 2. Borrar Temporada
        cursor.execute("DELETE FROM pl1final.Temporada WHERE year = %s AND NOT EXISTS (SELECT 1 FROM pl1final.GranPremio WHERE year = %s);", (anio, anio))
        filas_temp = cursor.rowcount
        
        connection.commit()
        
        if filas_gp > 0 or filas_temp > 0:
            print(f"[EXITO] Limpieza completada. Borrados: {filas_gp} GPs, {filas_temp} Temporadas.")
        else:
            print("[INFO] No se borró nada (quizás no había datos o no tienes permisos).")

    except psycopg2.errors.InsufficientPrivilege:
        print(f"[BLOQUEADO] El usuario '{rol_nombre}' NO tiene permisos de borrado.")
        connection.rollback()
    except (Exception, Error) as error:
        print("[ERROR] Error en limpieza:", error)
        connection.rollback()
    finally:
        cursor.close()

def main():
    cred = mostrar_menu()
    conn = conectar_bd(cred["user"], cred["pass"])
    
    if conn:
        # 1. Lectura
        ejecutar_consulta_lectura(conn)
        
        # 2. Escritura
        ejecutar_insercion(conn, cred["rol"])
        
        # 2.5 Verificar visualmente la inserción
        visualizar_cambios(conn, "2.5. VERIFICACIÓN TRAS INSERCIÓN")
        
        # 3. Limpieza
        limpiar_datos(conn, cred["rol"])
        
        # 4. Verificar visualmente el borrado (NUEVO PASO)
        visualizar_cambios(conn, "4. VERIFICACIÓN FINAL (Post-Limpieza)")
        
        conn.close()
        print("\n[INFO] Conexión cerrada.")

if __name__ == "__main__":
    main()