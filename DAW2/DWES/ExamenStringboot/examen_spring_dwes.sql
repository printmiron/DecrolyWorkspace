CREATE DATABASE IF NOT EXISTS examen_spring_dwes
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE examen_spring_dwes;

CREATE TABLE IF NOT EXISTS equipos (
id BIGINT NOT NULL AUTO_INCREMENT,
nombre_equipo VARCHAR(100) NOT NULL,
sede VARCHAR(100) NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS jugadores (
id CHAR(36) NOT NULL,
dorsal INT NOT NULL,
nombre VARCHAR(100) NOT NULL,
apellido1 VARCHAR(100) NOT NULL,
apellido2 VARCHAR(100) NOT NULL,
posicion ENUM('PORTERO', 'DEFENSA', 'MEDIO', 'DELANTERO') NOT NULL,
equipo_id BIGINT NOT NULL,
PRIMARY KEY (id),
INDEX idx_jugadores_equipo (equipo_id),
CONSTRAINT fk_jugadores_equipos
FOREIGN KEY (equipo_id)
REFERENCES equipos(id)
ON UPDATE CASCADE
ON DELETE RESTRICT,
UNIQUE KEY uk_jugadores_equipo_dorsal (equipo_id, dorsal)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS arbitros (
id CHAR(36) NOT NULL,
nombre VARCHAR(100) NOT NULL,
apellido1 VARCHAR(100) NOT NULL,
apellido2 VARCHAR(100) NOT NULL,
rol ENUM('PRINCIPAL', 'ASISTENTE') NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS partidos (
id CHAR(36) NOT NULL,
equipo1_id BIGINT NOT NULL,
equipo2_id BIGINT NOT NULL,
arbitro1_id CHAR(36) NOT NULL,
arbitro2_id CHAR(36) NOT NULL,
PRIMARY KEY (id),
INDEX idx_partidos_equipo1 (equipo1_id),
INDEX idx_partidos_equipo2 (equipo2_id),
INDEX idx_partidos_arbitro1 (arbitro1_id),
INDEX idx_partidos_arbitro2 (arbitro2_id),
CONSTRAINT fk_partidos_equipo1
FOREIGN KEY (equipo1_id)
REFERENCES equipos(id)
ON UPDATE CASCADE
ON DELETE RESTRICT,
CONSTRAINT fk_partidos_equipo2
FOREIGN KEY (equipo2_id)
REFERENCES equipos(id)
ON UPDATE CASCADE
ON DELETE RESTRICT,
CONSTRAINT fk_partidos_arbitro1
FOREIGN KEY (arbitro1_id)
REFERENCES arbitros(id)
ON UPDATE CASCADE
ON DELETE RESTRICT,
CONSTRAINT fk_partidos_arbitro2
FOREIGN KEY (arbitro2_id)
REFERENCES arbitros(id)
ON UPDATE CASCADE
ON DELETE RESTRICT
) ENGINE=InnoDB;

