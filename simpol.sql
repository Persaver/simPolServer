-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 26 Novembre 2016 à 16:57
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
--
-- Base de données :  `simpol`
--
DROP DATABASE IF EXISTS simpol;
CREATE DATABASE simpol DEFAULT CHARACTER SET utf8;
USE simpol;
-- --------------------------------------------------------
--
-- Structure de la table `backup_construction`
--
CREATE TABLE IF NOT EXISTS `backup_construction` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `backup` int(4) NOT NULL,
  `construction` int(3) NOT NULL,
  `x` int(5) NOT NULL,
  `y` int(5) NOT NULL,
  `nbSalarie` int(5) DEFAULT NULL,
  `nbCadres` int(5) DEFAULT NULL,
  `risque` int(5) DEFAULT NULL,
  `budget` int(5) DEFAULT NULL,
  `attractive` int(5) DEFAULT NULL,
  `postePourvu` int(5) DEFAULT NULL,
  `specificite` text,
  PRIMARY KEY (`id`),
  KEY `construction` (`construction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
-- --------------------------------------------------------

-- Table backup
-- id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  -- date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
  -- date_last DATETIME DEFAULT CURRENT_TIMESTAMP,
  -- nbj INT(5) NOT NULL DEFAULT 0,
  -- user 
  -- INSERT INTO backup (nbj,user)
  -- VALUES(1,1);
  
--
-- Structure de la table `budget`
--
CREATE TABLE IF NOT EXISTS `budget` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `ageTravail` int(11) DEFAULT NULL,
  `ageRetraite` int(11) DEFAULT NULL,
  `chargeSalariale` int(11) DEFAULT NULL,
  `chargeCadre` int(11) DEFAULT NULL,
  `salaireStandard` int(11) DEFAULT NULL,
  `salaireCadre` int(11) DEFAULT NULL,
  `nbSalaries` int(11) DEFAULT NULL,
  `nbCadres` int(11) DEFAULT NULL,
  `backup` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Structure de la table `categorie`
--
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Structure de la table `construction`
--
CREATE TABLE IF NOT EXISTS `construction` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `designation` varchar(30) NOT NULL,
  `w` int(3) NOT NULL,
  `h` int(3) NOT NULL,
  `price` decimal(7,2) NOT NULL,
  `baseSalarie` int(5) NOT NULL,
  `baseCadre` int(5) NOT NULL,
  `baseRisque` int(5) DEFAULT '0',
  `baseAttractivite` int(5) NOT NULL,
  `modSalarie` int(5) NOT NULL,
  `modCadre` int(5) NOT NULL,
  `modRisque` int(5) NOT NULL,
  `modAttractivite` int(5) NOT NULL,
  `specificite` text,
  `categorie` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categorie` (`categorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Structure de la table `criminalite`
--
CREATE TABLE IF NOT EXISTS `criminalite` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `indicMineur` int(11) DEFAULT NULL,
  `crimeMineur` int(11) DEFAULT NULL,
  `indicMoyen` int(11) DEFAULT NULL,
  `crimeMoyen` int(11) DEFAULT NULL,
  `indicGrave` int(11) DEFAULT NULL,
  `crimeGrave` int(11) DEFAULT NULL,
  `indicTerrorisme` int(11) DEFAULT NULL,
  `crimeTerroriste` int(11) DEFAULT NULL,
  `backup` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Structure de la table `population`
--
CREATE TABLE IF NOT EXISTS `population` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `fertilite` int(11) DEFAULT '29',
  `attractivite` int(11) DEFAULT '0',
  `popTab` text,
  `backup` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Structure de la table `user`
--
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

-- Contraintes pour la table `backup_construction`
--
ALTER TABLE `backup_construction`
  ADD CONSTRAINT `backup_construction_ibfk_1` FOREIGN KEY (`construction`) REFERENCES `construction` (`id`);
--
-- Contraintes pour la table `construction`
--
ALTER TABLE `construction`
  ADD CONSTRAINT `construction_ibfk_1` FOREIGN KEY (`categorie`) REFERENCES `categorie` (`id`);
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;