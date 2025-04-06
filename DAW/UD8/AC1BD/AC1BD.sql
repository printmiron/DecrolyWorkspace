DROP DATABASE IF EXISTS schema_ac1;
CREATE DATABASE IF NOT EXISTS schema_ac1;
USE schema_ac1;

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
id_tipo		int auto_increment primary key,
nombre		varchar(40),
id			int,
foreign key (id) references producto (id) on delete cascade
);


-- Select * from producto;