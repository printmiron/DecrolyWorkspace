-- Crear base de datos
CREATE DATABASE IF NOT EXISTS empleadosdb
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
USE empleadosdb;
-- Crear tabla employees
CREATE TABLE IF NOT EXISTS employees (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL,
salario DOUBLE
);
-- Datos de prueba (opcional)
INSERT INTO employees (nombre, email, salario) VALUES
('Ana Pérez', 'ana.perez@empresa.com', 25000),
('Juan López', 'juan.lopez@empresa.com', 28000),
('María García', 'maria.garcia@empresa.com', 30000);