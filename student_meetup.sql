-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2020 at 10:59 PM
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
  `session_id_admin` int(11) NOT NULL,
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

INSERT INTO `session` (`id_session`, `course`, `session_title`, `session_id_admin`, `session_admin`, `session_date`, `session_time`, `session_location`, `session_tags`, `session_descr`) VALUES
(63, 'IT', 'JAVASCRIPT', 7, 'Suelen', '2020-04-04', '21:22:00', 'ss', 'ss', 'ss'),
(64, 'Business', 'java', 7, 'Sue', '2020-04-04', '21:30:00', 'ss', 'ss', 'ss'),
(65, 'Business', 'JAVASCRIPT', 19, 'admin', '2020-04-04', '21:45:00', 'aa', 'aa', 'aa');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id_student` int(11) NOT NULL,
  `student_firstName` varchar(220) NOT NULL,
  `student_nickname` varchar(220) NOT NULL,
  `student_email` varchar(220) NOT NULL,
  `student_password` varchar(220) NOT NULL,
  `student_course` varchar(30) NOT NULL,
  `student_description` varchar(220) NOT NULL,
  `student_foto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id_student`, `student_firstName`, `student_nickname`, `student_email`, `student_password`, `student_course`, `student_description`, `student_foto`) VALUES
(7, 'Suelen', 'Sue', 'suelenfiorelo@hotmail.com', '123', 'IT', 's', ''),
(16, 'Teste', 'Teste', 'teste@hotmail.com', '$2y$12$6reFRasEqxKZOnPlvEWK0.3NiAgsziyEIRT1ge8180RWGBoalVpyO', 'IT', 'teste', ''),
(24, 'Suelen', 'admin', 'admin@hotmail.com', '123', 'IT', '123', '');

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
  MODIFY `id_session` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id_student` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
