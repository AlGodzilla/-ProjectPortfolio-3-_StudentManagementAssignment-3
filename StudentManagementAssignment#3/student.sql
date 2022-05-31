-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jan 17, 2022 at 06:07 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `NIM` varchar(12) NOT NULL,
  `NAMA` varchar(256) NOT NULL,
  `JENIS_KELAMIN` varchar(256) NOT NULL,
  `TEMPAT_LAHIR` varchar(256) NOT NULL,
  `TANGGAL_LAHIR` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`NIM`, `NAMA`, `JENIS_KELAMIN`, `TEMPAT_LAHIR`, `TANGGAL_LAHIR`) VALUES
('2401962384', 'Devin Revel', 'Pria', 'Jakarta', '2002-08-12'),
('2440010366', 'Steven Tafianoto', 'Pria', 'Jakarta', '2002-08-25'),
('2440029196', 'Muhammad Rizqy Al Gozali', 'Pria', 'Palembang', '1999-04-01'),
('2440045930', 'Fellik Fernando', 'Pria', 'Bogor', '2001-10-09');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`NIM`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
