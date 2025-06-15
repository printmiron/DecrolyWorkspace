DROP DATABASE IF EXISTS vetdaw;
CREATE DATABASE IF NOT EXISTS vetdaw;
USE vetdaw;


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema VetDaw
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema VetDaw
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `VetDaw` DEFAULT CHARACTER SET utf8 ;
USE `VetDaw` ;

-- -----------------------------------------------------
-- Table `VetDaw`.`Propietario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VetDaw`.`Propietario` (
  `dni` VARCHAR(10) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(12) NOT NULL,
  `Direcion` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VetDaw`.`Tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VetDaw`.`Tipo` (
  `idTipo` INT NOT NULL AUTO_INCREMENT,
  `Tipo` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `idTipo_UNIQUE` (`idTipo` ASC) VISIBLE,
  UNIQUE INDEX `Tipo_UNIQUE` (`Tipo` ASC) VISIBLE,
  PRIMARY KEY (`idTipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VetDaw`.`Mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VetDaw`.`Mascota` (
  `Pasaporte` VARCHAR(9) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Peso` DOUBLE NOT NULL,
  `FechaNacimiento` DATETIME NOT NULL,
  `Propietario_dni` VARCHAR(10) NOT NULL,
  `Tipo_idTipo` INT NOT NULL,
  PRIMARY KEY (`Pasaporte`, `Propietario_dni`, `Tipo_idTipo`),
  UNIQUE INDEX `Pasaporte_UNIQUE` (`Pasaporte` ASC) VISIBLE,
  INDEX `fk_Mascota_Propietario_idx` (`Propietario_dni` ASC) VISIBLE,
  INDEX `fk_Mascota_Tipo1_idx` (`Tipo_idTipo` ASC) VISIBLE,
  CONSTRAINT `fk_Mascota_Propietario`
    FOREIGN KEY (`Propietario_dni`)
    REFERENCES `VetDaw`.`Propietario` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mascota_Tipo1`
    FOREIGN KEY (`Tipo_idTipo`)
    REFERENCES `VetDaw`.`Tipo` (`idTipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VetDaw`.`Consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VetDaw`.`Consulta` (
  `idConsulta` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATETIME NOT NULL,
  `Duracion` INT NOT NULL,
  `Observaciones` TEXT NULL,
  `Mascota_Pasaporte` VARCHAR(9) NOT NULL,
  `Mascota_Propietario_dni` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idConsulta`, `Mascota_Pasaporte`, `Mascota_Propietario_dni`),
  INDEX `fk_Consulta_Mascota1_idx` (`Mascota_Pasaporte` ASC, `Mascota_Propietario_dni` ASC) VISIBLE,
  CONSTRAINT `fk_Consulta_Mascota1`
    FOREIGN KEY (`Mascota_Pasaporte` , `Mascota_Propietario_dni`)
    REFERENCES `VetDaw`.`Mascota` (`Pasaporte` , `Propietario_dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;







-- Insertar 10 propietarios
INSERT INTO Propietario (dni, Nombre, Apellido, Telefono, Direcion, Email) VALUES
('12345678A', 'Juan', 'Pérez', '600123456', 'Calle Falsa 123', 'juan1@mail.com'),
('12345678B', 'Ana', 'Gómez', '600123457', 'Avenida Real 45', 'ana2@mail.com'),
('12345678C', 'Luis', 'Martín', '600123458', 'Calle Luna 12', 'luis3@mail.com'),
('12345678D', 'Marta', 'López', '600123459', 'Calle Sol 89', 'marta4@mail.com'),
('12345678E', 'Carlos', 'Ruiz', '600123460', 'Calle Norte 21', 'carlos5@mail.com'),
('12345678F', 'Lucía', 'Díaz', '600123461', 'Avenida Este 34', 'lucia6@mail.com'),
('12345678G', 'Pedro', 'Sánchez', '600123462', 'Calle Sur 56', 'pedro7@mail.com'),
('12345678H', 'Sara', 'Torres', '600123463', 'Avenida Oeste 78', 'sara8@mail.com'),
('12345678I', 'Jorge', 'Navarro', '600123464', 'Plaza Mayor 9', 'jorge9@mail.com'),
('12345678J', 'Elena', 'Ortega', '600123465', 'Camino Largo 67', 'elena10@mail.com');

-- Insertar 10 tipos
INSERT INTO Tipo (Tipo) VALUES
('Perro'),
('Gato'),
('Conejo'),
('Tortuga'),
('Hámster'),
('Loro'),
('Pez'),
('Hurón'),
('Iguana'),
('Serpiente');

-- Insertar 10 mascotas
INSERT INTO Mascota (Pasaporte, Nombre, Peso, FechaNacimiento, Propietario_dni, Tipo_idTipo) VALUES
('P00000001', 'Bobby', 12.5, '2020-01-15', '12345678A', 1),
('P00000002', 'Misu', 4.2, '2019-03-10', '12345678B', 2),
('P00000003', 'Pelusa', 1.1, '2021-07-22', '12345678C', 3),
('P00000004', 'Speedy', 0.8, '2018-09-05', '12345678D', 4),
('P00000005', 'Nube', 0.3, '2022-12-01', '12345678E', 5),
('P00000006', 'Paco', 0.9, '2017-06-18', '12345678F', 6),
('P00000007', 'Nemo', 0.2, '2023-01-25', '12345678G', 7),
('P00000008', 'Zuri', 1.5, '2020-11-11', '12345678H', 8),
('P00000009', 'Iggy', 2.3, '2021-05-09', '12345678I', 9),
('P00000010', 'Sly', 3.1, '2019-04-30', '12345678J', 10);

-- Insertar 10 consultas
INSERT INTO Consulta (Fecha, Duracion, Observaciones, Mascota_Pasaporte, Mascota_Propietario_dni) VALUES
('2024-01-10 10:00:00', 30, 'Chequeo general', 'P00000001', '12345678A'),
('2024-02-15 11:30:00', 20, 'Vacunación', 'P00000002', '12345678B'),
('2024-03-01 09:00:00', 40, 'Dolor abdominal', 'P00000003', '12345678C'),
('2024-03-12 14:15:00', 25, 'Revisión post-quirúrgica', 'P00000004', '12345678D'),
('2024-04-08 16:45:00', 15, 'Corte de uñas', 'P00000005', '12345678E'),
('2024-04-20 13:20:00', 30, 'Desparasitación', 'P00000006', '12345678F'),
('2024-05-05 10:50:00', 35, 'Consulta dental', 'P00000007', '12345678G'),
('2024-05-10 17:00:00', 20, 'Control peso', 'P00000008', '12345678H'),
('2024-05-15 12:00:00', 45, 'Análisis sangre', 'P00000009', '12345678I'),
('2024-05-25 15:10:00', 30, 'Chequeo anual', 'P00000010', '12345678J');