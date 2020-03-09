-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cartID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `productID` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cartID`),
  KEY `FK_cart_products_idx` (`productID`),
  KEY `FK_cart_users_idx` (`userID`),
  CONSTRAINT `FK_cart_products` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cart_users` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (47,4,41,3);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices` (
  `priceID` int NOT NULL AUTO_INCREMENT,
  `productID` int NOT NULL,
  `price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `isDiscounted` tinyint NOT NULL DEFAULT '0',
  `discountedPrice` double DEFAULT NULL,
  `currentDate` date DEFAULT NULL,
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  PRIMARY KEY (`priceID`),
  KEY `FK_prices_products_idx` (`productID`),
  CONSTRAINT `FK_prices_products` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES (17,38,398.99,NULL,0,NULL,NULL,NULL,NULL),(18,39,168.99,NULL,0,NULL,NULL,NULL,NULL),(19,40,1999.98,NULL,0,NULL,NULL,NULL,NULL),(20,37,1314,NULL,0,NULL,NULL,NULL,NULL),(21,41,1349,NULL,0,NULL,NULL,NULL,NULL),(24,46,799,NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `template` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (37,'Iphone 11 Pro','Smartphone','Apple','Super Retina XDR. Tripla fotocamera. Un giorno di autonomia. Chip A13 Bionic. Nuovo ultra-grandangolo. Vetro più resistente. Arriva lo slofie. Quattro colori.','iphone11pro.jpg'),(38,'Mi 9T Pro','Smartphone','Xiaomi','Smartphone da 6.39 FHD+, Snapdragon 855, Tripla Fotocamera post.13 + 48 + 8MP, Fotocamera ant. Pop up selfie da 20MP, 4000 mAh, con NFC, 6GB RAM+128GB Memoria Interna','Xiaomi9Tpro.jpg'),(39,'Redmi Note 8','Smartphone','Xiaomi','Xiaomi Redmi Note 8 Smartphone, 4GB 64GB Mobilephone,Schermo Intero Da 6,3,Processore Octa Core Snapdragon 665,Quad Camera(48MP + 8MP + 2MP + 2MP) Versione Globale (Bianco)','xioamiRedmiNote8.jpg'),(40,'Alienware 15 R4','Laptop','Dell','L\'Alienware 15 R4 è un portatile da gioco spesso da 15,6\'\' che può essere configurato con un processore Intel Core i9-8950HK, GPU NVIDIA GeForce GTX 1070 e display Full HD.','alienware15R4.jpg'),(41,'Mavic 2 Pro','Drone','DJI','DJI Mavic 2 Pro con Drone Quadcopter, Videocamera Hasselblad L1D-20C da 20MP con Sensore CMOS da 1 pollice, Vola Fino a 31 Minuti a Una Velocità Massima di 72 km/h, Grigio','DJIMavic2Pro.jpg'),(46,'Ipad pro 11','Tablet','Apple','Apple iPad Pro Wi-Fi 64GB Grigio Siderale Tablet 11\'\' Wi-Fi con processore Chip A12X Bionic con architettura a 64 bit','ipadpro11.jpg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `phone` varchar(45) NOT NULL,
  `token` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'pippo.franco@gmail.com','0b1041f4430205fbe7a2f38f78de3364','Pippo','Franco','1960-10-10','3466666666','6NDjbhubS0P7T4FBhNnv5t74GjPjOuuDOma5CE3h8Uh6n'),(4,'jdoe@gmail.com','6e6bc4e49dd477ebc98ef4046c067b5f','john','doe','1993-06-09','+393359812341','be8BruddLxM1yRpRBDBTvC2CRdy5Ae8S9oP8i8fAOM3FU'),(5,'fede@gmail.com','6e6bc4e49dd477ebc98ef4046c067b5f','Fede','Rossi','1957-01-01','+391234665430','W9cOZO2Cv9EB4YthFpWwkcm69heVPXVqnwbEb21pXUWiW');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `wishlistID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `productID` int NOT NULL,
  PRIMARY KEY (`wishlistID`),
  KEY `FK_wishlist_products_idx` (`productID`),
  KEY `FK_wishlist_users_idx` (`userID`),
  CONSTRAINT `FK_wishlist_products` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_wishlist_users` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (17,4,38),(18,4,39);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-06 20:45:38
