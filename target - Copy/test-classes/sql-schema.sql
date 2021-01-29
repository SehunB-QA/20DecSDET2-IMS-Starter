DROP TABLE orders;
DROP  TABLE customers;
DROP  TABLE items;


CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `items`(
  `id_items` INT NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(100) NULL,
  `item_price` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id_items`)
  );
  
  
  CREATE TABLE `orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `fk_customers_id` int NOT NULL,
  PRIMARY KEY (`orders_id`,`fk_customers_id`),
  
  CONSTRAINT `fk_orders_customers` FOREIGN KEY (`fk_customers_id`) REFERENCES `customers` (`id`)
  );

  
   