-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dcuniverse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dcuniverse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dcuniverse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dcuniverse` ;

-- -----------------------------------------------------
-- Table `dcuniverse`.`characters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dcuniverse`.`characters` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `heroName` VARCHAR(50) NOT NULL,
  `fullName` VARCHAR(100) NULL DEFAULT NULL,
  `image1` TEXT NULL DEFAULT NULL,
  `image2` TEXT NULL DEFAULT NULL,
  `image3` TEXT NULL DEFAULT NULL,
  `gender` VARCHAR(20) NULL DEFAULT NULL,
  `race` VARCHAR(50) NULL DEFAULT NULL,
  `alignment` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dcuniverse`.`powerstats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dcuniverse`.`powerstats` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `intelligence` DOUBLE NOT NULL,
  `strength` DOUBLE NOT NULL,
  `speed` DOUBLE NOT NULL,
  `durability` DOUBLE NOT NULL,
  `power` DOUBLE NOT NULL,
  `combat` DOUBLE NOT NULL,
  `characters_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `characters_id`),
  UNIQUE INDEX `id` (`id` ASC) VISIBLE,
  INDEX `fk_powerstats_characters_idx` (`characters_id` ASC) VISIBLE,
  CONSTRAINT `fk_powerstats_characters`
    FOREIGN KEY (`characters_id`)
    REFERENCES `dcuniverse`.`characters` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- Insertar los datos en la tabla characters
INSERT INTO characters (heroName, fullName, image1, image2, image3, gender, race, alignment) VALUES
    ('Superman', 'Clark Kent', 'https://www.superherodb.com/pictures2/portraits/10/100/791.jpg', 'https://www.superherodb.com/pictures2/portraits/10/100/789.jpg', 'https://fydn.imgix.net/m%2Fgen%2Fart-print-std-portrait-p1%2F395a58c6-59ca-4f56-8200-379bc75d74d1.jpg','Male', 'Kryptonian', 'good'),
    ('Batman', 'Bruce Wayne', 'https://www.superherodb.com/pictures2/portraits/10/100/639.jpg', 'https://www.superherodb.com/pictures2/portraits/10/100/10441.jpg', 'https://www.superherodb.com/pictures2/portraits/10/100/1496.jpg', 'Male', 'Human', 'good'),
    ('Wonder Woman', 'Diana Prince', 'https://www.superherodb.com/pictures2/portraits/10/100/791.jpg', 'https://www.superherodb.com/pictures2/portraits/10/100/1497.jpg', 'https://i.pinimg.com/736x/53/05/15/53051516aa21eacde1eb7277fd698833.jpg', 'Female', 'Amazon', 'good'),
    ('Flash', 'Barry Allen', 'https://www.superherodb.com/pictures2/portraits/10/100/892.jpg', 'https://www.superherodb.com/pictures2/portraits/10/100/690.jpg', 'https://www.superherodb.com/pictures2/portraits/10/100/893.jpg', 'Male', 'Human', 'good'),
    ('Darkseid', 'Uxas', 'https://www.superherodb.com/pictures2/portraits/10/100/668.jpg', 'https://i.pinimg.com/736x/35/9a/27/359a2721f9c886ee5c30d83b633af4ba.jpg', 'https://i.ytimg.com/vi/Dp9Y1Ox5nGY/sddefault.jpg', 'Male', 'New God', 'bad'),
    ('Brainiac', 'Vril Dox', 'https://www.superherodb.com/pictures2/portraits/10/100/648.jpg', 'https://i.ytimg.com/vi/wrOrt-K4UgU/sddefault.jpg', 'https://static.wikia.nocookie.net/villainstournament/images/2/28/Brainiac.jpg', 'Male', 'Android', 'bad'),
    ('Black Adam', 'Teth-Adam', 'https://www.superherodb.com/pictures2/portraits/10/100/643.jpg', 'https://static.independent.co.uk/2022/10/18/17/rev-1-BAD-T2-009r_High_Res_JPEG.jpeg', 'https://imagenes.gatotv.com/categorias/peliculas/grande/black_adam.jpg', 'Male', 'Android', 'bad'),
    ('Joker', 'Jack Napier', 'https://www.superherodb.com/pictures2/portraits/10/100/719.jpg', 'https://urosario.edu.co/sites/default/files/2023-05/images.jpg', 'https://i.pinimg.com/originals/29/fc/c5/29fcc5bfe83093081261839fcce604a5.png', 'Male', 'Human', 'bad');
    
    INSERT INTO powerstats (intelligence, strength, speed, durability, power, combat, characters_id) VALUES (94, 100, 100, 100, 100, 85, 1), (95, 40, 29, 55, 63, 90, 2),
    (75, 100, 53, 90, 64, 64, 3), (63, 10, 100, 50, 68, 32, 4), (88, 100, 83, 100, 100, 95, 5),
    (100, 95, 63, 90, 60, 75, 6), (88, 100, 92, 100, 100, 56, 7), (95, 10, 12, 60, 43, 70, 8);
