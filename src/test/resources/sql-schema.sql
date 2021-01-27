DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `items`(
  `id_items` INT NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(100) NULL,
  `item_price` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id_items`)
  );


CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
    `fk_orders_id` INT(11),
    FOREIGN KEY (fk_orders_id) REFERENCES orders(orders_id),
    `fk_id_items` INT(11),
    FOREIGN KEY (fk_id_items) REFERENCES items(id_items)  
);


SELECT orders.orders_id, customers.first_name, customers.surname, items.item_name, items.item_price
FROM orders
INNER JOIN customers ON Orders.fk_customers_id = customers.id
INNER JOIN order_items ON Orders.orders_id = order_items.fk_orders_id
INNER JOIN items On order_items.fk_id_items = items.id_items; 