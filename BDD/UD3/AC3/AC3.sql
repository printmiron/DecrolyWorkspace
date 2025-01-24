DROP DATABASE IF EXISTS ud3_ac3;
CREATE DATABASE IF NOT EXISTS ud3_ac3;
USE ud3_ac3;

create table Taxi (
matricula varchar(7) primary key,
marca varchar(40) not null,
modelo varchar(40) not null,
numero_de_pasajeros int not null,
clientes_descapacitados boolean not null
);

create table Carreras (
id_carrera int auto_increment primary key,
origen varchar(40)  not null,
destino varchar(40) not null,
precio decimal (4, 2) not null,
turno_noche boolean not null
);

create table Conductor (
dni_nie varchar(9) primary key,
nombre varchar(40) not null,
apellido varchar(40) not null,
direccion varchar(40) not null
);

create table Carrera_Taxi_Conductor (
id_carrera int,
matricula_taxi varchar(8),
dni_nie_conductor varchar(9),
primary key (id_carrera, matricula_taxi, dni_nie_conductor),
foreign key (id_carrera) references Carreras(id_carrera),
foreign key (matricula_taxi) references Taxi(matricula),
foreign key (dni_nie_conductor) references Conductor(dni_nie)
);


insert into Taxi (matricula, marca, modelo, numero_de_pasajeros, clientes_descapacitados) values
('1234CHB', 'Lexus', 'SC500', '2', false),
('3243HGF', 'BMW', 'x1', '1', true),
('2345CKD', 'Honda', 'Civic', '3', false),
('2378FHG', 'Skoda', 'Octavia', '2', true),
('7069DLV', 'Mercedes', 'CLS', '4', false);

insert into Carreras (id_carrera, origen, destino, precio, turno_noche) values
(1, 'Madrid', 'Valencia', '50.00', false),
(2, 'Santander', 'Bilbao', '44.20', false),
(3, 'Malaga', 'Sevilla', '77.50', true),
(4, 'Madrid', 'Barcelona', '83.00', false),
(5, 'Barcelona', 'Burgos', '54.00', false),
(6, 'Murcia', 'Valencia', '66.70', true),
(7, 'Sevilla', 'Bilbao', '36.10', false),
(8, 'Valencia', 'Zaragoza', '43.00', false);

insert into Conductor (dni_nie, nombre, apellido, direccion) values
('11111111H', 'Pavel', 'Miron', 'Calle del Mar'),
('22222222J', 'Laura', 'Martínez García', 'Calle de la Luna'),
('33333333P', 'Daniel', 'López', 'Avenida de los Olivos'),
('44444444A', 'Elena', 'Sánchez', 'Plaza Mayor'),
('55555555B', 'Javier', 'Torres', ' Calle del Sol');

insert into Carrera_Taxi_Conductor (id_carrera, matricula_taxi, dni_nie_conductor) values
(1, '3243HGF', '11111111H'),
(2, '2345CKD', '22222222J'),
(3, '2345CKD', '22222222J'),
(4, '2378FHG', '33333333P'), 
(5, '2378FHG', '33333333P'), 
(6, '7069DLV', '44444444A'), 
(7, '7069DLV', '44444444A'), 
(8, '7069DLV', '55555555B');

-- 1. Muestra la matrícula, marca, modelo, DNI/NIE y nombre de todos los coches que tienen
-- asignado un conductor (y los datos de los conductores).

select matricula, marca, modelo, dni_nie, nombre
from Taxi
join Carrera_Taxi_Conductor on matricula = matricula.taxi
join Conductor on dni_nie_conductor = dni_nie

-- 2. Muestra el origen y destino de todos los servicios así como la matrícula del taxi que se
-- utilizó y si estaba adaptado para clientes discapacitados.

select origen, destino, matricula, clientes_descapacitados
from Carreras
join 

-- 3. Lista todos los nombres de conductores y DNI/NIE y sus respectivos coches (marca,
-- modelo, matrícula y número de pasajeros).

select

-- 4. Enumera todos los detalles de todos los taxis y todos los conductores.

-- 5. Muestra todos los detalles de todos los servicios y todos los taxis.
