-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2023 at 03:47 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `postcres`
--

-- --------------------------------------------------------

--
-- Table structure for table `especialidad`
--

CREATE TABLE `especialidad` (
  `codEsp` int(11) NOT NULL,
  `especialidad` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `especialidad`
--

INSERT INTO `especialidad` (`codEsp`, `especialidad`) VALUES
(1, 'cardiologia'),
(2, 'clínica médica'),
(3, 'dermatología'),
(4, 'diabetogia'),
(5, 'endocrinologia'),
(6, 'ginecologia'),
(7, 'infectologia'),
(8, 'neurologia'),
(9, 'nutricion'),
(10, 'obstetricia'),
(11, 'pediatría'),
(12, 'psicología'),
(13, 'reumatología'),
(14, 'traumatolgía'),
(15, 'urología');

-- --------------------------------------------------------

--
-- Table structure for table `profesional`
--

CREATE TABLE `profesional` (
  `matricula` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `codEsp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `profesional`
--

INSERT INTO `profesional` (`matricula`, `nombre`, `apellido`, `codEsp`) VALUES
('2345', 'Martin', 'Garcia', 1),
('33465', 'Milvia', 'Alasino', 3),
('34567', 'Yamila', 'Murga', 3),
('45322', 'cecilia', 'Solchaga', 2);

-- --------------------------------------------------------

--
-- Table structure for table `rol`
--

CREATE TABLE `rol` (
  `id` int(20) NOT NULL,
  `rol` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rol`
--

INSERT INTO `rol` (`id`, `rol`) VALUES
(1, 'ADMINISTRATIVO'),
(3, 'CLIENTE'),
(2, 'CONTABLE');

-- --------------------------------------------------------

--
-- Table structure for table `turno`
--

CREATE TABLE `turno` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `username` varchar(50) NOT NULL,
  `numProfesional` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `turno`
--

INSERT INTO `turno` (`id`, `fecha`, `hora`, `username`, `numProfesional`) VALUES
(1, '2023-06-20', '16:00:00', 'admin', '2345'),
(2, '2023-06-20', '16:30:00', 'tester', '2345');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'admin', '$2y$10$OMrOetdh9iDw3tbaVPSt1.RwgB.x7EEI95qxdKpOEZXk4v4nyvzUq'),
(2, 'tester', 'tester'),
(3, 'carlos', 'profe'),
(4, 'Peggy', 'Carter'),
(6, 'Raquel', 'paraBorrar');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int(20) DEFAULT NULL,
  `rol_id` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `rol_id`) VALUES
(1, 1),
(1, 2),
(3, 1),
(4, 3),
(2, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`codEsp`),
  ADD UNIQUE KEY `especialidad` (`especialidad`);

--
-- Indexes for table `profesional`
--
ALTER TABLE `profesional`
  ADD PRIMARY KEY (`matricula`),
  ADD UNIQUE KEY `matricula` (`matricula`),
  ADD KEY `codEsp` (`codEsp`);

--
-- Indexes for table `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `rol` (`rol`);

--
-- Indexes for table `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`),
  ADD KEY `numProfesional` (`numProfesional`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `user_roles_FK` (`rol_id`),
  ADD KEY `user_roles_FK_1` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `especialidad`
--
ALTER TABLE `especialidad`
  MODIFY `codEsp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `profesional`
--
ALTER TABLE `profesional`
  ADD CONSTRAINT `profesional_ibfk_1` FOREIGN KEY (`codEsp`) REFERENCES `especialidad` (`codEsp`);

--
-- Constraints for table `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `turno_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `turno_ibfk_2` FOREIGN KEY (`numProfesional`) REFERENCES `profesional` (`matricula`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_FK` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`),
  ADD CONSTRAINT `user_roles_FK_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
