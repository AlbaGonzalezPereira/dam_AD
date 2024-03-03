CREATE DATABASE futbol;
CREATE SCHEMA objetos;

--Creamos los objetos
CREATE TYPE objetos.Persona AS (
	nombre varchar(30),
	edad int
);
CREATE TYPE objetos.Jugador AS (
	dorsal int,
	posicion varchar(10),
	altura decimal 
);
CREATE TYPE objetos.Equipo AS (
	nombre varchar(20),
	ciudad varchar(20),
	entrenador objetos.Persona
);

--Creamos las tablas
CREATE TABLE objetos.Equipos(
    equipo_id SERIAL PRIMARY KEY,
    equipo_info objetos.Equipo
);

CREATE TABLE objetos.Jugadores (
	jugador_id SERIAL PRIMARY KEY,
	datos_personales objetos.Persona,
	jugador_info objetos.Jugador,
    equipo_id int REFERENCES objetos.Equipos(equipo_id)
);

CREATE TABLE objetos.Partidos(
    partido_id SERIAL PRIMARY KEY,
    fecha Date,
    equipo_local int REFERENCES objetos.Equipos(equipo_id),
    equipo_visitante int REFERENCES objetos.Equipos(equipo_id)
);

-- Insertamos datos en la tabla objetos.Equipos
INSERT INTO objetos.Equipos (equipo_id, equipo_info)
VALUES (1,
    ROW('Real Madrid', 'Madrid', ('Zinedine Zidane', 49))
);

INSERT INTO objetos.Equipos (equipo_id, equipo_info)
VALUES (2,
    ROW('FC Barcelona', 'Barcelona', ('Xavi Hernandez', 42))
);

-- Insertamos datos en la tabla objetos.Jugadores
INSERT INTO objetos.Jugadores (jugador_id, datos_personales, jugador_info, equipo_id)
VALUES (1, 
    ROW('Lionel Messi', 34), (10, 'Delantero', 180),
    2
);

INSERT INTO objetos.Jugadores (jugador_id, datos_personales, jugador_info, equipo_id)
VALUES (2,
    ROW('Sergio Ramos', 35), (4, 'Defensa', 175),
    1
);

INSERT INTO objetos.Jugadores (jugador_id, datos_personales, jugador_info, equipo_id)
VALUES (3,
    ROW('Andres Iniesta', 38), (8, 'Central', 182),
    2
);

INSERT INTO objetos.Jugadores (jugador_id, datos_personales, jugador_info, equipo_id)
VALUES (4,
    ROW('Gerard Piqué', 34), (3, 'Defensa', 2),
    2
);

-- Insertamos datos en la tabla objetos.Partidos
INSERT INTO objetos.Partidos (partido_id, fecha, equipo_local, equipo_visitante)
VALUES (1, '2024-02-24', 1, 2);

INSERT INTO objetos.Partidos (partido_id, fecha, equipo_local, equipo_visitante)
VALUES (2, '2024-03-10', 2, 1);

--pARA REINICIAR LA SECUENCIA
DO $$
DECLARE
    nuevo_valor INTEGER;
BEGIN
    SELECT MAX(equipo_id) + 1 INTO nuevo_valor FROM objetos.equipos;
    EXECUTE 'ALTER SEQUENCE objetos.equipos_equipo_id_seq RESTART WITH ' || nuevo_valor;
END $$;

DO $$
DECLARE
    nuevo_valor INTEGER;
BEGIN
    SELECT MAX(jugador_id) + 1 INTO nuevo_valor FROM objetos.jugadores;
    EXECUTE 'ALTER SEQUENCE objetos.jugadores_jugador_id_seq RESTART WITH ' || nuevo_valor;
END $$;

DO $$
DECLARE
    nuevo_valor INTEGER;
BEGIN
    SELECT MAX(partido_id) + 1 INTO nuevo_valor FROM objetos.partidos;
    EXECUTE 'ALTER SEQUENCE objetos.partidos_partido_id_seq RESTART WITH ' || nuevo_valor;
END $$;