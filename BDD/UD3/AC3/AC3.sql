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

create table Conductor (
dni_nie varchar(9) primary key,
nombre varchar(40) not null,
apellido varchar(40) not null,
direccion varchar(40) not null
);

create table Carreras (
id_carrera int auto_increment primary key,
origen varchar(40)  not null,
destino varchar(40) not null,
precio decimal (4, 2) not null
);

create table Servicios(
id int auto_increment primary key,
matricula_taxi varchar(7),
dni_conductor varchar(9),
foreign key (matricula_taxi) references Taxi(matricula),
foreign key (dni_conductor) references Conductor(dni_nie)
);

insert into Taxi(matricula, marca, modelo, numero_de_pasajeros, clientes_descapacitados) values
('1234 CHB', 'Lexus', 'SC', '2', false),
('3243 HGF', 'BMW', 'x1', '1', false),
('1234 CHB', 'Lexus', 'SC', '2', false),
();


