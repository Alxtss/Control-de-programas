CREATE DATABASE  IF NOT EXISTS `gestion_programas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestion_programas`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: gestion_programas
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `alarmas`
--

DROP TABLE IF EXISTS `alarmas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarmas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(20) DEFAULT 'Recordatorio',
  `asunto` varchar(500) DEFAULT NULL,
  `fecha_programada` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarmas`
--

LOCK TABLES `alarmas` WRITE;
/*!40000 ALTER TABLE `alarmas` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarmas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autores`
--

DROP TABLE IF EXISTS `autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_autor` varchar(50) DEFAULT NULL,
  `codigo_programa` varchar(10) DEFAULT NULL,
  `estado` varchar(15) DEFAULT 'Activo',
  `detalle` varchar(15) DEFAULT 'No Frecuente',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autores`
--

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` VALUES (1,'Autor 1','P1','Activo','Frecuente'),(2,'Autor 2','P1','Activo','No Frecuente'),(3,'Autor 3','P1','No Programar','No Frecuente'),(4,'Autor 4','P1','Activo','Frecuente'),(5,'Autor 5','P1','No Programar','No Frecuente'),(6,'Autor 6','P1','No Programar','No Frecuente'),(7,'Autor 7','P1','Activo','Frecuente'),(8,'Autor 8','P1','Activo','No Frecuente'),(9,'Autor 9','P1','No Programar','No Frecuente'),(10,'Autor 10','P1','Activo','Frecuente'),(11,'Autor 11','P1','No Programar','No Frecuente'),(12,'Autor 12','P1','Activo','No Frecuente'),(13,'Autor 14','P1','Activo','Frecuente');
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autores_frecuentes`
--

DROP TABLE IF EXISTS `autores_frecuentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autores_frecuentes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_autor` varchar(50) DEFAULT NULL,
  `id_autor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_autor` (`id_autor`),
  CONSTRAINT `autores_frecuentes_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autores_frecuentes`
--

LOCK TABLES `autores_frecuentes` WRITE;
/*!40000 ALTER TABLE `autores_frecuentes` DISABLE KEYS */;
INSERT INTO `autores_frecuentes` VALUES (1,'Autor 1',1),(2,'Autor 4',4),(3,'Autor 7',7),(4,'Autor 10',10),(5,'Autor 14',13);
/*!40000 ALTER TABLE `autores_frecuentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blacklist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_autor` int DEFAULT NULL,
  `nombre_autor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_autor` (`id_autor`),
  CONSTRAINT `blacklist_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blacklist`
--

LOCK TABLES `blacklist` WRITE;
/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
INSERT INTO `blacklist` VALUES (1,3,'Autor 3'),(2,6,'Autor 6'),(3,5,'Autor 5'),(4,11,'Autor 11'),(5,9,'Autor 9');
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nuevos_cds`
--

DROP TABLE IF EXISTS `nuevos_cds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nuevos_cds` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_autor` varchar(50) DEFAULT NULL,
  `cantidad_cds` int DEFAULT NULL,
  `id_autor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_autor` (`id_autor`),
  CONSTRAINT `nuevos_cds_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nuevos_cds`
--

LOCK TABLES `nuevos_cds` WRITE;
/*!40000 ALTER TABLE `nuevos_cds` DISABLE KEYS */;
INSERT INTO `nuevos_cds` VALUES (1,'Autor 1',3,1),(2,'Autor 7',4,7),(3,'Autor 10',3,10),(4,'Autor 8',1,8),(6,'Autor 12',1,12);
/*!40000 ALTER TABLE `nuevos_cds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programas`
--

DROP TABLE IF EXISTS `programas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) DEFAULT NULL,
  `nombre_programa` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programas`
--

LOCK TABLES `programas` WRITE;
/*!40000 ALTER TABLE `programas` DISABLE KEYS */;
INSERT INTO `programas` VALUES (1,'P1','Programa 1');
/*!40000 ALTER TABLE `programas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_programa1`
--

DROP TABLE IF EXISTS `registro_programa1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_programa1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_autor` varchar(50) NOT NULL,
  `fecha_programado` date DEFAULT NULL,
  `id_autor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_autor` (`id_autor`),
  CONSTRAINT `registro_programa1_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_programa1`
--

LOCK TABLES `registro_programa1` WRITE;
/*!40000 ALTER TABLE `registro_programa1` DISABLE KEYS */;
INSERT INTO `registro_programa1` VALUES (1,'Autor 1','2021-01-02',1),(2,'Autor 6','2021-01-03',6),(3,'Autor 3','2021-01-04',3),(4,'Autor 12','2021-01-05',12),(5,'Autor 4','2021-01-06',4),(6,'Autor 10','2021-01-07',10),(7,'Autor 5','2021-01-08',5),(8,'Autor 11','2021-01-09',11),(9,'Autor 8','2021-01-10',8),(10,'Autor 7','2021-01-11',7),(11,'Autor 2','2021-01-12',2),(12,'Autor 9','2021-01-13',9),(14,'Autor 7','2021-01-19',7),(15,'Autor 4','2021-01-18',4);
/*!40000 ALTER TABLE `registro_programa1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_programa2`
--

DROP TABLE IF EXISTS `registro_programa2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_programa2` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_autor` varchar(50) NOT NULL,
  `titulo_mensaje` varchar(100) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_programado` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_programa2`
--

LOCK TABLES `registro_programa2` WRITE;
/*!40000 ALTER TABLE `registro_programa2` DISABLE KEYS */;
INSERT INTO `registro_programa2` VALUES (1,'Autor principal Programa 2','Titulo 1','2017-06-15','2021-01-02'),(2,'Autor principal Programa 2','Titulo 1','2015-01-23','2021-01-03'),(3,'Autor principal Programa 2','Titulo 1','2020-03-14','2021-01-04'),(4,'Autor principal Programa 2','Titulo 1','2019-08-04','2021-01-05'),(5,'Autor principal Programa 2','Titulo 1','2016-05-05','2021-01-06'),(6,'Autor principal Programa 2','Titulo 1','2018-04-10','2021-01-07'),(7,'Autor principal Programa 2','Titulo 1','2015-01-07','2021-01-08'),(8,'Autor principal Programa 2','Titulo 1','2020-01-17','2021-01-09'),(9,'Autor principal Programa 2','Titulo 1','2019-09-29','2021-01-10'),(10,'Autor principal Programa 2','Titulo 1','2019-06-15','2021-01-11'),(11,'Autor principal Programa 2','Titulo 1','2017-02-11','2021-01-12'),(12,'Autor principal Programa 2','Titulo 1','2015-07-30','2021-01-13'),(13,'Autor principal Programa 2','Titulo 1','2020-03-13','2021-01-14'),(15,'Autor principal Programa 2','titulo','2002-04-03','2021-01-19'),(16,'Autor principal Programa 2','-- SIN REGISTRAR!! --','0000-00-00','2021-01-18');
/*!40000 ALTER TABLE `registro_programa2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `view_alarmas`
--

DROP TABLE IF EXISTS `view_alarmas`;
/*!50001 DROP VIEW IF EXISTS `view_alarmas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_alarmas` AS SELECT 
 1 AS `N° REG`,
 1 AS `ASUNTO`,
 1 AS `HORA DE PROGRAMACION`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_autores`
--

DROP TABLE IF EXISTS `view_autores`;
/*!50001 DROP VIEW IF EXISTS `view_autores`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_autores` AS SELECT 
 1 AS `ID`,
 1 AS `NOMBRE DEL AUTOR/A`,
 1 AS `PROGRAMA`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_autoresfrecuentes`
--

DROP TABLE IF EXISTS `view_autoresfrecuentes`;
/*!50001 DROP VIEW IF EXISTS `view_autoresfrecuentes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_autoresfrecuentes` AS SELECT 
 1 AS `id`,
 1 AS `AUTORES FRECUENTES`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_autoresnofrecuentes`
--

DROP TABLE IF EXISTS `view_autoresnofrecuentes`;
/*!50001 DROP VIEW IF EXISTS `view_autoresnofrecuentes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_autoresnofrecuentes` AS SELECT 
 1 AS `ID`,
 1 AS `NOMBRE DEL AUTOR/A`,
 1 AS `ESTADO`,
 1 AS `detalle`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_autoresprograma1`
--

DROP TABLE IF EXISTS `view_autoresprograma1`;
/*!50001 DROP VIEW IF EXISTS `view_autoresprograma1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_autoresprograma1` AS SELECT 
 1 AS `ID`,
 1 AS `NOMBRE DEL AUTOR/A`,
 1 AS `ESTADO`,
 1 AS `DETALLE`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_blacklist`
--

DROP TABLE IF EXISTS `view_blacklist`;
/*!50001 DROP VIEW IF EXISTS `view_blacklist`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_blacklist` AS SELECT 
 1 AS `ID`,
 1 AS `NO PROGRAMAR`,
 1 AS `IA`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_filtroautoresfrecuentes`
--

DROP TABLE IF EXISTS `view_filtroautoresfrecuentes`;
/*!50001 DROP VIEW IF EXISTS `view_filtroautoresfrecuentes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_filtroautoresfrecuentes` AS SELECT 
 1 AS `id`,
 1 AS `AUTORES FRECUENTES`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_filtronuevoscds`
--

DROP TABLE IF EXISTS `view_filtronuevoscds`;
/*!50001 DROP VIEW IF EXISTS `view_filtronuevoscds`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_filtronuevoscds` AS SELECT 
 1 AS `id`,
 1 AS `nombre_autor`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_nuevoscds`
--

DROP TABLE IF EXISTS `view_nuevoscds`;
/*!50001 DROP VIEW IF EXISTS `view_nuevoscds`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_nuevoscds` AS SELECT 
 1 AS `id`,
 1 AS `AUTOR/A`,
 1 AS `N° de CDs`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_programas`
--

DROP TABLE IF EXISTS `view_programas`;
/*!50001 DROP VIEW IF EXISTS `view_programas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_programas` AS SELECT 
 1 AS `Código`,
 1 AS `Nombre del Programa`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_registroprograma1`
--

DROP TABLE IF EXISTS `view_registroprograma1`;
/*!50001 DROP VIEW IF EXISTS `view_registroprograma1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_registroprograma1` AS SELECT 
 1 AS `N° REG.`,
 1 AS `AUTOR/A`,
 1 AS `FECHA DE PROGRAMADO`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_registroprograma2`
--

DROP TABLE IF EXISTS `view_registroprograma2`;
/*!50001 DROP VIEW IF EXISTS `view_registroprograma2`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_registroprograma2` AS SELECT 
 1 AS `N° REG.`,
 1 AS `AUTOR/A`,
 1 AS `TITULO DEL MENSAJE`,
 1 AS `FECHA DEL DISCO`,
 1 AS `FECHA DE PROGRAMADO`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'gestion_programas'
--

--
-- Dumping routines for database 'gestion_programas'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_deleteAlarmas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteAlarmas`(in idReg int)
begin
	delete from alarmas where id=idReg;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_deleteAutores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteAutores`(in idAut int)
begin
	delete from autores where id=idAut;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_deleteOfBlackList` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteOfBlackList`(in _id int)
begin
	delete from blackList where id=_id;
	select 'autor eliminado de la black list';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_deleteOfListFrecuentes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteOfListFrecuentes`(in _id int, in nombre varchar(50))
begin
	delete from autores_frecuentes where id=_id;
	update autores set detalle='No Frecuente' where nombre_autor=nombre;
	select 'se elimino de la lista de frecuentes';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_deleteProgramas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteProgramas`(in idProg int)
begin
	delete from programas where id=idProg;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_deleteRegPrograma1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteRegPrograma1`(in idReg int)
begin
	delete from registro_Programa1 where id=idReg;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_deleteRegPrograma2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_deleteRegPrograma2`(in idReg int)
begin
	delete from registro_Programa2 where id=idReg;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_editAutores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editAutores`(in idAutor int, in nombre varchar(50), in codPrograma varchar(10))
begin
	update autores set nombre_autor=nombre, codigo_programa=codPrograma where id=idAutor;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_editProgramas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editProgramas`(in idProg int, in codProgra varchar(10), in nombre varchar(50))
begin
	update programas set codigo=codProgra, nombre_programa=nombre where id=idProg;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_editRegistroPrograma1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editRegistroPrograma1`(in idAut int, in nomAutor varchar(50))
begin
	declare _id_autor int;
    set _id_autor = (select id from autores where nombre_autor=nomAutor);
	update registro_Programa1 set nombre_autor=nomAutor, id_autor=_id_autor where id=idAut;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_editRegistroPrograma2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editRegistroPrograma2`(in idReg int, in titulo varchar(100), in fechaEmision date)
begin
    update registro_Programa2 set titulo_mensaje=titulo, fecha_emision=fechaEmision where id=idReg;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertAlarmas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertAlarmas`(in asunto varchar(500), in hora time)
begin
	declare fecha date;
    set fecha = (select curdate());
    
	insert into alarmas values(null,'Recordatorio',asunto,fecha,hora);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertAutores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertAutores`(in nombre varchar(50), in codPrograma varchar(10))
begin
	declare estado varchar(15);
    declare detalle varchar(15);
    declare validarRegistro int;
    set validarRegistro = (select exists(select nombre_autor from autores where nombre_autor LIKE CONCAT('%',nombre)));
    set estado = 'Activo';
    set detalle = 'No Frecuente';
    
    if(validarRegistro=1) then
		select 'el autor ya existe en la bd';
	else
		insert into autores values(null, nombre, codPrograma, estado, detalle);
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertBlackList` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertBlackList`(in nombre varchar(50))
begin
	declare id_aut int;
    declare id_validator int;
    declare id_blackList int;
    set id_aut = (select exists(select id from autores where nombre_autor=nombre));
    set id_validator = (select id from autores where nombre_autor=nombre);
    set id_blackList = (select exists(select id_autor from blackList where id_autor=id_validator));
    
    if(id_aut=0) then 
		select 'no existe el autor espesificado';
	else   
		if(id_blackList=0) then
			insert into blackList values(null,id_validator,nombre);
			update autores set estado='No Programar' where id=id_validator;
			select 'autor agregado a la black list con exito!';
        else
			select 'el autor ya existe en la black list';
		end if;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertFrecuentes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertFrecuentes`(in nombre varchar(50))
begin
	declare validarRegistro int;
    declare idAutor int;
    set idAutor = (select id from autores where nombre_autor=nombre);
    set validarRegistro = (select exists(select nombre_autor from autores_frecuentes where nombre_autor=nombre));
    
    if(validarRegistro=1) then
		select 'el autor ya existe en la tabla frecuentes';
	else
		insert into autores_frecuentes values(null,nombre,idAutor);
        update autores set detalle='Frecuente' where id=idAutor;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertNuevosCds` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertNuevosCds`(in nombre varchar(50), in cantidad int)
begin
	declare validarRegistro int;
    declare idAutor int;
    set idAutor = (select id from autores where nombre_autor=nombre);
    set validarRegistro = (select exists(select nombre_autor from nuevos_cds where nombre_autor=nombre));
    
    if(validarRegistro=1) then
		select 'el autor ya existe en la tabla';
	else
		insert into nuevos_cds values(null,nombre,cantidad,idAutor);
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertProgramas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertProgramas`(in codigo varchar(10), in nombre varchar(50))
begin
	insert into programas values(null,codigo,nombre);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_registroPrograma1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registroPrograma1`(in nomAutor varchar(50))
begin
	declare fecha date;
    declare validarRegistro int;
    declare idAutor int;
    set idAutor = (select id from autores where nombre_autor=nomAutor);
    set validarRegistro = (select exists(select fecha_programado from registro_Programa1 where fecha_programado=curdate()));
    set fecha = (select curdate());
    
	if(validarRegistro=1) then
		select 'ya se registro el programa del dia';
	else
		insert into registro_Programa1 values(null, nomAutor, fecha, idAutor);
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_registroPrograma2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registroPrograma2`(in titulo varchar(100), in fechaEmision date)
begin
	declare fechaProgramado date;
    declare autor varchar(50);
    declare validarRegistro int;
    set validarRegistro = (select exists(select fecha_programado from registro_Programa2 where fecha_programado=curdate()));
    set autor = 'Autor principal Programa 2';
    set fechaProgramado = (select curdate());
    
    if(validarRegistro=0) then
		insert into registro_Programa2 values(null,autor,titulo,fechaEmision,fechaProgramado);
	else
		select 'Ya se registro el programa del dia';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_validarRegistroPrograma1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_validarRegistroPrograma1`()
begin
	declare fecha date;
	declare registro int;
    declare hora time;
    declare validFecha int;
    declare yesterday date;
    set yesterday = curdate()-1;
    set validFecha = (select exists(select nombre_autor from registro_Programa1  where fecha_programado=curdate()-1));
    set registro = (select exists(select nombre_autor from registro_Programa1  where fecha_programado=curdate()));
	set fecha = curdate();
    set hora = (select time(now()));

	 if (hora>'14:00:00' and registro = 0) then
		insert into registro_Programa1 values(null, '-- SIN REGISTRAR!! --', fecha,null);
        select 'no se encontraron registros, se inserto uno vacio';
	else
        select 'todo bien';
	end if;
    
    if(validFecha=0) then
		insert into registro_Programa1 values(null, '-- SIN REGISTRAR!! --', yesterday,null);
        select 'no se encontraron registros el dia de ayer, se inserto uno vacio';
	else
		select 'Registro de ayer comprobado!';
	end if;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_validarRegistroPrograma2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_validarRegistroPrograma2`()
begin
	declare fecha date;
	declare registro int;
    declare hora time;
    declare autor varchar(50);
    declare validFecha int;
    declare yesterday date;
    
    set yesterday = curdate()-1;
    set validFecha = (select exists(select nombre_autor from registro_Programa2  where fecha_programado=curdate()-1));    
    set registro = (select exists(select titulo_mensaje from registro_Programa2  where fecha_programado=curdate()));
	set fecha = curdate();
    set hora = (select time(now()));
    set autor = 'Autor principal Programa 2';

	if (hora>'10:00:00' and registro = 0) then
		insert into registro_Programa2 values(null,autor,'-- SIN REGISTRAR!! --','00-00-00',fecha);
        select 'no se encontraron registros, se inserto uno vacio';
	else
        select 'todo bien';
	end if;
    
    if(validFecha=0) then
		insert into registro_Programa2 values(null,autor,'-- SIN REGISTRAR!! --','00-00-00',yesterday);
        select 'no se encontraron registros, se inserto uno vacio';
	else
		select 'Registro de ayer comprobado!';
	end if;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `view_alarmas`
--

/*!50001 DROP VIEW IF EXISTS `view_alarmas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_alarmas` AS select `alarmas`.`id` AS `N° REG`,`alarmas`.`asunto` AS `ASUNTO`,(select concat_ws(' ',convert(date_format(`alarmas`.`fecha_programada`,'%a %d-%m-%Y') using utf8mb4),convert(date_format(`alarmas`.`hora`,'- %r') using utf8mb4))) AS `HORA DE PROGRAMACION` from `alarmas` order by `alarmas`.`id` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_autores`
--

/*!50001 DROP VIEW IF EXISTS `view_autores`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_autores` AS select `a`.`id` AS `ID`,`a`.`nombre_autor` AS `NOMBRE DEL AUTOR/A`,`p`.`nombre_programa` AS `PROGRAMA` from (`autores` `a` join `programas` `p` on((`a`.`codigo_programa` = `p`.`codigo`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_autoresfrecuentes`
--

/*!50001 DROP VIEW IF EXISTS `view_autoresfrecuentes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_autoresfrecuentes` AS select `autores_frecuentes`.`id` AS `id`,`autores_frecuentes`.`nombre_autor` AS `AUTORES FRECUENTES` from `autores_frecuentes` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_autoresnofrecuentes`
--

/*!50001 DROP VIEW IF EXISTS `view_autoresnofrecuentes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_autoresnofrecuentes` AS select `a`.`id` AS `ID`,`a`.`nombre_autor` AS `NOMBRE DEL AUTOR/A`,`a`.`estado` AS `ESTADO`,`a`.`detalle` AS `detalle` from (`autores` `a` join `programas` `p` on((`a`.`codigo_programa` = `p`.`codigo`))) where ((`p`.`codigo` = 'P1') and (`a`.`detalle` = 'No Frecuente') and (`a`.`estado` = 'Activo')) order by rand() */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_autoresprograma1`
--

/*!50001 DROP VIEW IF EXISTS `view_autoresprograma1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_autoresprograma1` AS select `a`.`id` AS `ID`,`a`.`nombre_autor` AS `NOMBRE DEL AUTOR/A`,`a`.`estado` AS `ESTADO`,`a`.`detalle` AS `DETALLE` from (`autores` `a` join `programas` `p` on((`a`.`codigo_programa` = `p`.`codigo`))) where ((`p`.`codigo` = 'P1') and (`a`.`estado` = 'Activo')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_blacklist`
--

/*!50001 DROP VIEW IF EXISTS `view_blacklist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_blacklist` AS select `blacklist`.`id` AS `ID`,`blacklist`.`nombre_autor` AS `NO PROGRAMAR`,`blacklist`.`id_autor` AS `IA` from `blacklist` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_filtroautoresfrecuentes`
--

/*!50001 DROP VIEW IF EXISTS `view_filtroautoresfrecuentes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_filtroautoresfrecuentes` AS select distinct `a`.`id` AS `id`,`af`.`nombre_autor` AS `AUTORES FRECUENTES` from ((`autores_frecuentes` `af` join `autores` `a` on((`af`.`id_autor` = `a`.`id`))) join `programas` `p` on((`a`.`codigo_programa` = `p`.`codigo`))) where ((`p`.`codigo` = 'P1') and (`a`.`estado` = 'Activo')) order by rand() */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_filtronuevoscds`
--

/*!50001 DROP VIEW IF EXISTS `view_filtronuevoscds`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_filtronuevoscds` AS select distinct `nc`.`id` AS `id`,`nc`.`nombre_autor` AS `nombre_autor` from (((`nuevos_cds` `nc` join `autores` `a` on((`nc`.`id_autor` = `a`.`id`))) join `programas` `p` on((`a`.`codigo_programa` = `p`.`codigo`))) join `registro_programa1` `r` on((`a`.`id` = `r`.`id_autor`))) where ((`p`.`codigo` = 'P1') and (`a`.`estado` = 'Activo')) order by rand() */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_nuevoscds`
--

/*!50001 DROP VIEW IF EXISTS `view_nuevoscds`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_nuevoscds` AS select `nuevos_cds`.`id` AS `id`,`nuevos_cds`.`nombre_autor` AS `AUTOR/A`,`nuevos_cds`.`cantidad_cds` AS `N° de CDs` from `nuevos_cds` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_programas`
--

/*!50001 DROP VIEW IF EXISTS `view_programas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_programas` AS select `programas`.`codigo` AS `Código`,`programas`.`nombre_programa` AS `Nombre del Programa` from `programas` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_registroprograma1`
--

/*!50001 DROP VIEW IF EXISTS `view_registroprograma1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_registroprograma1` AS select `registro_programa1`.`id` AS `N° REG.`,`registro_programa1`.`nombre_autor` AS `AUTOR/A`,date_format(`registro_programa1`.`fecha_programado`,'%a %d - %M - %Y') AS `FECHA DE PROGRAMADO` from `registro_programa1` order by `registro_programa1`.`id` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_registroprograma2`
--

/*!50001 DROP VIEW IF EXISTS `view_registroprograma2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_registroprograma2` AS select `registro_programa2`.`id` AS `N° REG.`,`registro_programa2`.`nombre_autor` AS `AUTOR/A`,`registro_programa2`.`titulo_mensaje` AS `TITULO DEL MENSAJE`,date_format(`registro_programa2`.`fecha_emision`,'%m-%d-%Y') AS `FECHA DEL DISCO`,date_format(`registro_programa2`.`fecha_programado`,'%a %d - %M - %Y') AS `FECHA DE PROGRAMADO` from `registro_programa2` order by `registro_programa2`.`id` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-19  2:15:43
