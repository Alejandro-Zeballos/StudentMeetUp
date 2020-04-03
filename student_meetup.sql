-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2020 at 07:18 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_meetup`
--

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id_report` int(11) NOT NULL,
  `id_student_reported` int(11) NOT NULL,
  `id_student_reportee` int(11) NOT NULL,
  `reason` enum('abusive','spamming_bot','','') NOT NULL,
  `desc_reason` varchar(220) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `id_session` int(11) NOT NULL,
  `course` varchar(20) NOT NULL,
  `session_title` varchar(220) NOT NULL,
  `session_admin` varchar(220) NOT NULL,
  `session_date` date NOT NULL,
  `session_time` time NOT NULL,
  `session_location` varchar(220) NOT NULL,
  `session_tags` varchar(220) NOT NULL,
  `session_descr` varchar(220) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`id_session`, `course`, `session_title`, `session_admin`, `session_date`, `session_time`, `session_location`, `session_tags`, `session_descr`) VALUES
(33, '', 'PHP', 'Sue', '2020-03-31', '21:10:00', 'Library', 'PHP', 'Hi guys, I\'ll be in the library today studying PHP. If anyone would like to join me (:'),
(34, '', 'JAVA', '', '2020-03-31', '21:15:00', 'Library', '62S6', 'Hi guys, I\'ll be in the library today studying JAVA. If anyone would like to join me (:'),
(35, '', 'NETWORK', '', '2020-03-31', '20:00:00', 'Library', '2s2s', 'Hi guys, I\'ll be in the library today studying Network. If anyone would like to join me (:'),
(41, '', 'JAVASCRIPT', '', '2020-04-02', '16:00:00', 'Library', 'javascript,web', 'Hi guys, I\'ll be in the library today studying JavaScript. If anyone would like to join me (:');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id_student` int(11) NOT NULL,
  `student_firstName` varchar(220) NOT NULL,
  `student_lastName` varchar(220) NOT NULL,
  `student_email` varchar(220) NOT NULL,
  `student_password` varchar(220) NOT NULL,
  `student_course` varchar(30) NOT NULL,
  `student_description` varchar(220) NOT NULL,
  `student_foto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id_student`, `student_firstName`, `student_lastName`, `student_email`, `student_password`, `student_course`, `student_description`, `student_foto`) VALUES
(7, 'Suelen', '', 'suelenfiorelo@hotmail.com', '123', 'IT', 's', ''),
(16, 'Teste', 'Teste', 'teste@hotmail.com', '$2y$12$6reFRasEqxKZOnPlvEWK0.3NiAgsziyEIRT1ge8180RWGBoalVpyO', 'IT', 'teste', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id_report`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id_session`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_student`),
  ADD UNIQUE KEY `student_email` (`student_email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id_report` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `id_session` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id_student` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
