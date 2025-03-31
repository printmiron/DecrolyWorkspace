DROP DATABASE IF EXISTS dawbd;
CREATE DATABASE IF NOT EXISTS dawbd;
USE dawbd;

create	table	producto (
id 			int auto_increment primary key,
referencia 	varchar(40),
nombre 		varchar(40),
descripcion varchar(40),
tipo 		int,
cantidad 	int,
precio 		double,
descuento 	int,
iva 		int,
aplicarDto 	boolean
);

create table tipo(
id		int auto_increment primary key,
nombre	varchar(40)
);

-- insert into producto (referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto) value
-- ('ref-01', 'Monitor', 'Monitor 144hz', '1', '7', '230.50', '0', '21', false);

Select * from producto;