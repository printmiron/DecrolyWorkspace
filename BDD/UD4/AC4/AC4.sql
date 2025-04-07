




DELIMITER $$
CREATE TRIGGER check_age
BEFORE INSERT ON clientes
FOR EACH ROW
BEGIN
	IF NEW.age <0
		THEN SIGNAL SQLSTATE '50001' SET MESSAGE_TEXT = 'Error. La edad debe ser
	superior a 0';
	END IF;
END $$

