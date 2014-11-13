-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 20 dec 2013 om 15:28
-- Serverversie: 5.6.12-log
-- PHP-versie: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `javadb`
--
CREATE DATABASE IF NOT EXISTS `javadb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `javadb`;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `competentie`
--

CREATE TABLE IF NOT EXISTS `competentie` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `beschrijving` varchar(80) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Gegevens worden uitgevoerd voor tabel `competentie`
--

INSERT INTO `competentie` (`ID`, `beschrijving`) VALUES
(1, 'Gegevens behandelen'),
(2, 'Analyseren'),
(3, 'Oplossing uitwerken'),
(4, 'Beheren'),
(5, 'Projectmatig werken'),
(6, 'Communiceren'),
(7, 'Zijn eigen gedrag aanpassen'),
(8, 'Kwaliteitsvol handelen'),
(9, 'Koekjes bakken');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `competentie_deelcompetentie`
--

CREATE TABLE IF NOT EXISTS `competentie_deelcompetentie` (
  `compID` int(11) NOT NULL,
  `deelcompID` int(11) NOT NULL,
  PRIMARY KEY (`compID`,`deelcompID`),
  KEY `deelcomp_ON_deelcomp` (`deelcompID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `competentie_deelcompetentie`
--

INSERT INTO `competentie_deelcompetentie` (`compID`, `deelcompID`) VALUES
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(4, 15),
(4, 16),
(4, 17),
(5, 18),
(5, 19),
(5, 20),
(5, 21),
(9, 39),
(9, 40),
(9, 41),
(9, 42),
(9, 43),
(9, 45),
(9, 46),
(6, 48);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `deelcompetentie`
--

CREATE TABLE IF NOT EXISTS `deelcompetentie` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `beschrijving` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Gegevens worden uitgevoerd voor tabel `deelcompetentie`
--

INSERT INTO `deelcompetentie` (`ID`, `beschrijving`) VALUES
(2, 'Gegevens modelleren'),
(3, 'Verbanden leggen'),
(4, 'Gegevens opslaan (uitzuiveren, converteren, stockeren'),
(5, 'Gegevens ter beschikking stellen'),
(6, 'De informatiebehoeften van een organisatie identificeren'),
(7, 'Deze informatiebehoeften vertalen in concepten, schema''s en relaties'),
(8, 'De mogelijke oplossingen documenteren'),
(9, 'De mogelijke oplossingen evalueren'),
(10, 'Testprocedures opstellen'),
(11, 'Programmeren(gestructureerd, object georiënteerd, gedistribueerd'),
(12, 'Implementeren'),
(13, 'Documenteren'),
(14, 'Testen(op het niveau van deelaspecten en integratie'),
(15, 'Systemen configureren, beveiligen en aanpassen'),
(16, 'Toepassingen configureren, beveiligen en aanpassen'),
(17, 'Databanken configureren, beveiligen en aanpassen'),
(18, 'Het project splitsen in taken en deeltaken'),
(19, 'Planningen opstellen(tijds-, budget-, middelenplanning'),
(20, 'Een project opvolgen(tijd, budget en middelen'),
(21, 'In een multidisciplinair/-cultureel team werken, als lid of leider'),
(39, 'Deeg rollen'),
(40, 'Deeg kneden'),
(41, 'De oven voorverwarmen'),
(42, 'Ingrediënten kopen'),
(43, 'Vormen maken'),
(45, 'Deeg in vormen steken'),
(46, 'Ingrediënten voorbereiden'),
(48, 'Afspraken maken');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `indicator`
--

CREATE TABLE IF NOT EXISTS `indicator` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `beschrijving` varchar(80) NOT NULL,
  `deelcompID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `deelcompID` (`deelcompID`),
  KEY `deelcompID_2` (`deelcompID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Gegevens worden uitgevoerd voor tabel `indicator`
--

INSERT INTO `indicator` (`ID`, `beschrijving`, `deelcompID`) VALUES
(11, 'kan een sterretje maken', 43),
(12, 'kan een beertje maken', 43),
(13, 'Een zonnetje maken', 43),
(19, 'Duidelijke afspraken', 48),
(20, 'Afspraken naleven', 48),
(21, 'Modelletjes maken', 2),
(22, 'Datums kunnen kiezen', 48);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `indicatorpartim`
--

CREATE TABLE IF NOT EXISTS `indicatorpartim` (
  `indID` int(11) NOT NULL,
  `partID` int(11) NOT NULL,
  PRIMARY KEY (`indID`,`partID`),
  KEY `partim_ON_partim` (`partID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `indicatorpartim`
--

INSERT INTO `indicatorpartim` (`indID`, `partID`) VALUES
(22, 1),
(11, 2),
(12, 2),
(21, 2),
(13, 6),
(19, 9),
(20, 9);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `module`
--

CREATE TABLE IF NOT EXISTS `module` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(100) NOT NULL,
  `oplID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `opl_ON_mod` (`oplID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Gegevens worden uitgevoerd voor tabel `module`
--

INSERT INTO `module` (`ID`, `naam`, `oplID`) VALUES
(3, 'ICT-management I', 1),
(4, 'Informatiesystemen II', 1),
(5, 'Webontwikkeling III', 1),
(6, 'Webbeveiliging II', 1),
(7, 'Softwareontwikkeling III', 1),
(8, 'Secure web development', 1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `opleiding`
--

CREATE TABLE IF NOT EXISTS `opleiding` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Gegevens worden uitgevoerd voor tabel `opleiding`
--

INSERT INTO `opleiding` (`ID`, `naam`) VALUES
(1, 'Toegepaste Informatica');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `partim`
--

CREATE TABLE IF NOT EXISTS `partim` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` text NOT NULL,
  `modID` int(11) NOT NULL COMMENT 'FK voor Module ID',
  PRIMARY KEY (`ID`),
  KEY `PARTIM_ON_MODULE` (`modID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Gegevens worden uitgevoerd voor tabel `partim`
--

INSERT INTO `partim` (`ID`, `naam`, `modID`) VALUES
(1, 'ICT-georiënteerd bedrijfsbeleid', 3),
(2, 'Integretatieproject', 3),
(3, 'Statistiek en datamining', 3),
(4, 'Datageoriënteerde systeemanalyse', 4),
(5, 'Wetgeving i.v.m. privacy en databanken', 4),
(6, 'Databanken (MSQL Server)', 4),
(7, 'Integratieproject', 4),
(8, 'XML en webservices', 5),
(9, 'Informaticarecht', 6),
(10, 'Java', 7),
(11, 'C#', 7),
(12, 'Programmeerproject', 7),
(13, 'Security techniques for web development', 8),
(14, 'Honeypot', 8);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `familienaam` varchar(50) NOT NULL,
  `voornaam` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Gegevens worden uitgevoerd voor tabel `student`
--

INSERT INTO `student` (`ID`, `familienaam`, `voornaam`) VALUES
(1, 'Schelstraete', 'Bryan'),
(2, 'Chou', 'Zhiyuan'),
(3, 'Chambaere', 'Yentl'),
(4, 'Collignon', 'Yann');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `studentprestatie`
--

CREATE TABLE IF NOT EXISTS `studentprestatie` (
  `studID` int(11) NOT NULL,
  `indID` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`studID`,`indID`),
  KEY `indID_ON_indicator` (`indID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `studentprestatie`
--

INSERT INTO `studentprestatie` (`studID`, `indID`, `score`) VALUES
(1, 11, 12),
(1, 12, 10),
(1, 19, 17),
(1, 20, 12),
(1, 21, 17),
(2, 11, 6),
(2, 12, 12);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `student_in_module`
--

CREATE TABLE IF NOT EXISTS `student_in_module` (
  `studID` int(11) NOT NULL,
  `modID` int(11) NOT NULL,
  PRIMARY KEY (`studID`,`modID`),
  KEY `modID_ON_module` (`modID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `student_in_module`
--

INSERT INTO `student_in_module` (`studID`, `modID`) VALUES
(1, 3),
(2, 3),
(3, 3),
(1, 4),
(2, 4),
(3, 4),
(1, 5),
(2, 5),
(1, 6),
(2, 6),
(1, 7),
(2, 7),
(1, 8),
(2, 8);

--
-- Beperkingen voor gedumpte tabellen
--

--
-- Beperkingen voor tabel `competentie_deelcompetentie`
--
ALTER TABLE `competentie_deelcompetentie`
  ADD CONSTRAINT `competentie_ON_competentie` FOREIGN KEY (`compID`) REFERENCES `competentie` (`ID`),
  ADD CONSTRAINT `deelcomp_ON_deelcomp` FOREIGN KEY (`deelcompID`) REFERENCES `deelcompetentie` (`ID`);

--
-- Beperkingen voor tabel `indicator`
--
ALTER TABLE `indicator`
  ADD CONSTRAINT `indicator_ON_deelcomp` FOREIGN KEY (`deelcompID`) REFERENCES `deelcompetentie` (`ID`);

--
-- Beperkingen voor tabel `indicatorpartim`
--
ALTER TABLE `indicatorpartim`
  ADD CONSTRAINT `indicator_ON_indicator` FOREIGN KEY (`indID`) REFERENCES `indicator` (`ID`),
  ADD CONSTRAINT `partim_ON_partim` FOREIGN KEY (`partID`) REFERENCES `partim` (`ID`);

--
-- Beperkingen voor tabel `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `opl_ON_mod` FOREIGN KEY (`oplID`) REFERENCES `opleiding` (`ID`);

--
-- Beperkingen voor tabel `partim`
--
ALTER TABLE `partim`
  ADD CONSTRAINT `PARTIM_ON_MODULE` FOREIGN KEY (`modID`) REFERENCES `module` (`ID`);

--
-- Beperkingen voor tabel `studentprestatie`
--
ALTER TABLE `studentprestatie`
  ADD CONSTRAINT `indID_ON_indicator` FOREIGN KEY (`indID`) REFERENCES `indicator` (`ID`);

--
-- Beperkingen voor tabel `student_in_module`
--
ALTER TABLE `student_in_module`
  ADD CONSTRAINT `modID_ON_module` FOREIGN KEY (`modID`) REFERENCES `module` (`ID`),
  ADD CONSTRAINT `studID_on_student` FOREIGN KEY (`studID`) REFERENCES `student` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
