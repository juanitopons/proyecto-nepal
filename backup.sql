-- MySQL dump 10.13  Distrib 5.5.29, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: nepal
-- ------------------------------------------------------
-- Server version	5.5.29-0ubuntu0.12.04.2

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
-- Table structure for table `alergias`
--

DROP TABLE IF EXISTS `alergias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alergias` (
  `idalergia` int(3) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idalergia`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alergias`
--

LOCK TABLES `alergias` WRITE;
/*!40000 ALTER TABLE `alergias` DISABLE KEYS */;
INSERT INTO `alergias` VALUES (6,'AlergiaPrueba',NULL);
/*!40000 ALTER TABLE `alergias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orfanatos`
--

DROP TABLE IF EXISTS `orfanatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orfanatos` (
  `idorfanato` int(3) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  PRIMARY KEY (`idorfanato`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orfanatos`
--

LOCK TABLES `orfanatos` WRITE;
/*!40000 ALTER TABLE `orfanatos` DISABLE KEYS */;
INSERT INTO `orfanatos` VALUES (1,'Bhimphedi'),(2,'Patán'),(3,'Mahendranagar'),(4,'Maiti');
/*!40000 ALTER TABLE `orfanatos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes` (
  `idpaciente` int(5) NOT NULL AUTO_INCREMENT,
  `idorfanato` int(1) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `genero` char(1) NOT NULL,
  `edad` int(2) NOT NULL,
  `antecedentes` varchar(255) DEFAULT NULL,
  `foto` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`idpaciente`),
  KEY `FKpacientes899736` (`idorfanato`),
  CONSTRAINT `FKpacientes899736` FOREIGN KEY (`idorfanato`) REFERENCES `orfanatos` (`idorfanato`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (9,3,'2013-07-21 09:23:48','Prueba','Cantó Astro','H',1,'','');
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pacientes_before_trigger` BEFORE INSERT ON `pacientes`
 FOR EACH ROW BEGIN
declare error varchar(20);
    IF LENGTH( NEW.nombre ) <= 0  THEN
    set error = "'nombre'";
        signal sqlstate '45000' set message_text = error;
    ELSEIF LENGTH( NEW.apellidos ) <= 0 THEN
    set error = "'apellidos'";
        signal sqlstate '45000' set message_text = error;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pacientes_update_trigger` BEFORE UPDATE ON `pacientes`
 FOR EACH ROW BEGIN
declare error varchar(20);
    IF LENGTH( NEW.nombre ) <= 0  THEN
    set error = "'nombre'";
        signal sqlstate '45000' set message_text = error;
    ELSEIF LENGTH( NEW.apellidos ) <= 0 THEN
    set error = "'apellidos'";
        signal sqlstate '45000' set message_text = error;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `pacientes_alergias`
--

DROP TABLE IF EXISTS `pacientes_alergias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes_alergias` (
  `idpaciente` int(5) NOT NULL,
  `idalergia` int(3) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idpaciente`,`idalergia`),
  KEY `FKpacientes_596333` (`idpaciente`),
  KEY `FKpacientes_89132` (`idalergia`),
  CONSTRAINT `FKpacientes_596333` FOREIGN KEY (`idpaciente`) REFERENCES `pacientes` (`idpaciente`) ON DELETE CASCADE,
  CONSTRAINT `FKpacientes_89132` FOREIGN KEY (`idalergia`) REFERENCES `alergias` (`idalergia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes_alergias`
--

LOCK TABLES `pacientes_alergias` WRITE;
/*!40000 ALTER TABLE `pacientes_alergias` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacientes_alergias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes_vacunas`
--

DROP TABLE IF EXISTS `pacientes_vacunas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes_vacunas` (
  `idpaciente` int(5) NOT NULL,
  `idvacuna` int(3) NOT NULL,
  `fecha` date NOT NULL,
  `dosis` int(1) DEFAULT NULL,
  PRIMARY KEY (`idpaciente`,`idvacuna`),
  KEY `FKpacientes_372323` (`idpaciente`),
  KEY `FKpacientes_447184` (`idvacuna`),
  CONSTRAINT `FKpacientes_372323` FOREIGN KEY (`idpaciente`) REFERENCES `pacientes` (`idpaciente`) ON DELETE CASCADE,
  CONSTRAINT `FKpacientes_447184` FOREIGN KEY (`idvacuna`) REFERENCES `vacunas` (`idvacuna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes_vacunas`
--

LOCK TABLES `pacientes_vacunas` WRITE;
/*!40000 ALTER TABLE `pacientes_vacunas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacientes_vacunas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacunas`
--

DROP TABLE IF EXISTS `vacunas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacunas` (
  `idvacuna` int(3) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `edadmin` int(2) DEFAULT NULL,
  `edadmax` int(2) DEFAULT NULL,
  PRIMARY KEY (`idvacuna`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacunas`
--

LOCK TABLES `vacunas` WRITE;
/*!40000 ALTER TABLE `vacunas` DISABLE KEYS */;
INSERT INTO `vacunas` VALUES (4,'VacunaPrueba',0,0);
/*!40000 ALTER TABLE `vacunas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitas`
--

DROP TABLE IF EXISTS `visitas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitas` (
  `idvisita` int(10) NOT NULL AUTO_INCREMENT,
  `idpaciente` int(5) NOT NULL,
  `fechavisita` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pelo` char(1) NOT NULL,
  `vision` char(1) NOT NULL,
  `oidos` char(1) NOT NULL,
  `dientes` char(1) NOT NULL,
  `higiene` char(1) NOT NULL,
  `altura` double NOT NULL,
  `peso` double NOT NULL,
  `imc` double DEFAULT NULL,
  `fc` int(3) DEFAULT NULL,
  `tamax` int(3) DEFAULT NULL,
  `tamin` int(3) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idvisita`,`idpaciente`),
  KEY `FKvisitas5050` (`idpaciente`),
  CONSTRAINT `FKvisitas5050` FOREIGN KEY (`idpaciente`) REFERENCES `pacientes` (`idpaciente`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitas`
--

LOCK TABLES `visitas` WRITE;
/*!40000 ALTER TABLE `visitas` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-21 18:03:43

