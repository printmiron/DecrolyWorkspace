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


