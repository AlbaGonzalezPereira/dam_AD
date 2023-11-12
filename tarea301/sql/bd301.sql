-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.27-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para tarea301
DROP DATABASE IF EXISTS `tarea301`;
CREATE DATABASE IF NOT EXISTS `tarea301` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `tarea301`;

-- Volcando estructura para tabla tarea301.alquiler
CREATE TABLE IF NOT EXISTS `alquiler` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `fechaAlquiler` date DEFAULT NULL,
  `fechaDevolucion` date DEFAULT NULL,
  `dniSocio` char(9) DEFAULT NULL,
  `codigoLibro` char(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dniSocio` (`dniSocio`),
  KEY `codigoLibro` (`codigoLibro`),
  CONSTRAINT `FK_libro_alquiler` FOREIGN KEY (`codigoLibro`) REFERENCES `libros` (`codigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_socio_alquiler` FOREIGN KEY (`dniSocio`) REFERENCES `socios` (`DNI`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla tarea301.alquiler: ~6 rows (aproximadamente)
DELETE FROM `alquiler`;
INSERT INTO `alquiler` (`id`, `fechaAlquiler`, `fechaDevolucion`, `dniSocio`, `codigoLibro`) VALUES
	(1, '2021-11-05', '2023-11-12', '12345678J', 'a0001'),
	(2, '2022-11-05', '2023-11-05', '98765432A', 'a0002'),
	(3, '2023-11-08', '2023-11-08', '12345678J', 'a0002'),
	(10, '2023-11-11', '2023-11-12', '98765432A', 'a0002'),
	(11, '2023-11-12', '2023-11-12', '98765432A', 'a0003'),
	(12, '2023-11-12', NULL, '12345678J', 'a0003');

-- Volcando estructura para tabla tarea301.libros
CREATE TABLE IF NOT EXISTS `libros` (
  `codigo` char(5) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `autor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla tarea301.libros: ~1 rows (aproximadamente)
DELETE FROM `libros`;
INSERT INTO `libros` (`codigo`, `titulo`, `autor`) VALUES
	('a0001', 'El señor de las abejas', 'Jose Luis'),
	('a0002', 'Buscando', 'Jose Luis'),
	('a0003', 'Happy hours', 'Xabier Maria');

-- Volcando estructura para tabla tarea301.socios
CREATE TABLE IF NOT EXISTS `socios` (
  `DNI` char(9) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Apellidos` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla tarea301.socios: ~2 rows (aproximadamente)
DELETE FROM `socios`;
INSERT INTO `socios` (`DNI`, `Nombre`, `Apellidos`) VALUES
	('12345678J', 'Alba', 'Gonzalez'),
	('98765432A', 'Juan', 'Pardo');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
