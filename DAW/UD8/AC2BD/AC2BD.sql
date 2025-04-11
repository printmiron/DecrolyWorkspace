DROP DATABASE IF EXISTS schema_ac2;
CREATE DATABASE IF NOT EXISTS schema_ac2;
USE schema_ac2;


-- Tabla Persona (base de Cliente)
CREATE TABLE persona (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(150),
    fecha_nacimiento DATE
);

-- Tabla Cliente
CREATE TABLE cliente (
    dni VARCHAR(20) PRIMARY KEY,
    num_socio VARCHAR(20) UNIQUE,
    fecha_baja DATE,
    FOREIGN KEY (dni) REFERENCES persona(dni)
);

-- Tabla Articulo
CREATE TABLE articulo (
    cod VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(100),
    fecha_registro DATE DEFAULT (CURRENT_DATE),
    fecha_baja DATE
);

-- Tabla Pelicula
CREATE TABLE pelicula (
    cod VARCHAR(20),
    genero ENUM('ACCION', 'AVENTURA', 'CATASTROFE', 'CIENCIA_FICCION', 'COMEDIA', 'DOCUMENTALES', 'DRAMA', 'FANTASIA'),
    fecha_alquiler DATETIME,
    is_alquilada BOOLEAN,
    FOREIGN KEY (cod) REFERENCES articulo(cod)
);

-- Tabla Videojuego
CREATE TABLE videojuego (
    cod VARCHAR(20),
    genero ENUM('ACCION', 'AVENTURA', 'CATASTROFE', 'CIENCIA_FICCION', 'COMEDIA', 'DOCUMENTALES', 'DRAMA', 'FANTASIA'),
    fecha_alquiler DATETIME,
    is_alquilada BOOLEAN,
    FOREIGN KEY (cod) REFERENCES articulo(cod)
);

-- Tabla VideoClub
CREATE TABLE videoclub (
    cif VARCHAR(20) PRIMARY KEY,
    direccion VARCHAR(150),
    fecha_alta DATE DEFAULT (CURRENT_DATE)
);

-- Relación de artículos registrados en el videoclub
CREATE TABLE articulos_videoclub (
    cif VARCHAR(20),
    cod_articulo VARCHAR(20),
    FOREIGN KEY (cif) REFERENCES videoclub(cif),
    FOREIGN KEY (cod_articulo) REFERENCES articulo(cod)
);

-- Relación de clientes registrados en el videoclub
CREATE TABLE clientes_videoclub (
    cif VARCHAR(20),
    dni_cliente VARCHAR(20),
    FOREIGN KEY (cif) REFERENCES videoclub(cif),
    FOREIGN KEY (dni_cliente) REFERENCES cliente(dni)
);

-- Relación de artículos alquilados por clientes
CREATE TABLE alquileres (
    dni_cliente VARCHAR(20),
    cod_articulo VARCHAR(20),
    fecha_alquiler DATETIME,
    FOREIGN KEY (dni_cliente) REFERENCES cliente(dni),
    FOREIGN KEY (cod_articulo) REFERENCES articulo(cod)
);


