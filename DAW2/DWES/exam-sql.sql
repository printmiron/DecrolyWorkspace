create database if not exists exempleexam;
USE  exempleexam;

CREATE TABLE Santander (
id BIGINT AUTO_INCREMENT  PRIMARY KEY,
fecha date ,
max_temp decimal(4,2),
min_temp decimal(4,2),
media_temp decimal(4,2),
condicion ENUM('LLUVIA', 'SOLEADO', 'NUBLADO', 'TORMENTA', 'NIEVE', 'GRANIZO', 'GALERNA' ),
humedad int,
velocidad int
);