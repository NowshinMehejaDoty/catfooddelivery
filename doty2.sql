-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2023 at 11:47 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doty2`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_placed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `user_id`, `is_placed`) VALUES
(1, 2, 0),
(2, 2, 0),
(4, 1, 0),
(5, 4, 1),
(6, 4, 0),
(7, 5, 1),
(8, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cart_detail`
--

CREATE TABLE `cart_detail` (
  `id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart_detail`
--

INSERT INTO `cart_detail` (`id`, `cart_id`, `food_id`, `product_id`) VALUES
(1, 1, 1, 2),
(2, 1, 1, 1),
(3, 4, 2, 3),
(4, 4, 1, 2),
(5, 4, 1, 1),
(6, 4, 1, 2),
(7, 4, 1, 2),
(8, 4, 2, 3),
(9, 4, 2, 3),
(10, 4, 11, 5),
(11, 4, 4, 4),
(12, 4, 4, 4),
(13, 4, 4, 4),
(14, 5, 1, 1),
(15, 5, 2, 3),
(16, 5, 4, 4),
(17, 6, 2, 3),
(18, 7, 1, 1),
(19, 7, 1, 2),
(20, 7, 12, 6),
(21, 8, 4, 4),
(22, 8, 12, 6);

-- --------------------------------------------------------

--
-- Table structure for table `cart_details`
--

CREATE TABLE `cart_details` (
  `id` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `cart_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `name`, `type`) VALUES
(1, 'Chicken Cat Food', 1),
(2, 'Vegetable Cat Food', 0),
(3, 'Liquid Cat Food Best', 1),
(4, 'Premium', 0),
(6, 'GG', 0),
(7, 'BB', 0),
(8, 'DD', 1),
(9, 'JJ', 0),
(10, 'MM', 0),
(11, 'fish', 0),
(12, 'tuna fish', 0),
(13, 'fish fillet', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Table structure for table `placement`
--

CREATE TABLE `placement` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `total_price` double NOT NULL,
  `payment_method` int(11) NOT NULL,
  `house_no` varchar(256) DEFAULT NULL,
  `road_no` varchar(256) DEFAULT NULL,
  `district` varchar(256) DEFAULT NULL,
  `cust_name` varchar(256) NOT NULL,
  `cust_mobile` varchar(256) NOT NULL,
  `cart_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `placement`
--

INSERT INTO `placement` (`id`, `user_id`, `total_price`, `payment_method`, `house_no`, `road_no`, `district`, `cust_name`, `cust_mobile`, `cart_id`) VALUES
(1, 2, 320, 0, NULL, NULL, NULL, '', '', 0),
(2, 4, 6340, 1, 'House120', 'Road 20', 'Dhaka', 'Gittu', '0111111111', 5),
(3, 5, 860, 0, 'basundhara', 'ggtt', 'hh', 'qqq', '2321', 7),
(4, 6, 6440, 0, 'House120', 'Road 20', 'Dhaka', 'jab', '0111111111', 8);

-- --------------------------------------------------------

--
-- Table structure for table `placement_detail`
--

CREATE TABLE `placement_detail` (
  `id` int(11) NOT NULL,
  `placement_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `placement_detail`
--

INSERT INTO `placement_detail` (`id`, `placement_id`, `food_id`, `product_id`, `price`) VALUES
(1, 1, 1, 1, 320),
(2, 2, 1, 1, 320),
(3, 2, 2, 3, 20),
(4, 2, 4, 4, 6000),
(5, 3, 1, 1, 320),
(6, 3, 1, 2, 100),
(7, 3, 12, 6, 440),
(8, 4, 4, 4, 6000),
(9, 4, 12, 6, 440);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `price` double NOT NULL,
  `food_id_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `food_id`, `name`, `price`, `food_id_id`) VALUES
(1, 1, 'Product 12', 320, NULL),
(2, 1, 'Product 2', 100, NULL),
(3, 2, 'Veg Product', 20, NULL),
(4, 4, 'Ocean White Fish & Tuna Shreds (Small)', 6000, NULL),
(5, 11, 'Ocean White Fish & Tuna Shreds', 550, NULL),
(6, 12, 'tuna can', 440, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `type`) VALUES
(1, 'doty', '123', 0),
(2, 'customer', '123', 1),
(3, 'asd', 'asd', 1),
(4, 'gittu', '123', 1),
(5, 'bb', 'hh', 1),
(6, 'jab', 'jab', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`);

--
-- Indexes for table `cart_detail`
--
ALTER TABLE `cart_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrg4yopd2252nwj8bfcgq5f4jp` (`cart_id`),
  ADD KEY `FKrvqg5i1h18w17ocen2du1rov` (`food_id`),
  ADD KEY `FK37hai783jhfcqo6h0pkiqmc9s` (`product_id`);

--
-- Indexes for table `cart_details`
--
ALTER TABLE `cart_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhq1m50l0ke2fkqxxd6ubo3x4b` (`cart_id`),
  ADD KEY `FKcb3xmdk30xs8t823j5anobd` (`food_id`),
  ADD KEY `FKngo5q1x6m7sudq0m8ylo5prrh` (`product_id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `placement`
--
ALTER TABLE `placement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK81j5l2p7k43vvxq8hsuko7q6` (`user_id`);

--
-- Indexes for table `placement_detail`
--
ALTER TABLE `placement_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8cg6h4aspbico5g4c84wd3mrk` (`food_id`),
  ADD KEY `FK8i2oryn9vypqq03fmyp0ipk0w` (`placement_id`),
  ADD KEY `FKpnaecjiuwl378yhcj9cho88hl` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj8jnqi9qex5tcdl91wxt67ffy` (`food_id_id`),
  ADD KEY `FKn4s2fixfydfugrgx9uajlnrg9` (`food_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `cart_detail`
--
ALTER TABLE `cart_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `cart_details`
--
ALTER TABLE `cart_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `placement`
--
ALTER TABLE `placement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `placement_detail`
--
ALTER TABLE `placement_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cart_detail`
--
ALTER TABLE `cart_detail`
  ADD CONSTRAINT `FK37hai783jhfcqo6h0pkiqmc9s` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKrg4yopd2252nwj8bfcgq5f4jp` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `FKrvqg5i1h18w17ocen2du1rov` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`);

--
-- Constraints for table `placement`
--
ALTER TABLE `placement`
  ADD CONSTRAINT `FK81j5l2p7k43vvxq8hsuko7q6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `placement_detail`
--
ALTER TABLE `placement_detail`
  ADD CONSTRAINT `FK8cg6h4aspbico5g4c84wd3mrk` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `FK8i2oryn9vypqq03fmyp0ipk0w` FOREIGN KEY (`placement_id`) REFERENCES `placement` (`id`),
  ADD CONSTRAINT `FKpnaecjiuwl378yhcj9cho88hl` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FKj8jnqi9qex5tcdl91wxt67ffy` FOREIGN KEY (`food_id_id`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `FKn4s2fixfydfugrgx9uajlnrg9` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
