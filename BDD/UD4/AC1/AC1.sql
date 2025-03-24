DROP DATABASE IF EXISTS ud4_ac1;
CREATE DATABASE IF NOT EXISTS ud4_ac1;
USE ud4_ac1;

CREATE	TABLE	alumno	(
	nombre					VARCHAR	(40)	NOT	NULL,
	apellido				VARCHAR	(40)	NOT	NULL,
	f_nac	 				DATE			NOT NULL
);

INSERT	INTO	alumno	(nombre, apellido, f_nac)	VALUES 
('Pavel', 'Miron', '2004-08-06'),
('Victor', 'Fernandez', '2005-12-05'),
('Pepe', 'Grande', '2002-04-26'),
('Fernando', 'Alonso', '1981-07-29'); 

-- GRANT ALL PRIVILEGES ON ud4_ac1.* TO 'usuarios';

-- REVOKE usuarios FROM 'alumno'@'localhost';


-- CREATE ROLE usuarios;

-- GRANT usuarios TO 'decroly'@'localhost';
-- GRANT usuarios TO 'alumno'@'localhost';

-- GRANT ALL PRIVILEGES ON ud4_ac1. * TO 'decroly'@'localhost';

-- DROP	USER	'alumno'@'localhost';

-- CREATE	USER	'alumno'@'localhost'	IDENTIFIED	BY	'alumno';

-- DROP	USER	'decroly'@'localhost';

-- CREATE	USER	'decroly'@'localhost'	IDENTIFIED	BY	'decroly';

-- GRANT	SELECT	ON	alumno	TO	'decroly'@'localhost';

-- GRANT	UPDATE	(f_nac)	ON	alumno	TO  'decroly'@'localhost';

use ud4_ac1; 

UPDATE alumno SET f_nac='1990-04-20' WHERE nombre='Fernando'; 
UPDATE alumno SET apellido='Alonso' WHERE nombre='Fernando'; 


SELECT * FROM alumno; 

-- SHOW DATABASES;

