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
id			int,
id_tipo		int auto_increment primary key,
nombre		varchar(40),
foreign key (id) references producto (id) on delete cascade
);

insert into producto (referencia, nombre, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto) value
('ref-01', 'Monitor', 'Monitor 144hz', '1', '7', '230.50', '0', '21', false);

-- Select * from producto;