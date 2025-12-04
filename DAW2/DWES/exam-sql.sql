CREATE DATABASE IF NOT EXISTS exempleexam;
USE exempleexam;

CREATE TABLE IF NOT EXISTS Pais (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL
);



CREATE TABLE IF NOT EXISTS Ciudad (
    id VARCHAR(40) PRIMARY KEY,

    pais_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,

    fecha DATE,
    max_temp DECIMAL(4,2),
    min_temp DECIMAL(4,2),
    media_temp DECIMAL(4,2),

    condicion ENUM(
        'LLUVIA',
        'SOLEADO',
        'NUBLADO',
        'TORMENTA',
        'NIEVE',
        'GRANIZO',
        'GALERNA'
    ),

    humedad INT,
    velocidad INT,

    -- Relación con Pais
    CONSTRAINT fk_pais
        FOREIGN KEY (pais_id) REFERENCES Pais(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);








