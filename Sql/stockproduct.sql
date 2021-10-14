-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2021 at 06:26 AM
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
-- Database: `stockproduct`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounttable`
--

CREATE TABLE `accounttable` (
  `id_account` varchar(16) NOT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `account_password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounttable`
--

INSERT INTO `accounttable` (`id_account`, `account_name`, `account_password`) VALUES
('01', 'test1', 'test1');

-- --------------------------------------------------------

--
-- Table structure for table `receipttable`
--

CREATE TABLE `receipttable` (
  `id_receipt` int(11) NOT NULL,
  `id_product` varchar(16) DEFAULT NULL,
  `id_account` varchar(16) DEFAULT NULL,
  `receipt_type` varchar(16) DEFAULT NULL,
  `p_quatity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receipttable`
--

INSERT INTO `receipttable` (`id_receipt`, `id_product`, `id_account`, `receipt_type`, `p_quatity`) VALUES
(39, 'aqua200', '01', 'stock in', 5);

-- --------------------------------------------------------

--
-- Table structure for table `stocktable`
--

CREATE TABLE `stocktable` (
  `id_product` varchar(16) NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stocktable`
--

INSERT INTO `stocktable` (`id_product`, `product_name`, `product_stock`) VALUES
('aqua200', 'aqua 200ml', 20);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounttable`
--
ALTER TABLE `accounttable`
  ADD PRIMARY KEY (`id_account`);

--
-- Indexes for table `receipttable`
--
ALTER TABLE `receipttable`
  ADD PRIMARY KEY (`id_receipt`),
  ADD UNIQUE KEY `id_receipt` (`id_receipt`),
  ADD KEY `id_product` (`id_product`),
  ADD KEY `id_account` (`id_account`);

--
-- Indexes for table `stocktable`
--
ALTER TABLE `stocktable`
  ADD PRIMARY KEY (`id_product`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `receipttable`
--
ALTER TABLE `receipttable`
  MODIFY `id_receipt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `receipttable`
--
ALTER TABLE `receipttable`
  ADD CONSTRAINT `receipttable_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `stocktable` (`id_product`),
  ADD CONSTRAINT `receipttable_ibfk_2` FOREIGN KEY (`id_account`) REFERENCES `accounttable` (`id_account`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
