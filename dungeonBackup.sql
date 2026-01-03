CREATE DATABASE  IF NOT EXISTS `dungeon` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dungeon`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: dungeon
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `armaduras`
--

DROP TABLE IF EXISTS `armaduras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armaduras` (
  `idArmadura` int NOT NULL AUTO_INCREMENT,
  `nombreArmadura` varchar(255) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `defensaArmadura` int NOT NULL,
  `precioArmadura` int NOT NULL,
  PRIMARY KEY (`idArmadura`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armaduras`
--

LOCK TABLES `armaduras` WRITE;
/*!40000 ALTER TABLE `armaduras` DISABLE KEYS */;
INSERT INTO `armaduras` VALUES (1,'Calzones cómodos',0,0),(2,'Armadura de hierro',5,40),(3,'Armadura de plata',10,80),(4,'Armadura de oro',20,175);
/*!40000 ALTER TABLE `armaduras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `armas`
--

DROP TABLE IF EXISTS `armas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armas` (
  `idArma` int NOT NULL AUTO_INCREMENT,
  `maxArma` int NOT NULL,
  `minArma` int NOT NULL,
  `nombreArma` varchar(255) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `precioArma` int NOT NULL,
  PRIMARY KEY (`idArma`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armas`
--

LOCK TABLES `armas` WRITE;
/*!40000 ALTER TABLE `armas` DISABLE KEYS */;
INSERT INTO `armas` VALUES (1,5,1,'Espada Oxidada',0),(2,8,4,'Espada de Hierro',30),(3,12,7,'Espada de Plata',60),(4,16,13,'Espada de Oro',150);
/*!40000 ALTER TABLE `armas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enemigos`
--

DROP TABLE IF EXISTS `enemigos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enemigos` (
  `idEnemigo` int NOT NULL AUTO_INCREMENT,
  `defensaEnemigo` int NOT NULL,
  `nombreEnemigo` varchar(255) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `vitalidadEnemigo` int NOT NULL,
  `ataqueMax` int NOT NULL,
  `ataqueMin` int NOT NULL,
  PRIMARY KEY (`idEnemigo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enemigos`
--

LOCK TABLES `enemigos` WRITE;
/*!40000 ALTER TABLE `enemigos` DISABLE KEYS */;
INSERT INTO `enemigos` VALUES (1,0,'Lobo Blanco',8,7,3),(2,0,'Lobo',6,3,1),(3,0,'Murciélago',7,8,6),(4,2,'Perro Guardián',13,12,0),(5,0,'Zombi',20,16,10),(6,3,'Zombi con armadura',20,14,10),(7,5,'Rey Maldito',16,21,12),(8,0,'Fantasma',26,16,11),(9,0,'Cráneo',32,21,14),(10,22,'Maldad',40,32,20);
/*!40000 ALTER TABLE `enemigos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial` (
  `idPartida` int NOT NULL AUTO_INCREMENT,
  `combateFinal` int NOT NULL,
  `nivel` int NOT NULL,
  `nombreJugador` varchar(255) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `fecha` varchar(255) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `oroEquipo` int NOT NULL,
  `oroVida` int NOT NULL,
  PRIMARY KEY (`idPartida`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores` (
  `idJugador` int NOT NULL AUTO_INCREMENT,
  `ataque` int NOT NULL,
  `defensa` int NOT NULL,
  `nivel` int NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
  `oro` int NOT NULL,
  `vitalidad` int NOT NULL,
  PRIMARY KEY (`idJugador`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` VALUES (1,0,0,0,'Arthur',50,12);
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-03 18:18:07
