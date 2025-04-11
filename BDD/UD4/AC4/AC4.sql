DROP DATABASE IF EXISTS ud4_ac4;
CREATE DATABASE IF NOT EXISTS ud4_ac4;
USE ud4_ac4;


create table clientes(
	id			int auto_increment primary key,
	first_name 	VARCHAR(40),
    last_name	VARCHAR(40),
    age 		int
);
-- 1
DELIMITER $$
CREATE TRIGGER check_age
BEFORE INSERT ON clientes
FOR EACH ROW
BEGIN
	IF NEW.age <16
		THEN SIGNAL SQLSTATE '50001' SET MESSAGE_TEXT = 'Error. La edad debe ser
	superior a 16';
	END IF;
END $$

-- 2
DELIMITER $$
CREATE TRIGGER check_name
BEFORE INSERT ON clientes
FOR EACH ROW
BEGIN
	IF NEW.first_name ='Lucia' or 'Martin' 
		THEN SIGNAL SQLSTATE '50001' SET MESSAGE_TEXT = 'Error.';
	END IF;
END $$
