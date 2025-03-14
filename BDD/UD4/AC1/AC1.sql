DROP DATABASE IF EXISTS ud4_ac1;
CREATE DATABASE IF NOT EXISTS ud4_ac1;
USE ud4_ac1;

CREATE	TABLE	Alumno	(
	nombre					VARCHAR	(40)	NOT	NULL,
	apellido				VARCHAR	(40)	NOT	NULL,
	fecha_de_nacimiento	 	DATE			NOT NULL
);

