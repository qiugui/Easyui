-- MySQL dump 10.13  Distrib 5.6.21, for Win64 (x86_64)
--
-- Host: localhost    Database: mytest
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `org`
--

DROP TABLE IF EXISTS `org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org` (
  `id` int(11) NOT NULL,
  `name` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `iconCls` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `principal` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `description` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `state` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org`
--

LOCK TABLES `org` WRITE;
/*!40000 ALTER TABLE `org` DISABLE KEYS */;
INSERT INTO `org` VALUES (1,'Company',NULL,'King',100,'I am Boss','closed',999999),(2,'Beijing',NULL,'Jim',50,'I am Beijing','closed',1),(3,'Shanghai',NULL,'Tom',50,'I am Shanghai','closed',1),(5,'Beijing Part1',NULL,'LiLei',20,'I am Beijing Part1',NULL,2),(6,'Beijing Part2',NULL,'Green',20,'I am Beijing Part2',NULL,2),(7,'Beijing Part3',NULL,'Hanmei',20,'I am Beijing Part3',NULL,2),(8,'Shanhai Part1',NULL,'Sylar',20,'I am Shanghai Part1',NULL,3),(9,'Shanhai Part2',NULL,'Qiu',0,'I am Shanghai Part2',NULL,3),(13,'Shanhai Part3',NULL,'Henri',20,'I am Shanghai Part3','closed',3),(14,'111111',NULL,'44444',22,NULL,NULL,13),(15,'5555555',NULL,'88',66,'777777',NULL,13),(16,'Shanghai Part4',NULL,'QiuDan',30,'I am Shanghai Part4',NULL,3);
/*!40000 ALTER TABLE `org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `checked` int(11) DEFAULT '0',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (1,'Authority',NULL,0,NULL,999999),(2,'UserManage',NULL,0,NULL,1),(3,'JobManage',NULL,0,NULL,1),(4,'ResourceManage','',0,NULL,1),(5,'UserFunction',NULL,0,NULL,2),(7,'JobFunction',NULL,0,NULL,3),(60,'ResourceFunction',NULL,0,NULL,4),(61,'ResourceFunction2','resourcefunction2',0,NULL,4),(62,'xinjia','qqqqq',0,NULL,4),(64,'ccuy','',0,NULL,74),(65,'88888888','gf',0,NULL,4),(66,'wqwq','qqwqw',0,NULL,4),(67,'dwwddddd','w',0,NULL,4),(68,'555555555555','rfgfg tyut ',0,NULL,74),(69,'4444','sddasdf',0,NULL,74),(70,'sadafsdfa','asdadfa',0,NULL,74),(74,'1111111111','222222222',0,NULL,4),(77,'erte','ere',0,NULL,65),(78,'ertet','ert',0,NULL,65),(79,'etre','',0,NULL,65),(80,'tfuit7i','tytu',0,NULL,79),(83,'sdfgsdfgsdf','ddf',0,NULL,80),(84,'sdfsgsdf','dfdfd',0,NULL,83),(85,'dasdfasdfas','asdfasd',0,NULL,83),(86,'11111111','fasd111111',0,NULL,83),(87,'11444444','444',0,NULL,86),(88,'5555555','',0,NULL,86),(89,'666666','',0,NULL,86),(90,'邱桂',NULL,0,NULL,70);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET latin1 NOT NULL,
  `password` varchar(32) CHARACTER SET latin1 NOT NULL,
  `email` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `cityId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'qiugui','e10adc3949ba59abbe56e057f20f883e','qiugui@sina.com',NULL,NULL),(2,'qiudan','e10adc3949ba59abbe56e057f20f883e','qiudan@sina.com',NULL,NULL),(3,'taogang','e10adc3949ba59abbe56e057f20f883e','taogang@sina.com',NULL,NULL),(4,'taoguang','e10adc3949ba59abbe56e057f20f883e','taoguang@sina.com',NULL,NULL),(5,'taojing','e10adc3949ba59abbe56e057f20f883e','taojing@sina.com',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-24 14:13:55
