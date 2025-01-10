drop database ud3_ac1;
create database ud3_ac1;
use ud3_ac1;

create table Departamento(
id_departamento int auto_increment primary key,
nombre varchar(40),
ubicacion varchar(40)
);

create table Empleado( 
dni_nie varchar(9) primary key, 
nombre varchar(40), 
apellido varchar(40), 
fecha_de_nacimiento date, 
id_departamento int,
foreign key (id_departamento) references Departamento(id_departamento)
);

create table Coche(
matricula varchar(8) primary key,
marca varchar (20),
modelo varchar (40),
dni_nie varchar (9),
foreign key (dni_nie) references Empleado(dni_nie)
);

insert into Departamento (nombre, ubicacion)
values 	(1, 'DPO.mount', 'Edificio C / Bloque 1');

insert into Empleado (dni_nie, nombre, apellido, fecha_de_nacimiento, id_departamento)
values 	('Y7665863X', 'Pavel', 'Miron', '06-08-2004', 1),
		('C4567890C', 'Luis', 'Martínez', '1988-03-10', 1),
		('Y5678901D', 'Marta', 'Rodríguez', '1992-11-05', 1);


insert into Coche (matricula, marca, modelo)
values 	('ES12345', 'BMW', 'X6', 'Y7665863X'),
		('YZ67356', 'Toyota', 'Corola', 'Y5678901D');
        
update Empleado 
set nombre = 'David'
where dni_nie = 'C4567890C';


update Departamento
set nombre = 'IT'
where id_departamento = 1;

update Coche
set Marca = 'Seat', Modelo = 'Ibiza'
where matricula = 'ES12345';

delete from Empleado
where dni_nie = 'Y5678901D';

delete from Coche
where matricula = 'YZ67356';

delete from Coche;
delete from Empleado;
delete from Departamento;

        
