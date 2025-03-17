DROP DATABASE IF EXISTS ud4_ac1;
CREATE DATABASE IF NOT EXISTS ud4_ac1;
USE ud4_ac1;

CREATE	TABLE	Alumno	(
	nombre					VARCHAR	(40)	NOT	NULL,
	apellido				VARCHAR	(40)	NOT	NULL,
	fecha_de_nacimiento	 	DATE			NOT NULL
);

INSERT	INTO	Alumno	(nombre, apellido, fecha_de_nacimiento)	VALUES 
('Pavel', 'Miron', '2004-08-06'),
('Victor', 'Fernandez', '2005-12-05'),
('Pepe', 'Grande', '2002-04-26');


CREATE	USER	'decroly'@'localhost'	IDENTIFIED	BY	'decoly';
