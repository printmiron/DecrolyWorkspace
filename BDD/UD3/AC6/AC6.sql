DROP DATABASE IF EXISTS ud3_ac6;
CREATE DATABASE IF NOT EXISTS ud3_ac6;
USE ud3_ac6;

drop database actividad3;
create database actividad3;
use actividad3;

create table Robot_de_AL (
id_robot_almacenamiento int auto_increment primary key,
estado boolean,
ubicacion int not null,
capacidad_de_carga int,
eficiencia varchar(50),
energetica int
); 

create table Estanteria(
id_estanteria int auto_increment primary key,
ubicacion int not null,
capacidad_de_almacenamiento int,
nivel_de_uso int,
condicion varchar(50)
);

create table Producto(
id_producto int auto_increment primary key,
Nombre varchar(50) not null,
dimensiones int,
cantidad_en_inverntario int,
peso int
);

create table Pedido(
id_pedido int auto_increment primary key,
fecha_de_pedido time not null,
estado_de_pedido boolean,
cliente varchar(50),
fecha_de_entrega time,
estimada varchar(50)
);

create table Drone(
id_drone int auto_increment primary key,
estado boolean,
ubicacion int,
autonomia int,
capacidad_de_carga int
);

create table Robot_de_empaque (
id_robot_de_empaque int auto_increment primary key,
estado boolean,
ubicacion int,
capacidad_de_empaque int,
energetica int
);

create table Modulo_de_enegia (
id_modulo int auto_increment primary key,
capacidad int,
energetica int,
estado boolean,
ubicacion int
);

create table Personal_humano(
id_personal int auto_increment primary key,
DNI_NIE varchar(9),
nombre varchar(40),
apellido varchar(40),
rol varchar(50),
turno boolean,
especialidad varchar(50)
);

create table Robot_AL_Estanteria (
id_robot_al_estanteria int auto_increment primary key,
fecha_de_opracion time not null,
duarcion_de_la_operacion time not null,
id_robot_almacenamiento int,
id_estanteria int,
foreign key (id_robot_almacenamiento) references Robot_de_AL(id_robot_almacenamiento),
foreign key (id_estanteria) references Estanteria(id_estanteria)
);

create table Estanteria_Producto (
id_estanteria_producto int auto_increment primary key,
cantidad_almacenada int not null,
id_producto int,
id_estanteria int,
foreign key (id_producto) references Producto(id_producto),
foreign key (id_estanteria) references Estanteria(id_estanteria)
);

create table Producto_Pedido (
id_producto_pedido int auto_increment primary key,
cantidad_pedida int not null,
id_producto int,
id_pedido int,
foreign key (id_producto) references Producto(id_producto),
foreign key (id_pedido) references Pedido(id_pedido)
);

create table Pedido_Drone_Entrega(
id_pedido_drone int auto_increment primary key,
fecha_de_entrega time not null,
duracion_de_entrega time not null,
id_pedido int,
id_drone int,
foreign key (id_pedido) references Pedido(id_pedido),
foreign key (id_drone) references Drone(id_drone)
);

create table Robot_de_empaque_Pedido(
id_robot_de_empaque_pedido int auto_increment primary key,
fecha_de_empaque time not null,
tiempo_de_proceso time not null,
id_robot_de_empaque int,
id_pedido int,
foreign key (id_robot_de_empaque) references Robot_de_empaque(id_robot_de_empaque),
foreign key (id_pedido) references Pedido(id_pedido)
);

create table Modulo_de_enegia_Robot_AL_Empaque_Drone(
id_modulo_robot_AL_EM_DR int auto_increment primary key,
fecha_recarga time not null,
duracion_recarga time not null,
id_modulo int,
id_robot_almacenamiento int,
id_robot_de_empaque int,
id_drone int,
foreign key (id_modulo) references Modulo_de_enegia(id_modulo),
foreign key (id_robot_almacenamiento) references Robot_de_AL(id_robot_almacenamiento),
foreign key (id_robot_de_empaque) references Robot_de_empaque(id_robot_de_empaque),
foreign key (id_drone) references Drone(id_drone)
);

create table Personal_humano_Robot_AL_Empaque_Drone(
id_modulo_robot_AL_EM_DR int auto_increment primary key,
fecha_mantenimiento time not null,
descripcion_de_la_tarea varchar(100),
id_personal int,
id_robot_almacenamiento int,
id_robot_de_empaque int,
id_drone int,
foreign key (id_personal) references Personal_humano(id_personal),
foreign key (id_robot_almacenamiento) references Robot_de_AL(id_robot_almacenamiento),
foreign key (id_robot_de_empaque) references Robot_de_empaque(id_robot_de_empaque),
foreign key (id_drone) references Drone(id_drone)
);

-- Insertar datos en Robot_de_AL
INSERT INTO Robot_de_AL (estado, ubicacion, capacidad_de_carga, eficiencia, energetica) VALUES
(true, 101, 50, 'Alta', 90),
(false, 102, 60, 'Media', 80),
(true, 103, 70, 'Alta', 95);

-- Insertar datos en Estanteria
INSERT INTO Estanteria (ubicacion, capacidad_de_almacenamiento, nivel_de_uso, condicion) VALUES
(201, 200, 80, 'Buena'),
(202, 150, 60, 'Regular'),
(203, 180, 90, 'Excelente');

-- Insertar datos en Producto
INSERT INTO Producto (Nombre, dimensiones, cantidad_en_inverntario, peso) VALUES
('Producto A', 10, 100, 5),
('Producto B', 15, 150, 7),
('Producto C', 20, 200, 10);

-- Insertar datos en Pedido
INSERT INTO Pedido (fecha_de_pedido, estado_de_pedido, cliente, fecha_de_entrega, estimada) VALUES
('08:30:00', true, 'Cliente 1', '10:30:00', '2 horas'),
('09:00:00', false, 'Cliente 2', '11:00:00', '2 horas'),
('10:15:00', true, 'Cliente 3', '12:15:00', '2 horas');

-- Insertar datos en Drone
INSERT INTO Drone (estado, ubicacion, autonomia, capacidad_de_carga) VALUES
(true, 301, 120, 20),
(false, 302, 100, 15),
(true, 303, 140, 25);

-- Insertar datos en Robot_de_empaque
INSERT INTO Robot_de_empaque (estado, ubicacion, capacidad_de_empaque, energetica) VALUES
(true, 401, 30, 85),
(false, 402, 25, 80),
(true, 403, 35, 90);

-- Insertar datos en Modulo_de_enegia
INSERT INTO Modulo_de_enegia (capacidad, energetica, estado, ubicacion) VALUES
(500, 95, true, 501),
(400, 90, false, 502),
(450, 92, true, 503);

-- Insertar datos en Personal_humano
INSERT INTO Personal_humano (DNI_NIE, nombre, apellido, rol, turno, especialidad) VALUES
('12345678A', 'Juan', 'Perez', 'Técnico', true, 'Mantenimiento'),
('87654321B', 'Ana', 'Gomez', 'Operario', false, 'Logística'),
('56781234C', 'Luis', 'Martínez', 'Supervisor', true, 'Supervisión');

-- Insertar datos en Robot_AL_Estanteria
INSERT INTO Robot_AL_Estanteria (fecha_de_opracion, duarcion_de_la_operacion, id_robot_almacenamiento, id_estanteria) VALUES
('08:45:00', '00:30:00', 1, 1),
('09:15:00', '00:25:00', 2, 2),
('10:00:00', '00:35:00', 3, 3);

-- Insertar datos en Estanteria_Producto
INSERT INTO Estanteria_Producto (cantidad_almacenada, id_producto, id_estanteria) VALUES
(50, 1, 1),
(75, 2, 2),
(100, 3, 3);

-- Insertar datos en Producto_Pedido
INSERT INTO Producto_Pedido (cantidad_pedida, id_producto, id_pedido) VALUES
(10, 1, 1),
(20, 2, 2),
(30, 3, 3);

-- Insertar datos en Pedido_Drone_Entrega
INSERT INTO Pedido_Drone_Entrega (fecha_de_entrega, duracion_de_entrega, id_pedido, id_drone) VALUES
('10:45:00', '00:15:00', 1, 1),
('11:30:00', '00:30:00', 2, 2),
('12:45:00', '00:20:00', 3, 3);

-- Insertar datos en Robot_de_empaque_Pedido
INSERT INTO Robot_de_empaque_Pedido (fecha_de_empaque, tiempo_de_proceso, id_robot_de_empaque, id_pedido) VALUES
('08:50:00', '00:10:00', 1, 1),
('09:20:00', '00:15:00', 2, 2),
('10:30:00', '00:20:00', 3, 3);

-- Insertar datos en Modulo_de_enegia_Robot_AL_Empaque_Drone
INSERT INTO Modulo_de_enegia_Robot_AL_Empaque_Drone (fecha_recarga, duracion_recarga, id_modulo, id_robot_almacenamiento, id_robot_de_empaque, id_drone) VALUES
('07:00:00', '00:40:00', 1, 1, 1, 1),
('08:00:00', '00:35:00', 2, 2, 2, 2),
('09:30:00', '00:45:00', 3, 3, 3, 3);

-- Insertar datos en Personal_humano_Robot_AL_Empaque_Drone
INSERT INTO Personal_humano_Robot_AL_Empaque_Drone (fecha_mantenimiento, descripcion_de_la_tarea, id_personal, id_robot_almacenamiento, id_robot_de_empaque, id_drone) VALUES
('07:30:00', 'Revisión general', 1, 1, 1, 1),
('08:30:00', 'Reparación de sistema', 2, 2, 2, 2),
('09:45:00', 'Mantenimiento preventivo', 3, 3, 3, 3);

-- Actualizar eficiencia de un Robot de Almacenamiento
UPDATE Robot_de_AL 
SET eficiencia = 'Muy Alta' 
WHERE id_robot_almacenamiento = 1;

-- Actualizar estado de un Drone
UPDATE Drone 
SET estado = true 
WHERE id_drone = 2;

-- Aumentar la cantidad de un producto en inventario
UPDATE Producto 
SET cantidad_en_inverntario = cantidad_en_inverntario + 50 
WHERE id_producto = 1;

-- Modificar la capacidad de una estantería
UPDATE Estanteria 
SET capacidad_de_almacenamiento = 220 
WHERE id_estanteria = 3;

-- Cambiar el estado de un pedido a entregado
UPDATE Pedido 
SET estado_de_pedido = true 
WHERE id_pedido = 2;

-- Ajustar la capacidad de carga de un Robot de Empaque
UPDATE Robot_de_empaque 
SET capacidad_de_empaque = 40 
WHERE id_robot_de_empaque = 3;

-- Modificar la autonomía de un Drone
UPDATE Drone 
SET autonomia = 160 
WHERE id_drone = 1;

-- Cambiar el turno de un empleado
UPDATE Personal_humano 
SET turno = false 
WHERE id_personal = 3;

-- Reducir el nivel de uso de una estantería
UPDATE Estanteria 
SET nivel_de_uso = 70 
WHERE id_estanteria = 2;

-- Modificar la fecha estimada de entrega de un pedido
UPDATE Pedido 
SET estimada = '3 horas' 
WHERE id_pedido = 1;

-- Consultar los productos en una estanteria especifica
SELECT Producto.Nombre, Estanteria.ubicacion 
FROM Estanteria_Producto
JOIN Producto ON Estanteria_Producto.id_producto = Producto.id_producto
JOIN Estanteria ON Estanteria_Producto.id_estanteria = Estanteria.id_estanteria
WHERE Estanteria.id_estanteria = 2;

-- Listar los pedidos con sus productos asociados
SELECT Pedido.id_pedido, Pedido.cliente, Producto.Nombre, Producto_Pedido.cantidad_pedida 
FROM Pedido
JOIN Producto_Pedido ON Pedido.id_pedido = Producto_Pedido.id_pedido
JOIN Producto ON Producto_Pedido.id_producto = Producto.id_producto;

-- Obtener la cantidad de productos almacenados en cada estantería
SELECT Estanteria.id_estanteria, SUM(Estanteria_Producto.cantidad_almacenada) AS total_almacenado 
FROM Estanteria_Producto
JOIN Estanteria ON Estanteria_Producto.id_estanteria = Estanteria.id_estanteria
GROUP BY Estanteria.id_estanteria;

-- Consultar los robots de empaque con su ubicación y estado
SELECT id_robot_de_empaque, ubicacion, estado 
FROM Robot_de_empaque;

-- Obtener el tiempo total de operacion de cada Robot de Almacenamiento
SELECT id_robot_almacenamiento, SUM(TIME_TO_SEC(duarcion_de_la_operacion)) / 60 AS minutos_totales 
FROM Robot_AL_Estanteria
GROUP BY id_robot_almacenamiento;
