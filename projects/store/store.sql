-- MySQL dump 10.13  Distrib 5.5.28, for osx10.6 (i386)
--
-- Host: localhost    Database: store
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `user_email` varchar(128) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `buy_data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'default'),(5,'интерьер'),(2,'книги'),(4,'одежда'),(3,'электроника');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Н. В. Гоголь. Сочинения в 12 томах (комплект из 6 книг)',11200),(2,'И. С. Тургенев \"Вешние воды\"',616),(3,'Велемир Хлебников \"Доски судьбы\"',24633),(4,'Антон Чехов \"Три сестры\"',945),(5,'Л. Н. Толстой. Иллюстрированное собрание сочинений в 10 томах (комплект)',500250),(6,'iPhone 4S 16GB, Black',25990),(7,'Asus Eee Pad Transformer Prime TF201 64GB + Dock',27990),(8,'Lenovo IdeaPad U310',21390),(9,'Apple iPod Shuffle 2 GB, Pink',2390),(10,'Apple iPad New 16GB Wi-Fi, White',19390),(11,'Джемпер Colletto Bianco',3890),(12,'Рубашка Katie Baby, цвет: серый, белый',975),(13,'Брюки Mininio, цвет: серый, темно-синий',1303),(14,'Куртка Hamilton',3890),(15,'Юбка SVESTA',2990),(16,'Постер холст Ностальгия',549),(17,'Картина 24х30см CAT-24',283),(18,'Картина LAND ART Меню',323),(19,'Картина Летний сад 71х27см',689);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_categories_rel`
--

DROP TABLE IF EXISTS `products_categories_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products_categories_rel` (
  `product_id` int(11) NOT NULL DEFAULT '0',
  `category_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_categories_rel`
--

LOCK TABLES `products_categories_rel` WRITE;
/*!40000 ALTER TABLE `products_categories_rel` DISABLE KEYS */;
INSERT INTO `products_categories_rel` VALUES (1,2),(2,2),(3,2),(4,2),(5,2),(6,3),(7,3),(8,3),(9,3),(10,3),(11,4),(12,4),(13,4),(14,4),(15,4),(16,5),(17,5),(18,5),(19,5);
/*!40000 ALTER TABLE `products_categories_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `email` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT '%username%',
  `role` varchar(16) NOT NULL DEFAULT 'user',
  `pass` varchar(32) NOT NULL DEFAULT '',
  `balance` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin@localhost','Админ Админов','admin','1',0),('vasek@mail.ru','Василий','user','1',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-24 21:57:25
