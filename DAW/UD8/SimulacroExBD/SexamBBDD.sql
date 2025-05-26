DROP DATABASE IF EXISTS siex_bbdd;
CREATE DATABASE IF NOT EXISTS siex_bbdd;
USE siex_bbdd;

create table persona (
	nombre 			varchar(30),
    apellido		varchar(30),
    dni				varchar(30),
    edad			int,
    sexo 			varchar(30),
    fechaNacimiento date,
    telefono		varchar(30),
    correo			varchar(30),
    direccion		varchar(30)
);
