-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2022 at 02:09 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_kain`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_user` int(10) NOT NULL,
  `tipe_admin` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_user`, `tipe_admin`) VALUES
(2, 1),
(4, 3),
(5, 1),
(16, 1),
(17, 2),
(21, 2);

-- --------------------------------------------------------

--
-- Table structure for table `bahan`
--

CREATE TABLE `bahan` (
  `id_bahan` int(10) NOT NULL,
  `nama_bahan` varchar(128) NOT NULL,
  `harga_bahan` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bahan`
--

INSERT INTO `bahan` (`id_bahan`, `nama_bahan`, `harga_bahan`) VALUES
(1001, 'Katun', 5000),
(1002, 'Wol', 3000),
(1003, 'Plastik', 3000),
(1005, 'sofa', 3000),
(1006, 'Polymer', 5000),
(1007, 'Nylon', 5000),
(1008, 'BSY', 7500),
(1009, 'Jeans', 7000),
(1010, 'Besi', 9000);

--
-- Triggers `bahan`
--
DELIMITER $$
CREATE TRIGGER `del_bahan` BEFORE DELETE ON `bahan` FOR EACH ROW BEGIN
DELETE FROM kain_toko WHERE id_bahan = OLD.id_bahan;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` int(10) NOT NULL,
  `id_kain` varchar(64) NOT NULL,
  `quantity` int(10) NOT NULL,
  `nama_kain` varchar(128) NOT NULL,
  `harga` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `id_kain`, `quantity`, `nama_kain`, `harga`) VALUES
(1, '1001-2001-3001', 1, 'Kain Katun Merah Abstrak ', 15000),
(1, 'CUSTOM-1', 2, 'Kain Besi Hitam Polos', 5000),
(2, '1003-2003-3001', 1, 'Kain Plastik Hijau Abstrak', 12500),
(2, '1003-2003-3002', 1, 'Kain Plastik Hijau Polos', 8500),
(3, '1003-2003-3001', 1, 'Kain Plastik Hijau Abstrak', 12500),
(4, '1001-2001-3001', 1, 'Kain Katun Merah Abstrak', 15000),
(5, '1001-2001-3001', 3, 'Kain Katun Merah Abstrak', 15000),
(6, '1003-2003-3002', 1, 'Kain Plastik Hijau Polos', 10000),
(7, '1003-2003-3002', 1, 'Kain Plastik Hijau Polos', 10000),
(8, '1001-2001-3001', 1, 'Kain Katun Merah Abstrak', 15000),
(9, '1001-2002-3001', 2, 'Kain Katun Kuning Abstrak', 15000),
(10, '1002-2001-3001', 5, 'Kain Wol Merah Abstrak', 13000),
(11, '1001-2001-3001', 1, 'Kain Katun Merah Abstrak', 15000),
(12, '1001-2001-3001', 4, 'Kain Katun Merah Abstrak', 15000),
(13, '1001-2001-3001', 4, 'Kain Katun Merah Abstrak', 15000),
(14, '1001-2002-3001', 4, 'Kain Katun Kuning Abstrak', 15000),
(15, '1001-2001-3001', 2, 'Kain Katun Merah Abstrak', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `kain`
--

CREATE TABLE `kain` (
  `id_kain` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kain`
--

INSERT INTO `kain` (`id_kain`) VALUES
('1001-2001-3001'),
('1001-2001-3004'),
('1001-2002-3001'),
('1001-2004-3001'),
('1001-2005-3001'),
('1001-2006-3002'),
('1002-2001-3001'),
('1002-2001-3004'),
('1002-2002-3001'),
('1002-2004-3001'),
('1002-2006-3002'),
('1003-2001-3001'),
('1003-2001-3004'),
('1003-2002-3001'),
('1003-2002-3002'),
('1003-2003-3001'),
('1003-2003-3002'),
('1003-2004-3001'),
('1003-2006-3002'),
('1005-2001-3002'),
('1005-2002-3002'),
('1005-2003-3002'),
('1005-2004-3001'),
('1006-2004-3001'),
('1007-2001-3001'),
('1007-2002-3001'),
('1007-2003-3001'),
('1008-2001-3001'),
('1009-2001-3002'),
('1009-2002-3002'),
('1009-2003-3002'),
('1010-2001-3002'),
('1010-2002-3002'),
('1010-2003-3002'),
('1010-2004-3002'),
('1010-2005-3002'),
('CUSTOM-1'),
('CUSTOM-2'),
('CUSTOM-4'),
('CUSTOM-5'),
('CUSTOM-6');

--
-- Triggers `kain`
--
DELIMITER $$
CREATE TRIGGER `del_keranjang` BEFORE DELETE ON `kain` FOR EACH ROW BEGIN
DELETE FROM keranjang WHERE id_kain = OLD.id_kain;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kain_custom`
--

CREATE TABLE `kain_custom` (
  `id_kain` varchar(64) NOT NULL,
  `nama_bahan_custom` varchar(128) NOT NULL,
  `nama_warna_custom` varchar(128) NOT NULL,
  `nama_motif_custom` varchar(128) NOT NULL,
  `harga_kain_custom` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kain_custom`
--

INSERT INTO `kain_custom` (`id_kain`, `nama_bahan_custom`, `nama_warna_custom`, `nama_motif_custom`, `harga_kain_custom`) VALUES
('CUSTOM-1', 'Besi', 'Hitam', 'Polos', 5000),
('CUSTOM-2', 'Besi', 'Merah', 'Polos', 5000),
('CUSTOM-4', 'Baja', 'Biru', 'Kuning', 12000),
('CUSTOM-5', 'Tembaga', 'Merah', 'Polos', 7000),
('CUSTOM-6', 'Nilon', 'Pelangi', 'Polos', 7500);

--
-- Triggers `kain_custom`
--
DELIMITER $$
CREATE TRIGGER `delete_kain_custom` AFTER DELETE ON `kain_custom` FOR EACH ROW BEGIN
DELETE FROM kain WHERE id_kain = OLD.id_kain;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kain_toko`
--

CREATE TABLE `kain_toko` (
  `id_kain` varchar(64) NOT NULL,
  `id_bahan` int(10) NOT NULL,
  `id_warna` int(10) NOT NULL,
  `id_motif` int(10) NOT NULL,
  `stok` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kain_toko`
--

INSERT INTO `kain_toko` (`id_kain`, `id_bahan`, `id_warna`, `id_motif`, `stok`) VALUES
('1001-2001-3001', 1001, 2001, 3001, 15),
('1001-2001-3004', 1001, 2001, 3004, 0),
('1001-2002-3001', 1001, 2002, 3001, 15),
('1001-2004-3001', 1001, 2004, 3001, 0),
('1001-2005-3001', 1001, 2005, 3001, 0),
('1001-2006-3002', 1001, 2006, 3002, 0),
('1002-2001-3001', 1002, 2001, 3001, 29),
('1002-2001-3004', 1002, 2001, 3004, 0),
('1002-2002-3001', 1002, 2002, 3001, 15),
('1002-2004-3001', 1002, 2004, 3001, 0),
('1002-2006-3002', 1002, 2006, 3002, 0),
('1003-2001-3001', 1003, 2001, 3001, 15),
('1003-2001-3004', 1003, 2001, 3004, 0),
('1003-2002-3001', 1003, 2002, 3001, 20),
('1003-2002-3002', 1003, 2002, 3002, 20),
('1003-2003-3001', 1003, 2003, 3001, 18),
('1003-2003-3002', 1003, 2003, 3002, 13),
('1003-2004-3001', 1003, 2004, 3001, 0),
('1003-2006-3002', 1003, 2006, 3002, 0),
('1005-2001-3002', 1005, 2001, 3002, 10),
('1005-2002-3002', 1005, 2002, 3002, 69),
('1005-2003-3002', 1005, 2003, 3002, 39),
('1005-2004-3001', 1005, 2004, 3001, 0),
('1006-2004-3001', 1006, 2004, 3001, 0),
('1007-2001-3001', 1007, 2001, 3001, 0),
('1007-2002-3001', 1007, 2002, 3001, 0),
('1007-2003-3001', 1007, 2003, 3001, 0),
('1008-2001-3001', 1008, 2001, 3001, 0),
('1009-2001-3002', 1009, 2001, 3002, 0),
('1009-2002-3002', 1009, 2002, 3002, 0),
('1009-2003-3002', 1009, 2003, 3002, 0),
('1010-2001-3002', 1010, 2001, 3002, 0),
('1010-2002-3002', 1010, 2002, 3002, 0),
('1010-2003-3002', 1010, 2003, 3002, 0),
('1010-2004-3002', 1010, 2004, 3002, 0),
('1010-2005-3002', 1010, 2005, 3002, 0);

--
-- Triggers `kain_toko`
--
DELIMITER $$
CREATE TRIGGER `delete_kain_toko` AFTER DELETE ON `kain_toko` FOR EACH ROW BEGIN
DELETE FROM kain WHERE id_kain = OLD.id_kain;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `id_user` int(10) NOT NULL,
  `id_kain` varchar(64) NOT NULL,
  `quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `keranjang`
--

INSERT INTO `keranjang` (`id_user`, `id_kain`, `quantity`) VALUES
(3, '1002-2001-3001', 5),
(22, 'CUSTOM-6', 2);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id_message` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `id_pengirim` int(10) NOT NULL,
  `message` varchar(256) NOT NULL,
  `waktu` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id_message`, `id_user`, `id_pengirim`, `message`, `waktu`) VALUES
(1, 1, 1, 'Test 1234', '2022-11-20 03:00:05'),
(2, 1, 1, 'Test 456', '2022-11-20 03:00:13'),
(3, 1, 1, 'Hello Admin', '2022-11-20 03:04:46'),
(4, 1, 4, 'Hello User1', '2022-11-20 04:12:23'),
(5, 1, 4, 'Apa kabar user 1?', '2022-11-20 04:14:17'),
(6, 1, 1, 'Sehat bang Owner', '2022-11-20 04:14:36'),
(7, 3, 4, 'Halo user2', '2022-11-20 04:15:16'),
(8, 3, 17, 'user2 jawab dong', '2022-11-20 04:18:02'),
(9, 1, 1, 'Woy bang owner bangun', '2022-11-20 08:24:30'),
(10, 1, 1, 'Jawab pertanyaan gw ', '2022-11-20 08:24:38'),
(11, 1, 4, 'Ya kenapa?', '2022-11-20 08:26:52'),
(12, 1, 1, 'Woy Admin', '2022-11-21 14:41:24'),
(13, 1, 1, 'Kebaca gk?', '2022-11-21 14:41:33'),
(14, 1, 4, 'Gk bro', '2022-11-21 14:43:03'),
(15, 1, 4, 'Bye', '2022-11-21 14:43:22'),
(16, 1, 1, 'Bye', '2022-11-21 16:57:18'),
(17, 1, 17, 'Bye', '2022-11-21 17:02:14'),
(18, 22, 22, 'Halo Admin', '2022-11-22 00:20:35'),
(19, 22, 17, 'Halo Asep', '2022-11-22 00:21:55');

-- --------------------------------------------------------

--
-- Table structure for table `motif`
--

CREATE TABLE `motif` (
  `id_motif` int(10) NOT NULL,
  `nama_motif` varchar(128) NOT NULL,
  `harga_motif` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `motif`
--

INSERT INTO `motif` (`id_motif`, `nama_motif`, `harga_motif`) VALUES
(3001, 'Abstrak', 5000),
(3002, 'Polos', 2000),
(3003, 'Zebra', 8000),
(3004, 'Zig-Zag', 5000);

--
-- Triggers `motif`
--
DELIMITER $$
CREATE TRIGGER `del_motif` BEFORE DELETE ON `motif` FOR EACH ROW BEGIN
DELETE FROM kain_toko WHERE id_motif = OLD.id_motif;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `progress` enum('DIBUAT','DIKEMAS','DIKIRIM','SELESAI') NOT NULL,
  `tipe_bayar` enum('CASH','OVO','GOPAY','COD','TRANSFER') NOT NULL,
  `tipe_pengiriman` int(3) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `waktu_transaksi` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_bayar` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `progress`, `tipe_bayar`, `tipe_pengiriman`, `alamat`, `waktu_transaksi`, `total_bayar`) VALUES
(1, 1, 'DIKIRIM', 'OVO', 0, 'Jalan 1234, Bandung', '2022-11-17 09:29:20', 11000),
(2, 1, 'DIKEMAS', 'TRANSFER', 3, 'Jl. 123', '2022-11-21 14:33:30', 23000),
(3, 1, 'DIKEMAS', 'OVO', 1, 'Jalan Dipati Ukur No. 80', '2022-11-21 14:35:56', 14500),
(4, 3, 'DIKIRIM', 'OVO', 3, 'Ambil di toko', '2022-11-21 16:02:44', 16000),
(5, 3, 'DIKIRIM', 'OVO', 3, 'Ambil di toko', '2022-11-21 16:06:54', 48000),
(6, 1, 'DIBUAT', 'CASH', 3, 'Ambil di toko', '2022-11-21 16:34:52', 10000),
(7, 1, 'DIBUAT', 'COD', 4, 'Jl. 123', '2022-11-21 16:37:21', 11000),
(8, 1, 'DIBUAT', 'TRANSFER', 0, 'Jl. Banceuy No. 44', '2022-11-21 17:33:49', 18000),
(9, 6, 'DIBUAT', 'TRANSFER', 2, '1', '2022-11-21 22:52:51', 38000),
(10, 6, 'DIBUAT', 'CASH', 3, 'Ambil di toko', '2022-11-21 22:58:04', 65000),
(11, 6, 'DIBUAT', 'GOPAY', 0, '1', '2022-11-21 22:59:07', 18000),
(12, 6, 'DIBUAT', 'GOPAY', 1, '1', '2022-11-21 22:59:29', 68000),
(13, 7, 'DIBUAT', 'TRANSFER', 4, 'Jalan 123 No. DEF', '2022-11-21 23:00:46', 64000),
(14, 7, 'DIBUAT', 'GOPAY', 0, '1234', '2022-11-21 23:01:24', 72000),
(15, 22, 'SELESAI', 'TRANSFER', 3, 'Ambil di toko', '2022-11-22 00:18:20', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(10) NOT NULL,
  `username` varchar(128) NOT NULL,
  `nama_lengkap` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `no_telpon` varchar(32) NOT NULL,
  `status` enum('CUSTOMER','ADMIN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `nama_lengkap`, `email`, `password`, `alamat`, `no_telpon`, `status`) VALUES
(1, 'user1', 'Ahmad Supratman', 'amd123@gmail.com', '1234', 'Jl. Banceuy No. 44', '081312383456', 'CUSTOMER'),
(2, 'admin1', 'Jeffri', 'jeff@gmail.com', '1234', '', '', 'ADMIN'),
(3, 'user2', 'Josef', 'ahmada1234@gmail.com', '1234', 'Jalan 1234', '08131384756', 'CUSTOMER'),
(4, 'Owner', 'Ahmada Daisuki', 'owner@gmail.com', '1234', '', '', 'ADMIN'),
(5, 'admin2', 'Jakantoro', 'jkto@gmail.com', '1234', '', '', 'ADMIN'),
(6, 'user3', 'Askold', 'gmail.com', '1234', '1', '1', 'CUSTOMER'),
(7, 'user4', 'A', 'A', '1234', '1234', '1234', 'CUSTOMER'),
(8, 'dewa', 'dewa', '1', '1234', '1', '1234666', 'CUSTOMER'),
(16, 'admin3', 'Jozef', 'joz2348@gmail.com', '1234', '', '', 'ADMIN'),
(17, 'cs1', 'Janssen', 'janskaya@gmail.com', '1234', '', '', 'ADMIN'),
(18, 'user5', 'Polkadot', 'polk@gmail.com', '1234', 'Jl. 3456', '0813135678', 'CUSTOMER'),
(19, 'user6', '1234', '122232gmail.com', '1234', 'Jl. Fer No. 2456', '092746567', 'CUSTOMER'),
(20, 'user7', 'Jeffri Santoso', 'jefr@gmail.com', '1234', 'Jl. ABC no.566', '081335555543', 'CUSTOMER'),
(21, 'cs2', 'Rafael', 'raff@gmail.com', '1234', '', '', 'ADMIN'),
(22, 'Asep', 'Asep Asep', 'asep@gmail.com', '12345', 'Jl. ABC No. 456', '08139485763', 'CUSTOMER');

-- --------------------------------------------------------

--
-- Table structure for table `warna`
--

CREATE TABLE `warna` (
  `id_warna` int(10) NOT NULL,
  `nama_warna` varchar(128) NOT NULL,
  `harga_warna` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `warna`
--

INSERT INTO `warna` (`id_warna`, `nama_warna`, `harga_warna`) VALUES
(2001, 'Merah', 5000),
(2002, 'Kuning', 5000),
(2003, 'Hijau', 5000),
(2004, 'Biru', 5000),
(2005, 'Putih', 5000),
(2006, 'Rainbow', 8000);

--
-- Triggers `warna`
--
DELIMITER $$
CREATE TRIGGER `del_warna` BEFORE DELETE ON `warna` FOR EACH ROW BEGIN
DELETE FROM kain_toko WHERE id_warna = OLD.id_warna;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `bahan`
--
ALTER TABLE `bahan`
  ADD PRIMARY KEY (`id_bahan`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_transaksi`,`id_kain`),
  ADD KEY `id_kain` (`id_kain`);

--
-- Indexes for table `kain`
--
ALTER TABLE `kain`
  ADD PRIMARY KEY (`id_kain`);

--
-- Indexes for table `kain_custom`
--
ALTER TABLE `kain_custom`
  ADD PRIMARY KEY (`id_kain`);

--
-- Indexes for table `kain_toko`
--
ALTER TABLE `kain_toko`
  ADD PRIMARY KEY (`id_kain`),
  ADD KEY `id_bahan` (`id_bahan`),
  ADD KEY `id_warna` (`id_warna`),
  ADD KEY `id_motif` (`id_motif`);

--
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`id_user`,`id_kain`),
  ADD KEY `id_kain` (`id_kain`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id_message`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_pengirim` (`id_pengirim`);

--
-- Indexes for table `motif`
--
ALTER TABLE `motif`
  ADD PRIMARY KEY (`id_motif`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `warna`
--
ALTER TABLE `warna`
  ADD PRIMARY KEY (`id_warna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bahan`
--
ALTER TABLE `bahan`
  MODIFY `id_bahan` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1011;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id_message` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `motif`
--
ALTER TABLE `motif`
  MODIFY `id_motif` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3005;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `warna`
--
ALTER TABLE `warna`
  MODIFY `id_warna` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2007;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);

--
-- Constraints for table `kain_custom`
--
ALTER TABLE `kain_custom`
  ADD CONSTRAINT `kain_custom_ibfk_1` FOREIGN KEY (`id_kain`) REFERENCES `kain` (`id_kain`);

--
-- Constraints for table `kain_toko`
--
ALTER TABLE `kain_toko`
  ADD CONSTRAINT `kain_toko_ibfk_1` FOREIGN KEY (`id_kain`) REFERENCES `kain` (`id_kain`),
  ADD CONSTRAINT `kain_toko_ibfk_2` FOREIGN KEY (`id_bahan`) REFERENCES `bahan` (`id_bahan`),
  ADD CONSTRAINT `kain_toko_ibfk_3` FOREIGN KEY (`id_warna`) REFERENCES `warna` (`id_warna`),
  ADD CONSTRAINT `kain_toko_ibfk_4` FOREIGN KEY (`id_motif`) REFERENCES `motif` (`id_motif`);

--
-- Constraints for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD CONSTRAINT `keranjang_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `keranjang_ibfk_2` FOREIGN KEY (`id_kain`) REFERENCES `kain` (`id_kain`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`id_pengirim`) REFERENCES `users` (`id_user`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
