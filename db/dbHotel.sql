-- Host: localhost    Database: hotel
-- ------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `hotel`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `hotel` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `hotel`;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_Cliente` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_doc` varchar(10) NOT NULL,
  `nro_doc` varchar(45) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `sexo` varchar(45) NOT NULL,
  `fecha_Nacimiento` date NOT NULL,
  `tipo_Tarjeta_Credito` varchar(255) NOT NULL,
  `nro_Tarjeta_Credito` varchar(255) NOT NULL,
  PRIMARY KEY (`id_Cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'dni','10101010','Juan','Perez','jp@gmail.com','jperez', '4101010', 'M', '1991-03-24', 'Visa', '45435345439583495839');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadia`
--

DROP TABLE IF EXISTS `estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadia` (
  `id_Estadia` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_Ingreso` date NOT NULL,
  `fecha_Egreso` date NOT NULL,
  `estado` varchar(45) NOT NULL,
  `id_Cliente` int(11) NOT NULL,
  `nro_Habitacion` int(11) NOT NULL,
  PRIMARY KEY (`id_Estadia`),
  CONSTRAINT id_Cliente
    FOREIGN KEY (id_Cliente)
    REFERENCES hotel.cliente (id_Cliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT nro_Habitacion
    FOREIGN KEY (nro_Habitacion)
    REFERENCES hotel.habitacion(nro_Habitacion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_habitacion`
--

DROP TABLE IF EXISTS `tipo_habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_habitacion` (
  id_Tipo_Habitacion INT NOT NULL AUTO_INCREMENT,
  denominacion VARCHAR(45) NOT NULL,
  descripcion VARCHAR(255) NULL,
  capacidad_Personas INT NOT NULL,
  precio_Por_Dia FLOAT NOT NULL,
  PRIMARY KEY (id_Tipo_Habitacion))
  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `habitacion` (
  nro_Habitacion INT NOT NULL AUTO_INCREMENT,
  id_Tipo_Habitacion INT NOT NULL,
  PRIMARY KEY (nro_Habitacion),
  CONSTRAINT tipo_Habitacion
    FOREIGN KEY (id_Tipo_Habitacion)
    REFERENCES hotel.tipo_habitacion (id_Tipo_Habitacion)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
    ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
    
--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicio` (
  id_Servicio INT NOT NULL AUTO_INCREMENT,
  denominacion VARCHAR(45) NOT NULL,
  descripcion VARCHAR(255),
  costo FLOAT NOT NULL,
  PRIMARY KEY (id_Servicio));
  
--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `estadia_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadia_servicio` (
  id_estadia INT NOT NULL,
  id_servicio INT NOT NULL,
  PRIMARY KEY (id_estadia, id_servicio),
  CONSTRAINT estadia
    FOREIGN KEY (id_estadia)
    REFERENCES hotel.estadia (id_Estadia)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT servicio
    FOREIGN KEY (id_servicio)
    REFERENCES hotel.servicio (id_Servicio)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


