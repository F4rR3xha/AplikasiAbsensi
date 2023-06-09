-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2023 at 05:35 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `absensi`
--

-- --------------------------------------------------------

--
-- Table structure for table `presensi`
--

CREATE TABLE `presensi` (
  `nim` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `matakuliah` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `presensi`
--

INSERT INTO `presensi` (`nim`, `nama`, `kelas`, `matakuliah`, `status`) VALUES
('A11.2020.12487', 'Ikhwan Fila Wadzakiyya', 'A11.4612', 'Kecerdasan Buatan', 'Hadir'),
('A11.2020.12708', 'Muhammad Ikhsan Anugrah', 'A11.4612', 'Kecerdasan Buatan', 'Hadir'),
('A11.2020.13084', 'Rosalia Natal Silalahi', 'A11.4612', 'Kecerdasan Buatan', 'Hadir'),
('A11.2020.12915', 'Muhammad Rifky Luhur', 'A11.4612', 'Kecerdasan Buatan ', 'Hadir'),
('A11.2020.12487', 'Ikhwan Fila Wadzakiyya ', 'A11.4612', 'Kecerdasan Buatan ', 'Hadir'),
('A11.2020.12487', 'Ikhwan Fila Wadzakiyya ', 'A11.4614', 'Projek Perangkat Lunak', 'Hadir');

-- --------------------------------------------------------

--
-- Table structure for table `user_admin`
--

CREATE TABLE `user_admin` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_admin`
--

INSERT INTO `user_admin` (`id`, `nama`, `username`, `password`) VALUES
(1, 'AdminSatu', 'admin1', 'user1');

-- --------------------------------------------------------

--
-- Table structure for table `user_mhs`
--

CREATE TABLE `user_mhs` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_mhs`
--

INSERT INTO `user_mhs` (`id`, `nama`, `username`, `password`) VALUES
(2, 'Muhammad Ikhsan Anugrah', 'A11.2020.12708', 'mhs12708'),
(3, 'Muhammad Rifky Luhur', 'A11.2020.12915', 'mhs12915'),
(4, 'Irham Johar Permana', 'A11.2020.12652', 'mhs12652'),
(5, 'Rosalia Natal Silalahi', 'A11.2020.13084', 'mhs13084'),
(6, 'Devi Kartika Sari', 'A11.2020.12518', 'mhs12518'),
(9, 'Ikhwan Fila Wadzakiyya', 'A11.2020.12487', 'mhs12487');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user_admin`
--
ALTER TABLE `user_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_mhs`
--
ALTER TABLE `user_mhs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user_admin`
--
ALTER TABLE `user_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_mhs`
--
ALTER TABLE `user_mhs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
