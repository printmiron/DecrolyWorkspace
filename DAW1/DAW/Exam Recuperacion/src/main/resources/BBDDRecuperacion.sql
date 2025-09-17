-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `valdecilladaw` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema valdecilladaw
-- -----------------------------------------------------
USE `valdecilladaw` ;

-- -----------------------------------------------------
-- Table `mydb`.`TipoConsulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `valdecilladaw`.`TipoConsulta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Tipo_UNIQUE` (`Tipo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `valdecilladaw`.`Paciente`(
  `dni` VARCHAR(10) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `valdecilladaw`.`Doctor` (
  `num_colegiado` VARCHAR(25) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  `Direccion` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(100) NOT NULL,
  `fecha_alta` BIGINT NOT NULL,
  `TipoConsulta_id` INT NOT NULL,
  PRIMARY KEY (`num_colegiado`, `TipoConsulta_id`),
  UNIQUE INDEX `dni_UNIQUE` (`num_colegiado` ASC) VISIBLE,
  INDEX `fk_Doctor_TipoConsulta_idx` (`TipoConsulta_id` ASC) VISIBLE,
  CONSTRAINT `fk_Doctor_TipoConsulta`
    FOREIGN KEY (`TipoConsulta_id`)
    REFERENCES `valdecilladaw`.`TipoConsulta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `valdecilladaw`.`Consulta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Observaciones` TEXT NOT NULL,
  `fecha` BIGINT NOT NULL,
  `TipoConsulta_id` INT NOT NULL,
  `Paciente_dni` VARCHAR(10) NOT NULL,
  `Doctor_num_colegiado` VARCHAR(25) NOT NULL,
  `Doctor_TipoConsulta_id` INT NOT NULL,
  PRIMARY KEY (`id`, `TipoConsulta_id`, `Paciente_dni`, `Doctor_num_colegiado`, `Doctor_TipoConsulta_id`),
  INDEX `fk_Consulta_TipoConsulta1_idx` (`TipoConsulta_id` ASC) VISIBLE,
  INDEX `fk_Consulta_Paciente1_idx` (`Paciente_dni` ASC) VISIBLE,
  INDEX `fk_Consulta_Doctor1_idx` (`Doctor_num_colegiado` ASC, `Doctor_TipoConsulta_id` ASC) VISIBLE,
  CONSTRAINT `fk_Consulta_TipoConsulta1`
    FOREIGN KEY (`TipoConsulta_id`)
    REFERENCES `valdecilladaw`.`TipoConsulta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consulta_Paciente1`
    FOREIGN KEY (`Paciente_dni`)
    REFERENCES `valdecilladaw`.`Paciente` (`dni`)
    ON DELETE NO ACTION	
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consulta_Doctor1`
    FOREIGN KEY (`Doctor_num_colegiado` , `Doctor_TipoConsulta_id`)
    REFERENCES `valdecilladaw`.`Doctor` (`num_colegiado` , `TipoConsulta_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



