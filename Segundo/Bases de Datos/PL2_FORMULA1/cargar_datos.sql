BEGIN;

\echo '--- LIMPIEZA INICIAL ---'
DROP SCHEMA IF EXISTS pl1temp CASCADE;
DROP SCHEMA IF EXISTS pl1final CASCADE;

\echo '--- 1. CREANDO ESQUEMA INTERMEDIO (TEMPORAL) ---'
CREATE SCHEMA IF NOT EXISTS pl1temp;

-- ########################################################
-- ###      CREACIÓN Y CARGA DE TABLAS TEMPORALES       ###
-- ########################################################



\echo 'Creando tabla temporal para circuits...'
CREATE TABLE IF NOT EXISTS pl1temp.circuits_temp (
    circuitId    TEXT,
    circuitRef   TEXT,
    name         TEXT,
    location     TEXT,
    country      TEXT,
    lat          TEXT,
    lng          TEXT,
    alt          TEXT,
    url          TEXT
);
\echo 'Cargando datos de circuits.csv...'
\copy pl1temp.circuits_temp FROM './datos/circuits.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para drivers.csv ---
\echo 'Creando tabla temporal para drivers...'
CREATE TABLE IF NOT EXISTS pl1temp.drivers_temp (
    driverId    TEXT,
    driverRef   TEXT,
    number      TEXT,
    code        TEXT,
    forename    TEXT,
    surname     TEXT,
    dob         TEXT,
    nationality TEXT,
    url         TEXT
);
\echo 'Cargando datos de drivers.csv...'
\copy pl1temp.drivers_temp FROM './datos/drivers.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para races.csv ---
\echo 'Creando tabla temporal para races...'
CREATE TABLE IF NOT EXISTS pl1temp.races_temp (
    raceId      TEXT,
    year        TEXT,
    round       TEXT,
    circuitId   TEXT,
    name        TEXT,
    date        TEXT,
    time        TEXT,
    url         TEXT,
    fp1_date    TEXT,
    fp1_time    TEXT,
    fp2_date    TEXT,
    fp2_time    TEXT,
    fp3_date    TEXT,
    fp3_time    TEXT,
    quali_date  TEXT,
    quali_time  TEXT,
    sprint_date TEXT,
    sprint_time TEXT
);
\echo 'Cargando datos de races.csv...'
\copy pl1temp.races_temp FROM './datos/races.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para constructors.csv ---
\echo 'Creando tabla temporal para constructors...'
CREATE TABLE IF NOT EXISTS pl1temp.constructors_temp (
    constructorId   TEXT,
    constructorRef  TEXT,
    name            TEXT,
    nationality     TEXT,
    url             TEXT
);
\echo 'Cargando datos de constructors.csv'
\copy pl1temp.constructors_temp FROM './datos/constructors.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para results.csv (CORREGIDO SEGÚN TU CSV) ---
\echo 'Creando tabla temporal para results...'
CREATE TABLE IF NOT EXISTS pl1temp.results_temp (
    resultados_id           TEXT,
    gpid                    TEXT,
    pilotoid                TEXT,
    escuderiaid             TEXT,
    "número"                TEXT, 
    pos_parrilla            TEXT,
    "posición"              TEXT, 
    "posicióntexto"         TEXT, 
    "posiciónorden"         TEXT, 
    puntos                  TEXT,
    vueltas                 TEXT,
    tiempo                  TEXT,
    tiempomilsgs            TEXT,
    "vueltarápida"          TEXT, 
    puesto_campeonato       TEXT,
    "vueltarápida_tiempo"   TEXT, 
    "vueltarápida_velocidad" TEXT, 
    estadoid                TEXT
);
\echo 'Cargando datos de results.csv...'
\copy pl1temp.results_temp FROM './datos/results.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para qualifying.csv ---
\echo 'Creando tabla temporal para qualifying...'
CREATE TABLE IF NOT EXISTS pl1temp.qualifying_temp (
    qualifyId       TEXT,
    raceId          TEXT,
    driverId        TEXT,
    constructorId   TEXT,
    number          TEXT,
    position        TEXT,
    q1              TEXT,
    q2              TEXT,
    q3              TEXT
);
\echo 'Cargando datos de qualifying.csv...'
\copy pl1temp.qualifying_temp FROM './datos/qualifying.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para pit_stops.csv ---
\echo 'Creando tabla temporal para pit_stops...'
CREATE TABLE IF NOT EXISTS pl1temp.pit_stops_temp (
    raceId          TEXT,
    driverId        TEXT,
    stop            TEXT,
    lap             TEXT,
    time            TEXT,
    duration        TEXT,
    milliseconds    TEXT
);
\echo 'Cargando datos de pit_stops.csv...'
\copy pl1temp.pit_stops_temp FROM './datos/pit_stops.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para lap_times.csv ---
\echo 'Creando tabla temporal para lap_times...'
CREATE TABLE IF NOT EXISTS pl1temp.lap_times_temp (
    raceId          TEXT,
    driverId        TEXT,
    lap             TEXT,
    position        TEXT,
    time            TEXT,
    milliseconds    TEXT
);
\echo 'Cargando datos de lap_times.csv...'
\copy pl1temp.lap_times_temp FROM './datos/lap_times.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para seasons.csv ---
\echo 'Creando tabla temporal para seasons...'
CREATE TABLE IF NOT EXISTS pl1temp.seasons_temp (
    year TEXT,
    url  TEXT
);
\echo 'Cargando datos de seasons.csv...'
\copy pl1temp.seasons_temp FROM './datos/seasons.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- --- Bloque para status.csv (CORREGIDO) ---
\echo 'Creando tabla temporal para status...'
CREATE TABLE IF NOT EXISTS pl1temp.status_temp (
    statusId TEXT,
    status   TEXT
);
\echo 'Cargando datos de status.csv...'
-- Corregida la errata de 'DELIMITA'
\copy pl1temp.status_temp FROM './datos/status.csv' WITH (FORMAT csv, HEADER, DELIMITER ',', NULL 'NULL', ENCODING 'UTF-8');


-- ########################################################
-- ###       PARTE 2: ESQUEMA FINAL (pl1final)          ###
-- ###      Creación de tablas limpias y con FKs        ###
-- ########################################################

\echo '--- 2. CREANDO ESQUEMA FINAL (DEFINITIVO) ---'
CREATE SCHEMA IF NOT EXISTS pl1final;

-- El orden de creación es importante para las Foreign Keys
-- 1. Tablas "padre" (sin dependencias)

\echo 'Creando tabla final Escuderia...'
CREATE TABLE IF NOT EXISTS pl1final.Escuderia (
    constructorId INTEGER PRIMARY KEY,
    constructorRef TEXT,
    name TEXT,
    nationality TEXT,
    url TEXT
);

\echo 'Creando tabla final Piloto...'
CREATE TABLE IF NOT EXISTS pl1final.Piloto (
    driverId INTEGER PRIMARY KEY,
    driverRef TEXT,
    number TEXT,
    code TEXT,
    forename TEXT,
    surname TEXT,
    dob DATE,
    nationality TEXT,
    url TEXT
);

\echo 'Creando tabla final Temporada...'
CREATE TABLE IF NOT EXISTS pl1final.Temporada (
    year INTEGER PRIMARY KEY,
    url TEXT
);

\echo 'Creando tabla final Circuito...'
CREATE TABLE IF NOT EXISTS pl1final.Circuito (
    circuitId INTEGER PRIMARY KEY,
    circuitRef TEXT,
    name TEXT,
    location TEXT,
    country TEXT,
    lat FLOAT,
    lng FLOAT,
    alt INTEGER,
    url TEXT
);

\echo 'Creando tabla final Status...'
CREATE TABLE IF NOT EXISTS pl1final.Status (
    statusId INTEGER PRIMARY KEY,
    status TEXT
);

-- 2. Tablas "hijas" (con dependencias y FKs)

\echo 'Creando tabla final GranPremio...'
CREATE TABLE IF NOT EXISTS pl1final.GranPremio (
    raceId INTEGER PRIMARY KEY,
    year INTEGER,
    round INTEGER,
    circuitId INTEGER,
    name TEXT,
    date DATE,
    time TIME,
    url TEXT,
    CONSTRAINT fk_temporada FOREIGN KEY (year) REFERENCES pl1final.Temporada(year),
    CONSTRAINT fk_circuito FOREIGN KEY (circuitId) REFERENCES pl1final.Circuito(circuitId)
);

\echo 'Creando tabla final Resultado...'
CREATE TABLE IF NOT EXISTS pl1final.Resultado (
    resultados_id INTEGER PRIMARY KEY,
    gpid INTEGER,
    pilotoid INTEGER,
    escuderiaid INTEGER,
    "número" INTEGER,
    pos_parrilla INTEGER,
    "posición" INTEGER,
    "posicióntexto" TEXT,
    "posiciónorden" INTEGER,
    puntos FLOAT,
    vueltas INTEGER,
    tiempo TEXT,
    tiempomilsgs INTEGER,
    "vueltarápida" INTEGER,
    puesto_campeonato INTEGER,
    "vueltarápida_tiempo" TEXT,
    "vueltarápida_velocidad" TEXT,
    estadoid INTEGER,
    CONSTRAINT fk_granpremio FOREIGN KEY (gpid) REFERENCES pl1final.GranPremio(raceId),
    CONSTRAINT fk_piloto FOREIGN KEY (pilotoid) REFERENCES pl1final.Piloto(driverId),
    CONSTRAINT fk_escuderia FOREIGN KEY (escuderiaid) REFERENCES pl1final.Escuderia(constructorId),
    CONSTRAINT fk_status FOREIGN KEY (estadoid) REFERENCES pl1final.Status(statusId)
);

\echo 'Creando tabla final Clasificacion...'
CREATE TABLE IF NOT EXISTS pl1final.Clasificacion (
    qualifyId INTEGER PRIMARY KEY,
    raceId INTEGER,
    driverId INTEGER,
    constructorId INTEGER,
    number INTEGER,
    position INTEGER,
    q1 TEXT,
    q2 TEXT,
    q3 TEXT,
    CONSTRAINT fk_granpremio FOREIGN KEY (raceId) REFERENCES pl1final.GranPremio(raceId),
    CONSTRAINT fk_piloto FOREIGN KEY (driverId) REFERENCES pl1final.Piloto(driverId),
    CONSTRAINT fk_escuderia FOREIGN KEY (constructorId) REFERENCES pl1final.Escuderia(constructorId)
);

\echo 'Creando tabla final Vuelta...'
CREATE TABLE IF NOT EXISTS pl1final.Vuelta (
    raceId INTEGER,
    driverId INTEGER,
    lap INTEGER,
    position INTEGER,
    time TEXT,
    milliseconds INTEGER,
    PRIMARY KEY (raceId, driverId, lap),
    CONSTRAINT fk_granpremio FOREIGN KEY (raceId) REFERENCES pl1final.GranPremio(raceId),
    CONSTRAINT fk_piloto FOREIGN KEY (driverId) REFERENCES pl1final.Piloto(driverId)
);

\echo 'Creando tabla final Boxes...'
CREATE TABLE IF NOT EXISTS pl1final.Boxes (
    raceId INTEGER,
    driverId INTEGER,
    stop INTEGER,
    lap INTEGER,
    time TIME,
    duration TEXT,
    milliseconds INTEGER,
    PRIMARY KEY (raceId, driverId, stop),
    CONSTRAINT fk_granpremio FOREIGN KEY (raceId) REFERENCES pl1final.GranPremio(raceId),
    CONSTRAINT fk_piloto FOREIGN KEY (driverId) REFERENCES pl1final.Piloto(driverId)
);

-- ########################################################
-- ###      PARTE 3: MIGRACIÓN DE DATOS (INSERT)        ###
-- ###      De pl1temp (trastero) a pl1final (casa)     ###
-- ########################################################

\echo '--- 3. MIGRANDO DATOS A ESQUEMA FINAL ---'

-- El orden de inserción DEBE seguir el orden de creación
-- 1. Rellenar tablas "padre"

\echo 'Migrando datos a Escuderia...'
INSERT INTO pl1final.Escuderia (constructorId, constructorRef, name, nationality, url)
SELECT DISTINCT
    CAST(NULLIF(constructorId, '\N') AS INTEGER),
    NULLIF(constructorRef, '\N'),
    NULLIF(name, '\N'),
    NULLIF(nationality, '\N'),
    NULLIF(url, '\N')
FROM
    pl1temp.constructors_temp;

\echo 'Migrando datos a Piloto...'
INSERT INTO pl1final.Piloto (driverId, driverRef, number, code, forename, surname, dob, nationality, url)
SELECT DISTINCT
    CAST(NULLIF(driverId, '\N') AS INTEGER),
    NULLIF(driverRef, '\N'),
    NULLIF(number, '\N'),
    NULLIF(code, '\N'),
    NULLIF(forename, '\N'),
    NULLIF(surname, '\N'),
    CAST(NULLIF(dob, '\N') AS DATE),
    NULLIF(nationality, '\N'),
    NULLIF(url, '\N')
FROM
    pl1temp.drivers_temp;

\echo 'Migrando datos a Temporada...'
INSERT INTO pl1final.Temporada (year, url)
SELECT DISTINCT
    CAST(NULLIF(year, '\N') AS INTEGER),
    NULLIF(url, '\N')
FROM
    pl1temp.seasons_temp;

\echo 'Migrando datos a Circuito...'
INSERT INTO pl1final.Circuito (circuitId, circuitRef, name, location, country, lat, lng, alt, url)
SELECT DISTINCT
    CAST(NULLIF(circuitId, '\N') AS INTEGER),
    NULLIF(circuitRef, '\N'),
    NULLIF(name, '\N'),
    NULLIF(location, '\N'),
    NULLIF(country, '\N'),
    CAST(NULLIF(lat, '\N') AS FLOAT),
    CAST(NULLIF(lng, '\N') AS FLOAT),
    CAST(NULLIF(alt, '\N') AS INTEGER),
    NULLIF(url, '\N')
FROM
    pl1temp.circuits_temp;

\echo 'Migrando datos a Status...'
INSERT INTO pl1final.Status (statusId, status)
SELECT DISTINCT
    CAST(NULLIF(statusId, '\N') AS INTEGER),
    NULLIF(status, '\N')
FROM
    pl1temp.status_temp;

-- 2. Rellenar tablas "hijas"
-- (Si hay errores aquí, es por fallos de Integridad Referencial en los datos)

\echo 'Migrando datos a GranPremio...'
INSERT INTO pl1final.GranPremio (raceId, year, round, circuitId, name, date, time, url)
SELECT DISTINCT
    CAST(NULLIF(raceId, '\N') AS INTEGER),
    CAST(NULLIF(year, '\N') AS INTEGER),
    CAST(NULLIF(round, '\N') AS INTEGER),
    CAST(NULLIF(circuitId, '\N') AS INTEGER),
    NULLIF(name, '\N'),
    CAST(NULLIF(date, '\N') AS DATE),
    CAST(NULLIF(time, '\N') AS TIME),
    NULLIF(url, '\N')
FROM
    pl1temp.races_temp;

\echo 'Migrando datos a Resultado...'
INSERT INTO pl1final.Resultado (resultados_id, gpid, pilotoid, escuderiaid, "número", pos_parrilla, "posición", "posicióntexto", "posiciónorden", puntos, vueltas, tiempo, tiempomilsgs, "vueltarápida", puesto_campeonato, "vueltarápida_tiempo", "vueltarápida_velocidad", estadoid)
SELECT DISTINCT
    CAST(NULLIF(resultados_id, '\N') AS INTEGER),
    CAST(NULLIF(gpid, '\N') AS INTEGER),
    CAST(NULLIF(pilotoid, '\N') AS INTEGER),
    CAST(NULLIF(escuderiaid, '\N') AS INTEGER),
    CAST(NULLIF("número", '\N') AS INTEGER),
    CAST(NULLIF(pos_parrilla, '\N') AS INTEGER),
    CAST(NULLIF("posición", '\N') AS INTEGER),
    NULLIF("posicióntexto", '\N'),
    CAST(NULLIF("posiciónorden", '\N') AS INTEGER),
    CAST(NULLIF(puntos, '\N') AS FLOAT),
    CAST(NULLIF(vueltas, '\N') AS INTEGER),
    NULLIF(tiempo, '\N'),
    CAST(NULLIF(tiempomilsgs, '\N') AS INTEGER),
    CAST(NULLIF("vueltarápida", '\N') AS INTEGER),
    CAST(NULLIF(puesto_campeonato, '\N') AS INTEGER),
    NULLIF("vueltarápida_tiempo", '\N'),
    NULLIF("vueltarápida_velocidad", '\N'),
    CAST(NULLIF(estadoid, '\N') AS INTEGER)
FROM
    pl1temp.results_temp;

\echo 'Migrando datos a Clasificacion...'
INSERT INTO pl1final.Clasificacion (qualifyId, raceId, driverId, constructorId, number, position, q1, q2, q3)
SELECT DISTINCT
    CAST(NULLIF(qualifyId, '\N') AS INTEGER),
    CAST(NULLIF(raceId, '\N') AS INTEGER),
    CAST(NULLIF(driverId, '\N') AS INTEGER),
    CAST(NULLIF(constructorId, '\N') AS INTEGER),
    CAST(NULLIF(number, '\N') AS INTEGER),
    CAST(NULLIF(position, '\N') AS INTEGER),
    NULLIF(q1, '\N'),
    NULLIF(q2, '\N'),
    NULLIF(q3, '\N')
FROM
    pl1temp.qualifying_temp;

\echo 'Migrando datos a Vuelta...'
INSERT INTO pl1final.Vuelta (raceId, driverId, lap, position, time, milliseconds)
SELECT DISTINCT
    CAST(NULLIF(raceId, '\N') AS INTEGER),
    CAST(NULLIF(driverId, '\N') AS INTEGER),
    CAST(NULLIF(lap, '\N') AS INTEGER),
    CAST(NULLIF(position, '\N') AS INTEGER),
    NULLIF(time, '\N'),
    CAST(NULLIF(milliseconds, '\N') AS INTEGER)
FROM
    pl1temp.lap_times_temp;

\echo 'Migrando datos a Boxes...'
INSERT INTO pl1final.Boxes (raceId, driverId, stop, lap, time, duration, milliseconds)
SELECT DISTINCT
    CAST(NULLIF(raceId, '\N') AS INTEGER),
    CAST(NULLIF(driverId, '\N') AS INTEGER),
    CAST(NULLIF(stop, '\N') AS INTEGER),
    CAST(NULLIF(lap, '\N') AS INTEGER),
    CAST(NULLIF(time, '\N') AS TIME),
    NULLIF(duration, '\N'),
    CAST(NULLIF(milliseconds, '\N') AS INTEGER)
FROM
    pl1temp.pit_stops_temp;


-- ########################################################
-- ###  PARTE 4: COMPROBACIONES (SELECTs en pl1final)   ###
-- ########################################################

\echo '--- 4. COMPROBANDO CARGA DE DATOS (en ESQUEMA FINAL) ---'

\echo 'Filas en pl1final.Escuderia:'
SELECT count(*) FROM pl1final.Escuderia;
\echo 'Filas en pl1final.Piloto:'
SELECT count(*) FROM pl1final.Piloto;
\echo 'Filas en pl1final.Temporada:'
SELECT count(*) FROM pl1final.Temporada;
\echo 'Filas en pl1final.Circuito:'
SELECT count(*) FROM pl1final.Circuito;
\echo 'Filas en pl1final.Status:'
SELECT count(*) FROM pl1final.Status;
\echo 'Filas en pl1final.GranPremio:'
SELECT count(*) FROM pl1final.GranPremio;
\echo 'Filas en pl1final.Resultado:'
SELECT count(*) FROM pl1final.Resultado;
\echo 'Filas en pl1final.Clasificacion:'
SELECT count(*) FROM pl1final.Clasificacion;
\echo 'Filas en pl1final.Vuelta:'
SELECT count(*) FROM pl1final.Vuelta;
\echo 'Filas en pl1final.Boxes:'
SELECT count(*) FROM pl1final.Boxes;

-- #############################
-- ###  ANTIGUAS CONSULTAS   ###
-- #############################

\echo 'Consulta 1: Circuitos y numero de GPs'
SELECT 
    t_circuito.name AS nombre_circuito, 
    COUNT(t_gp.raceid) AS numero_de_carreras 
FROM 
    pl1final.circuito AS t_circuito 
JOIN 
    pl1final.granpremio AS t_gp ON t_circuito.circuitid = t_gp.circuitid 
GROUP BY 
    t_circuito.circuitid, t_circuito.name 
ORDER BY 
    numero_de_carreras DESC;


\echo 'Consulta 2: Estadisticas de Ayrton Senna'
SELECT 
    t_piloto.forename, 
    t_piloto.surname, 
    COUNT(t_resultado.resultados_id) AS total_carreras_corridas, 
    SUM(t_resultado.puntos) AS total_puntos_conseguidos 
FROM 
    pl1final.piloto AS t_piloto 
JOIN 
    pl1final.resultado AS t_resultado ON t_piloto.driverid = t_resultado.pilotoid
WHERE
    t_piloto.forename = 'Ayrton' AND t_piloto.surname = 'Senna'
GROUP BY 
    t_piloto.driverid, t_piloto.forename, t_piloto.surname;


\echo 'Consulta 3: Pilotos nacidos despues de 1999'
SELECT 
    t_piloto.forename AS nombre, 
    t_piloto.surname AS apellido, 
    t_piloto.dob AS fecha_nacimiento, 
    COUNT(t_resultado.resultados_id) AS numero_de_carreras 
FROM 
    pl1final.piloto AS t_piloto 
LEFT JOIN 
    pl1final.resultado AS t_resultado ON t_piloto.driverid = t_resultado.pilotoid 
WHERE 
    t_piloto.dob > '1999-12-31' 
GROUP BY 
    t_piloto.driverid, t_piloto.forename, t_piloto.surname, t_piloto.dob 
ORDER BY 
    numero_de_carreras DESC;


\echo 'Consulta 4: Escuderias espanolas o italianas'
SELECT 
    t_escuderia.name AS nombre_escuderia, 
    t_escuderia.nationality, 
    COUNT(DISTINCT t_resultado.gpid) AS grandes_premios_corridos 
FROM 
    pl1final.escuderia AS t_escuderia 
JOIN 
    pl1final.resultado AS t_resultado ON t_escuderia.constructorid = t_resultado.escuderiaid 
WHERE 
    t_escuderia.nationality = 'Spanish' OR t_escuderia.nationality = 'Italian' 
GROUP BY 
    t_escuderia.constructorid, t_escuderia.name, t_escuderia.nationality 
ORDER BY 
    grandes_premios_corridos DESC;


\echo 'Consulta 5: Vista de puntos por temporada'
CREATE VIEW vista_puntos_por_temporada AS 
SELECT 
    gp.year AS temporada, 
    p.forename AS nombre_piloto, 
    p.surname AS apellido_piloto, 
    SUM(r.puntos) AS puntos_totales_temporada 
FROM 
    pl1final.granpremio AS gp 
JOIN 
    pl1final.resultado AS r ON gp.raceid = r.gpid 
JOIN 
    pl1final.piloto AS p ON r.pilotoid = p.driverid 
GROUP BY 
    gp.year, 
    p.driverid, 
    p.forename, 
    p.surname 
ORDER BY 
    temporada DESC, 
    puntos_totales_temporada DESC;



-- ###########################
-- ###  NUEVAS CONSULTAS   ###
-- ###########################


\echo 'Consulta 6: Ganadores 2010-2015 (usando la vista)'
SELECT DISTINCT ON (temporada)
    temporada,
    nombre_piloto,
    apellido_piloto,
    puntos_totales_temporada
FROM
    vista_puntos_por_temporada
WHERE
    temporada BETWEEN 2010 AND 2015
ORDER BY
    temporada ASC,
    puntos_totales_temporada DESC;

\echo 'Consulta 7: Pilotos con al menos una victoria (Posicion = 1)'
SELECT DISTINCT
    p.forename,
    p.surname
FROM
    pl1final.Piloto p
JOIN
    pl1final.Resultado r ON p.driverId = r.pilotoid
WHERE
    r."posición" = 1
ORDER BY
    p.surname;

\echo 'Consulta 8: Numero de GPs por pais'
SELECT
    c.country AS pais,
    COUNT(gp.raceId) AS numero_grandes_premios
FROM
    pl1final.Circuito c
JOIN
    pl1final.GranPremio gp ON c.circuitId = gp.circuitId
GROUP BY
    c.country
ORDER BY
    numero_grandes_premios DESC;

\echo 'Consulta 9: Vuelta mas rapida de la historia'
SELECT
    p.forename,
    p.surname,
    v.time AS tiempo_vuelta,
    v.milliseconds
FROM
    pl1final.Piloto p
JOIN
    pl1final.Vuelta v ON p.driverId = v.driverId
WHERE
    v.milliseconds = (
        SELECT MIN(milliseconds)
        FROM pl1final.Vuelta
    );

\echo 'Consulta 10: Paradas por piloto en Monaco 2023'
SELECT
    p.forename,
    p.surname,
    COUNT(b.stop) AS numero_paradas
FROM
    pl1final.Boxes b
JOIN
    pl1final.GranPremio gp ON b.raceId = gp.raceId
JOIN
    pl1final.Piloto p ON b.driverId = p.driverId
WHERE
    gp.year = 2023 AND gp.name LIKE '%Monaco%'
GROUP BY
    p.driverId, p.forename, p.surname
ORDER BY
    numero_paradas DESC;

\echo 'Consulta 11: Pilotos con +100 carreras'
SELECT
    p.forename,
    p.surname,
    COUNT(r.resultados_id) AS carreras_participadas
FROM
    pl1final.Piloto p
JOIN
    pl1final.Resultado r ON p.driverId = r.pilotoid
GROUP BY
    p.driverId, p.forename, p.surname
HAVING
    COUNT(r.resultados_id) > 100
ORDER BY
    carreras_participadas DESC;


-- ###################
-- ###  TRIGGERS   ###
-- ###################

-- 1.1. TRIGGER DE AUDITORÍA
-- tabla donde se guardarán los registros
CREATE TABLE IF NOT EXISTS pl1final.Auditoria (
    id_auditoria SERIAL PRIMARY KEY,
    tabla_afectada TEXT,
    operacion TEXT, -- INSERT, UPDATE, DELETE
    usuario TEXT,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    datos_nuevos TEXT -- para ver qué se cambió
);

CREATE OR REPLACE FUNCTION pl1final.func_auditoria() 
RETURNS TRIGGER AS $$
DECLARE
    datos_registro TEXT;
BEGIN
    -- Determinamos qué datos guardar según la operación (NUEVO)
    IF (TG_OP = 'DELETE') THEN
        datos_registro := OLD::text;
    ELSE
        datos_registro := NEW::text;
    END IF;

    INSERT INTO pl1final.Auditoria (tabla_afectada, operacion, usuario, datos_nuevos)
    VALUES (TG_TABLE_NAME, TG_OP, current_user, datos_registro);
    
    -- En triggers AFTER, el valor de retorno se ignora, pero es buena práctica devolver null 
    -- en delete o new en otros.
    IF (TG_OP = 'DELETE') THEN
        RETURN OLD;
    ELSE
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Asignamos el trigger a las tablas principales (Ejemplo: Piloto, Escuderia, GranPremio)
DROP TRIGGER IF EXISTS trg_auditoria_piloto ON pl1final.Piloto;
CREATE TRIGGER trg_auditoria_piloto
AFTER INSERT OR UPDATE OR DELETE ON pl1final.Piloto
FOR EACH ROW EXECUTE FUNCTION pl1final.func_auditoria();

DROP TRIGGER IF EXISTS trg_auditoria_escuderia ON pl1final.Escuderia;
CREATE TRIGGER trg_auditoria_escuderia
AFTER INSERT OR UPDATE OR DELETE ON pl1final.Escuderia
FOR EACH ROW EXECUTE FUNCTION pl1final.func_auditoria();

-- 1.2. TRIGGER DE PUNTOS DE PILOTO
-- tabla de resumen de puntos con los datos actuales
CREATE TABLE IF NOT EXISTS pl1final.PuntosTotalesPiloto (
    driverId INTEGER PRIMARY KEY,
    puntos_totales FLOAT
);

-- la tabla con la suma actual (para que empiece bien)
TRUNCATE TABLE pl1final.PuntosTotalesPiloto;
INSERT INTO pl1final.PuntosTotalesPiloto (driverId, puntos_totales)
SELECT driverId, 0 FROM pl1final.Piloto; -- Iniciamos a 0 todos

-- Actualizamos con los puntos históricos
UPDATE pl1final.PuntosTotalesPiloto p
SET puntos_totales = (
    SELECT COALESCE(SUM(r.puntos), 0)
    FROM pl1final.Resultado r
    WHERE r.pilotoid = p.driverId
);

-- función del trigger
CREATE OR REPLACE FUNCTION pl1final.func_actualizar_puntos()
RETURNS TRIGGER AS $$
BEGIN
    -- Intentamos actualizar
    UPDATE pl1final.PuntosTotalesPiloto
    SET puntos_totales = puntos_totales + NEW.puntos
    WHERE driverId = NEW.pilotoid;

    -- Si no se actualizó ninguna fila (porque el piloto es nuevo en la tabla de resumen), lo insertamos. (NUEVO)
    IF NOT FOUND THEN
        INSERT INTO pl1final.PuntosTotalesPiloto (driverId, puntos_totales)
        VALUES (NEW.pilotoid, NEW.puntos);
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Asignamos el trigger a la tabla Resultado (se dispara al insertar un resultado)
DROP TRIGGER IF EXISTS trg_sumar_puntos ON pl1final.Resultado;
CREATE TRIGGER trg_sumar_puntos
AFTER INSERT ON pl1final.Resultado
FOR EACH ROW EXECUTE FUNCTION pl1final.func_actualizar_puntos();

-- #######################################
-- ###        PRUEBAS TRIGGERS         ###
-- #######################################

/*
-- --- PRUEBA 1: TRIGGER AUDITORIA ---
\echo '--- Probando Auditoría ---'

-- 1. Ver estado actual
SELECT * FROM pl1final.Auditoria ORDER BY id_auditoria DESC LIMIT 5;

-- 2. Operaciones que disparan el trigger
INSERT INTO pl1final.Piloto (driverId, driverRef, surname) VALUES (99999, 'test_dummy', 'DummyMan');
UPDATE pl1final.Piloto SET forename = 'Crash' WHERE driverId = 99999;
DELETE FROM pl1final.Piloto WHERE driverId = 99999; -- Esto limpia la tabla Piloto, pero deja rastro en Auditoria (correcto)

-- 3. Verificación final (Debe haber 3 registros nuevos: INSERT, UPDATE, DELETE)
SELECT * FROM pl1final.Auditoria ORDER BY id_auditoria DESC LIMIT 5;


-- --- PRUEBA 2: TRIGGER PUNTOS PILOTO ---
\echo '--- Probando Suma de Puntos ---'

-- 1. Ver puntos actuales del piloto 1 (Lewis Hamilton suele ser el 1)
SELECT driverId, puntos_totales FROM pl1final.PuntosTotalesPiloto WHERE driverId = 1;

-- 2. Insertar resultado ficticio (50 puntos extra)
INSERT INTO pl1final.Resultado (resultados_id, gpid, pilotoid, puntos)
VALUES (999999, 1, 1, 50); 

-- 3. Verificación final (Debería tener 50 puntos más que antes)
SELECT driverId, puntos_totales FROM pl1final.PuntosTotalesPiloto WHERE driverId = 1;

-- 4. LIMPIEZA (Importante para re-ejecución)
-- Borramos el resultado de prueba. 
-- OJO: Esto NO restará los puntos si no hiciste trigger de DELETE. 
-- Pero al menos evita error de "Duplicate Key" si corres el script de nuevo.
DELETE FROM pl1final.Resultado WHERE resultados_id = 999999;
*/


-- ########################################
-- ###      GESTIÓN DE USUARIOS         ###
-- ########################################

\echo '--- CREACION DE ROLES Y USUARIOS ---'

-- 1. USUARIO ADMINISTRADOR
-- Debe poder ejecutar cualquier operación.
-- Nota: En PostgreSQL 'postgres' o el dueño de la DB ya es admin, pero creamos uno específico.

-- En lugar de borrar (DROP), verificamos si existe.
-- Si no existe, lo creamos. Si existe, solo actualizamos la contraseña.
DO $$
BEGIN
  IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'admin_f1') THEN
    CREATE USER admin_f1 WITH PASSWORD 'admin123';
  ELSE
    ALTER USER admin_f1 WITH PASSWORD 'admin123';
  END IF;
END
$$;

GRANT ALL PRIVILEGES ON SCHEMA pl1final TO admin_f1;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA pl1final TO admin_f1;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA pl1final TO admin_f1;
GRANT ALL PRIVILEGES ON DATABASE "f1_db" TO admin_f1;


-- 2. USUARIO GESTOR DE COMPETICIONES
-- Puede manejar datos (inserción, actualización, borrado y consulta), pero no debe
-- de poder crear nuevas tablas ni elementos que afecten a la estructura de la base de datos.
DO $$
BEGIN
  IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'gestor_f1') THEN
    CREATE USER gestor_f1 WITH PASSWORD 'gestor123';
  ELSE
    ALTER USER gestor_f1 WITH PASSWORD 'gestor123';
  END IF;
END
$$;

GRANT USAGE ON SCHEMA pl1final TO gestor_f1;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA pl1final TO gestor_f1;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA pl1final TO gestor_f1; 
-- (Necesario para que funcionen los SERIAL/autoincrementales al insertar)


-- 3. USUARIO ANALISTA
-- Un usuario analista que sólo puede realizar consultas a la base de datos.
DO $$
BEGIN
  IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'analista_f1') THEN
    CREATE USER analista_f1 WITH PASSWORD 'analista123';
  ELSE
    ALTER USER analista_f1 WITH PASSWORD 'analista123';
  END IF;
END
$$;

GRANT USAGE ON SCHEMA pl1final TO analista_f1;
GRANT SELECT ON ALL TABLES IN SCHEMA pl1final TO analista_f1;


-- 4. USUARIO INVITADO
-- Un usuario invitado que sólo puede consultar los resultados de las carreras, de
-- los pilotos, grandes premios, escuderías, circuitos y temporadas.
-- Pero no puede consultar los tiempos por vuelta ni las paradas en boxes.
DO $$
BEGIN
  IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'invitado_f1') THEN
    CREATE USER invitado_f1 WITH PASSWORD 'invitado123';
  ELSE
    ALTER USER invitado_f1 WITH PASSWORD 'invitado123';
  END IF;
END
$$;

GRANT USAGE ON SCHEMA pl1final TO invitado_f1;

-- Damos permiso tabla por tabla explícitamente para evitar darle a 'Vuelta' y 'Boxes'
GRANT SELECT ON pl1final.Resultado TO invitado_f1;
GRANT SELECT ON pl1final.Piloto TO invitado_f1;
GRANT SELECT ON pl1final.GranPremio TO invitado_f1;
GRANT SELECT ON pl1final.Escuderia TO invitado_f1;
GRANT SELECT ON pl1final.Circuito TO invitado_f1;
GRANT SELECT ON pl1final.Temporada TO invitado_f1;
GRANT SELECT ON pl1final.Status TO invitado_f1;
GRANT SELECT ON pl1final.Clasificacion TO invitado_f1;

-- Aseguramos que NO tenga acceso a las prohibidas (por seguridad redundante)
REVOKE ALL ON pl1final.Vuelta FROM invitado_f1;
REVOKE ALL ON pl1final.Boxes FROM invitado_f1;


-- #######################################
-- ###      PRUEBAS DE PERMISOS        ###
-- #######################################

/*
-- 1. PRUEBA DEL ANALISTA (Solo lectura)
-- Requisito: "sólo puede realizar consultas"
SET ROLE analista_f1;
\echo '--- Prueba Analista: SELECT (Debe funcionar) ---'
SELECT count(*) FROM pl1final.Piloto; 
\echo '--- Prueba Analista: DELETE (Debe FALLAR - Permission denied) ---'
DELETE FROM pl1final.Piloto WHERE driverId = 1;
RESET ROLE;

-- 2. PRUEBA DEL GESTOR (Lectura y Escritura, pero no DDL)
-- Requisito: "manejar los datos... pero no debe poder crear nuevas tablas" [cite: 61]
SET ROLE gestor_f1;
\echo '--- Prueba Gestor: INSERT (Debe funcionar) ---'
-- Insertamos un status de prueba
INSERT INTO pl1final.Status (statusId, status) VALUES (999, 'Test Gestor');
\echo '--- Prueba Gestor: SELECT del dato insertado ---'
SELECT * FROM pl1final.Status WHERE statusId = 999;
\echo '--- Prueba Gestor: DROP TABLE (Debe FALLAR - Permission denied) ---'
DROP TABLE pl1final.Status;
-- Limpiamos (El gestor sí puede borrar datos)
DELETE FROM pl1final.Status WHERE statusId = 999;
RESET ROLE;

-- 3. PRUEBA DEL INVITADO (Restricción de tablas)
-- Requisito: "no puede consultar los tiempos por vuelta ni las paradas en boxes" [cite: 64]
SET ROLE invitado_f1;
\echo '--- Prueba Invitado: Ver Pilotos (Debe funcionar) ---'
SELECT * FROM pl1final.Piloto LIMIT 1;
\echo '--- Prueba Invitado: Ver Boxes (Debe FALLAR - Permission denied) ---'
SELECT * FROM pl1final.Boxes LIMIT 1;
RESET ROLE;
*/

-- ########################################################
-- ###         PARTE 5: FIN DE LA TRANSACCIÓN           ###
-- ########################################################

\echo '--- 5. FIN DE LA TRANSACCION ---'
\echo 'Codigo ejecutado con exito si a continuacion aparece COMMIT'
COMMIT;
-- ROLLBACK; 