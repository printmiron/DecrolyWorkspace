DROP DATABASE IF EXISTS ud4_ac1;
CREATE DATABASE IF NOT EXISTS ud4_ac1;
USE ud4_ac1;

CREATE	TABLE	alumno	(
	nombre					VARCHAR	(40)	NOT	NULL,
	apellido				VARCHAR	(40)	NOT	NULL,
	f_nac	 				DATE			NOT NULL
);

INSERT	INTO	Alumno	(nombre, apellido, f_nac)	VALUES 
('Pavel', 'Miron', '2004-08-06'),
('Victor', 'Fernandez', '2005-12-05'),
('Pepe', 'Grande', '2002-04-26');

DROP	USER	'decroly'@'localhost';

CREATE	USER	'decroly'@'localhost'	IDENTIFIED	BY	'decroly';

GRANT	SELECT	ON	Alumno	TO	'decroly'@'localhost';

GRANT	UPDATE	(f_nac)	ON	Alumno	TO  'decroly'@'localhost';

use ud4_ac1; 
SELECT * FROM alumno; 
UPDATE alumno SET f_nac='1990-04-20' WHERE nombre='nombre del alumno'; 
UPDATE alumno SET apellido='Alonso' WHERE nombre='nombre del alumno'; 
INSERT INTO alumno VALUES ('Fernando', 'Alonso', '1981-07-29'); 


SHOW DATABASES;

