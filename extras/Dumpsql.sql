CREATE DATABASE  IF NOT EXISTS `db_products_parts` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_products_parts`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: db_products_parts
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `Prod_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`Prod_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'ASUS','X501U-XX039H 15.6 Laptop - White '),(2,'ADVENT','Monza T100 15.6 Laptop - Blue'),(3,'LENOVO','G580 15.6 Laptop - Blue'),(4,'TOSHIBA','Satellite C850D-11Q 15.6 Laptop'),(5,'Apple','MacBook Air 13');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parts`
--

DROP TABLE IF EXISTS `parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parts` (
  `Part_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Prod_ID` int(10) NOT NULL DEFAULT '0',
  `Name` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Cost` decimal(10,2) NOT NULL,
  PRIMARY KEY (`Part_ID`,`Prod_ID`),
  KEY `Prod_ID` (`Prod_ID`),
  CONSTRAINT `parts_ibfk_1` FOREIGN KEY (`Prod_ID`) REFERENCES `product` (`Prod_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parts`
--

LOCK TABLES `parts` WRITE;
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
INSERT INTO `parts` VALUES (1,1,'HardDrive','500GB Internal HardDrive',199.99),(2,2,'Adapter','Ac Power Cord Adapter',50.00),(3,1,'Platform','Windows 8 ultimate',299.99),(4,3,'Platform','Windows 8 Home Premium',199.49),(5,2,'Processor','AMD Accelerated Processing Unit (APU)',399.00),(6,5,'Camera','HD camera 720p FaceTime',224.99),(7,5,'Processor','I7 Quad Core Processor',525.00),(8,3,'Battery','Li-Ion 9 cell Battery',199.99),(9,4,'Graphics Card','AMD Radeon HD 7310 Graphics Up to 2995 MB',325.00),(10,4,'Ram','2 X DDR3 8 GB maximum expandable memory',200.00);
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_products_parts'
--

--
-- Dumping routines for database 'db_products_parts'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-02 12:15:34
