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

-- Relación de artículos alquilados por clientes
CREATE TABLE alquileres (
    dni_cliente VARCHAR(20),
    cod_articulo VARCHAR(20),
    fecha_alquiler DATETIME,
    fecha_devolucion DATETIME,
    FOREIGN KEY (dni_cliente) REFERENCES cliente(dni),
    FOREIGN KEY (cod_articulo) REFERENCES articulo(cod)
);


-- INSERT para la tabla `persona`
INSERT INTO persona (dni, nombre, direccion, fecha_nacimiento) 
VALUES 
('12345678A', 'Juan Pérez', 'Calle Falsa 123, Madrid', '1985-05-12'),
('98765432B', 'Ana García', 'Avenida Libertad 456, Barcelona', '1990-08-25'),
('11223344C', 'Carlos López', 'Calle Real 789, Valencia', '1978-11-30');

-- INSERT para la tabla `cliente`
INSERT INTO cliente (dni, num_socio, fecha_baja) 
VALUES 
('12345678A', 'S-001', NULL),
('98765432B', 'S-002', NULL),
('11223344C', 'S-003', NULL);

-- INSERT para la tabla `articulo`
INSERT INTO articulo (cod, titulo, fecha_registro, fecha_baja) 
VALUES 
('P-001', 'La Vida Secreta de los Árboles', '2024-01-10', NULL),
('V-001', 'Super Mario Bros', '2024-02-20', NULL),
('P-002', 'Avengers: Endgame', '2024-03-15', NULL),
('V-002', 'The Legend of Zelda: Breath of the Wild', '2024-04-01', NULL);

-- INSERT para la tabla `pelicula`
INSERT INTO pelicula (cod, genero, fecha_alquiler, is_alquilada) 
VALUES 
('P-001', 'COMEDIA', '2025-04-01 10:00:00', TRUE),
('P-002', 'CIENCIA_FICCION', NULL, FALSE);

-- INSERT para la tabla `videojuego`
INSERT INTO videojuego (cod, genero, fecha_alquiler, is_alquilada) 
VALUES 
('V-001', 'ACCION', '2025-04-01 09:00:00', TRUE),
('V-002', 'AVENTURA', NULL, FALSE);

-- INSERT para la tabla `videoclub`
INSERT INTO videoclub (cif, direccion, fecha_alta) 
VALUES 
('A12345678', 'Calle Mayor 100, Madrid', '2024-01-01'),
('B23456789', 'Avenida Sol 200, Barcelona', '2024-02-01');

-- INSERT para la tabla `alquileres`
INSERT INTO alquileres (dni_cliente, cod_articulo, fecha_alquiler, fecha_devolucion) 
VALUES 
('12345678A', 'P-001', '2025-04-01 10:00:00', NULL),
('98765432B', 'V-001', '2025-04-05 12:00:00', NULL);

-- select * from videoclub;

-- select * from articulo;

-- select * from pelicula;

-- select * from videojuego;

-- select * from persona;

-- select * from cliente;

-- select * from alquileres;