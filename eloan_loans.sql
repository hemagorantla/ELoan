-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: eloan
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans` (
  `loanname` varchar(16) NOT NULL,
  `loanamount` int(10) NOT NULL,
  `businessstruture` varchar(32) NOT NULL,
  `loan_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `taxindiacator` varchar(32) DEFAULT NULL,
  `loanid` int(10) NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) NOT NULL,
  `address2` varchar(255) NOT NULL,
  `address3` varchar(255) NOT NULL,
  `id` int(11) DEFAULT NULL,
  `loanstatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`loanid`),
  KEY `fk_category` (`id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`id`) REFERENCES `users` (`Id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES ('homelaon',10000,'indi','2020-11-06 10:53:11','yes',1,'a1','a2','a3',6,'Aproved'),('homelaon',10000,'indi','2020-11-06 10:55:24','yes',2,'a1','a2','a3',6,'Rejected'),('homelaon',10000,'indi','2020-11-06 10:57:57','yes',3,'a1','a2','a3',6,'Aproved'),('homelaon',10000,'indi','2020-11-06 11:05:51','yes',4,'a1','a2','a3',6,NULL),('homelaon',10000,'indi','2020-11-06 11:14:13','yes',5,'a1','a2','a3',6,NULL),('homelaon',10000,'indi','2020-11-06 11:23:55','yes',6,'a1','a2','a3',6,NULL),('homelaon1',10000,'indi','2020-11-06 11:29:33','yes',7,'a1','a2','a3',6,NULL),('homelaon1',100,'indi','2020-11-06 11:30:23','yes',8,'a1a2a3','null','null',6,NULL),('loan1',100,'Individual','2020-11-07 08:52:54','yes',9,'aa1','aa2','aa3',6,NULL),('loan10updated',1000000,'indivisula','2020-11-07 14:44:45','yes',10,'aa1aa2aa3','null','null',9,NULL),('loan',1000000,'indivisula','2020-11-07 14:44:56','yes',11,'aa1aa2aa3','null','null',9,NULL),('mortgage',50000,'yre','2020-11-07 17:28:35','yes',12,'dshd','h','hh',9,NULL),('mortgage',50000,'yre','2020-11-07 17:28:50','yes',13,'dshdhhh','null','null',9,NULL),('mortgage',50000,'yre','2020-11-07 17:28:53','yes',14,'dshdhhh','null','null',9,NULL);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-09 12:12:59
