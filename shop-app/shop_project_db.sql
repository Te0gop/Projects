-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema shop_project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shop_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shop_project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `shop_project` ;

-- -----------------------------------------------------
-- Table `shop_project`.`shop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop_project`.`shop` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `shop_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shop_project`.`drinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop_project`.`drinks` (
  `id` BIGINT NOT NULL,
  `alcohol_content` DOUBLE NOT NULL,
  `manifacture_name` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DECIMAL(19,2) NULL DEFAULT NULL,
  `shop_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcdsdpt4vcaxyjk3mnv6ae5jev` (`shop_id` ASC) VISIBLE,
  CONSTRAINT `FKcdsdpt4vcaxyjk3mnv6ae5jev`
    FOREIGN KEY (`shop_id`)
    REFERENCES `shop_project`.`shop` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shop_project`.`foods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop_project`.`foods` (
  `id` BIGINT NOT NULL,
  `expiry_date` VARCHAR(255) NULL DEFAULT NULL,
  `manifacture_name` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` DECIMAL(19,2) NULL DEFAULT NULL,
  `shop_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK7kw6qd5n93cp9me9fadoc04rh` (`shop_id` ASC) VISIBLE,
  CONSTRAINT `FK7kw6qd5n93cp9me9fadoc04rh`
    FOREIGN KEY (`shop_id`)
    REFERENCES `shop_project`.`shop` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shop_project`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop_project`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shop_project`.`shop_drinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop_project`.`shop_drinks` (
  `shop_id` BIGINT NOT NULL,
  `drinks_id` BIGINT NOT NULL,
  UNIQUE INDEX `UK_rfk9gjdgtp2bfa1yhpbh2s0o4` (`drinks_id` ASC) VISIBLE,
  INDEX `FKad8oau71sirj4nd7glgy9kw0k` (`shop_id` ASC) VISIBLE,
  CONSTRAINT `FKad8oau71sirj4nd7glgy9kw0k`
    FOREIGN KEY (`shop_id`)
    REFERENCES `shop_project`.`shop` (`id`),
  CONSTRAINT `FKj6e26dm3fubkrcgomtinibssv`
    FOREIGN KEY (`drinks_id`)
    REFERENCES `shop_project`.`drinks` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `shop_project`.`shop_foods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop_project`.`shop_foods` (
  `shop_id` BIGINT NOT NULL,
  `foods_id` BIGINT NOT NULL,
  UNIQUE INDEX `UK_4yymn6p3cfu37epthlqkcq7va` (`foods_id` ASC) VISIBLE,
  INDEX `FK4dg8si9mk9r8m5sr5xoahrefg` (`shop_id` ASC) VISIBLE,
  CONSTRAINT `FK4dg8si9mk9r8m5sr5xoahrefg`
    FOREIGN KEY (`shop_id`)
    REFERENCES `shop_project`.`shop` (`id`),
  CONSTRAINT `FKj83t5liewl8h3onodp77mhh46`
    FOREIGN KEY (`foods_id`)
    REFERENCES `shop_project`.`foods` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;