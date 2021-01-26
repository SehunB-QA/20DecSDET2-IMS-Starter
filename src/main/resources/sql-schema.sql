drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ims`.`items` (
  `id_items` INT NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(100) NULL,
  `item_price` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id_items`));

  CREATE TABLE `ims`.`orders` (
  `orders_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`orders_id`));

  CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
  `fk_customers_id` INT(11) NOT NULL,
  `fk_items` INT(11) NOT NULL,
  PRIMARY KEY (`fk_customers_id`, `fk_items`),
  INDEX `fk_order_items_customers1_idx` (`fk_customers_id` ASC) VISIBLE,
  INDEX `fk_order_items_items1_idx` (`fk_items` ASC) VISIBLE,
  CONSTRAINT `fk_order_items_customers1`
    FOREIGN KEY (`fk_customers_id`)
    REFERENCES `ims`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_items_items1`
    FOREIGN KEY (`fk_items`)
    REFERENCES `ims`.`items` (`id_items`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


