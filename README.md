Test Coverage: 37.4%

# IMS Assignment - Sehun Babatunde

A CLI inventory management system that deals with managing and CRUD functionality of customers, items and orders which records are stored in  MySQL database.

## Getting Started

Make sure the prerequisites are installed, ensure environment variables for Java & Maven are set ups correctly.

### Prerequisites

```
Java 11 - Run the program
MySQL - For the database and crud functions
Git - Clone repo down 
Apache Mave
n - Run Unit tests
```
### Installing

 First you must clone this repo  to your computer  using the git clone command  :

```
git clone https://github.com/SehunB-QA/20DecSDET2-IMS-Starter.git
```


Using the same git terminal navigate to the Fat_Jar_File foler and execute the following command: 

```
java -jar ims-0.0.1-jar-with-dependencies.jar

```

## Running the tests

Once your repo is cloned wih the : 

```
git clone https://github.com/SehunB-QA/20DecSDET2-IMS-Starter.git
```

Using a command line interface, navigate to the new cloned repo with your terminal/ Command Line interface  and you can run maven tests with the command:

```
mvn test
```
### Unit Tests 

Unit Tests were conducted with JUnit & maven to test intended behaviour of CRUD function of the data acess objects.

### Test Deleting a customer without orders

```java
 @Test
    public void testDeleteCustomerWithoutOrders() {
        assertEquals(1, DAO.deleteCustomerWithoutOrders(1));
    }
```
## Deployment

First you need a MySql instance  set up , then you can reconstruct the IMS database structure using the follow SQL statement.

```sql
DROP SCHEMA ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) DEFAULT NULL,
  `surname` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
  `id_items` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) DEFAULT NULL,
  `item_price` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`id_items`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `fk_customers_id` int NOT NULL,
  PRIMARY KEY (`orders_id`,`fk_customers_id`),
  KEY `fk_orders_customers_idx` (`fk_customers_id`),
  CONSTRAINT `fk_orders_customers` FOREIGN KEY (`fk_customers_id`) REFERENCES `customers` (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
  `fk_orders_id` int DEFAULT NULL,
  `fk_id_items` int DEFAULT NULL,
  KEY `fk_orders_id` (`fk_orders_id`),
  KEY `fk_id_items` (`fk_id_items`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`fk_orders_id`) REFERENCES `orders` (`orders_id`),
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`fk_id_items`) REFERENCES `items` (`id_items`)
);
```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Nick Johnson**  - [nickrstewarttds](https://github.com/nickrstewarttds/)
* **Sehun Babatunde**  - [SehunB-QA](https://github.com/SehunB-QA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* A big thank you to my team mates Cameron, Raimonds, Henry, Melike for all help and support during the development of this project. A thank you to Nick, Pawel and my QA Trainers for teaching, help and support and memes.


