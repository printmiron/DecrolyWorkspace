DROP DATABASE IF EXISTS siex_bbdd;
CREATE DATABASE IF NOT EXISTS siex_bbdd;
USE siex_bbdd;

CREATE TABLE persona (
    nombre             VARCHAR(30),
    apellido           VARCHAR(30),
    dni                VARCHAR(30) PRIMARY KEY,
    edad               INT,
    sexo               VARCHAR(30),
    fechaNacimiento    DATE,
    telefono           VARCHAR(30),
    correo             VARCHAR(30),
    direccion          VARCHAR(30),
    UNIQUE KEY (dni)
);

