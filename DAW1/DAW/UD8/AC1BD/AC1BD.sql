DROP DATABASE IF EXISTS schema_ac1;
CREATE DATABASE IF NOT EXISTS schema_ac1;
USE schema_ac1;

create table Tipo(
id_tipo		int auto_increment primary key,
nombre		varchar(40)
);

create	table	Producto (
id 			int auto_increment primary key,
referencia 	varchar(40),
nombre 		varchar(40),
descripcion varchar(40),
id_tipo		int,
cantidad 	int,
precio 		double,
descuento 	int,
iva 		int,
aplicarDto 	boolean,
foreign key (id_tipo) references Tipo (id_tipo) on delete cascade
);

-- Insertar registros en la tabla Tipo
INSERT INTO Tipo (nombre) VALUES
('Electrónica'),
('Ropa'),
('Alimentos'),
('Hogar'),
('Deportes');

-- Insertar registros en la tabla Producto
INSERT INTO Producto (referencia, nombre, descripcion, id_tipo, cantidad, precio, descuento, iva, aplicarDto) VALUES
('P001', 'Smartphone', 'Teléfono móvil ', 1, 50, 299.99, 10, 21, TRUE),
('P002', 'Camiseta', 'Camiseta de algodón', 2, 200, 15.50, 5, 21, FALSE),
('P003', 'Laptop', 'Laptop con procesador i7', 1, 30, 899.99, 15, 21, TRUE),
('P004', 'Arroz', 'Paquete de arroz ', 3, 500, 1.99, 0, 21, FALSE),
('P005', 'Bicicleta', 'Bicicleta de montaña de 18', 5, 25, 450.00, 20, 21, TRUE);

Select * from producto;