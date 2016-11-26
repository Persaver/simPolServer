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
-- Contenu de la table `budget`
--

INSERT INTO `budget` (`id`, `ageTravail`, `ageRetraite`, `chargeSalariale`, `chargeCadre`, `salaireStandard`, `salaireCadre`, `nbSalaries`, `nbCadres`, `backup`) VALUES
(1, 14, 70, 0, 0, 1000, 1500, 10, 1, 0),
(2, 18, 65, 0, 0, 1200, 2000, 6, 1, 0),
(3, 16, 65, 0, 0, 1200, 2250, 15, 2, 0);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`id`, `libelle`) VALUES
(1, 'education'),
(2, 'securite'),
(3, 'economie'),
(4, 'distraction'),
(5, 'sante');

-- --------------------------------------------------------

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
-- Contenu de la table `construction`
--

INSERT INTO `construction` (`id`, `designation`, `w`, `h`, `price`, `baseSalarie`, `baseCadre`, `baseRisque`, `baseAttractivite`, `modSalarie`, `modCadre`, `modRisque`, `modAttractivite`, `specificite`, `categorie`) VALUES
(1, 'maison', 1, 1, '80000.00', 0, 0, 0, 0, 0, 0, 0, 0, '', 3),
(2, 'musee', 1, 1, '99999.99', 3, 1, 0, 0, 0, 0, 0, 0, '', 4),
(3, 'restaurant', 1, 1, '99999.99', 3, 1, 0, 0, 0, 0, 0, 0, '', 4),
(4, 'bureau', 1, 1, '99999.99', 10, 1, 0, 0, 0, 0, 0, 0, '', 3),
(5, 'commissariat', 1, 1, '99999.99', 10, 2, 0, 0, 0, 0, 0, 0, '', 2),
(6, 'hopital', 1, 1, '99999.99', 15, 3, 0, 0, 0, 0, 0, 0, '', 5),
(7, 'entrepot', 1, 1, '75000.00', 10, 2, 0, 0, 0, 0, 0, 0, '', 3),
(8, 'eglise', 1, 1, '99999.99', 1, 1, 0, 0, 0, 0, 0, 0, '', 4),
(9, 'night_club', 1, 1, '50000.00', 1, 3, 0, 0, 0, 0, 0, 0, '', 4),
(10, 'temple', 1, 1, '99999.99', 1, 1, 0, 0, 0, 0, 0, 0, '', 4),
(11, 'temple_tower', 1, 1, '50000.00', 1, 1, 0, 0, 0, 0, 0, 0, '', 4),
(12, 'ferme', 1, 1, '10000.00', 1, 2, 0, 0, 0, 0, 0, 0, '', 3),
(13, 'Rescue_tower', 1, 1, '50000.00', 1, 5, 0, 0, 0, 0, 0, 0, '', 2),
(14, 'Circus', 1, 1, '99999.99', 1, 10, 0, 0, 0, 0, 0, 0, '', 4),
(15, 'Television', 1, 1, '99999.99', 3, 20, 0, 0, 0, 0, 0, 0, '', 3),
(16, 'Salle_de_spectacle', 1, 1, '99999.99', 1, 8, 0, 0, 0, 0, 0, 0, '', 4),
(17, 'Caserne', 1, 1, '75000.00', 1, 8, 0, 0, 0, 0, 0, 0, '', 2),
(18, 'Tour_militaire', 1, 1, '75000.00', 1, 8, 0, 0, 0, 0, 0, 0, '', 2),
(19, 'usine', 1, 1, '75000.00', 2, 20, 0, 0, 0, 0, 0, 0, '', 2),
(20, 'universite', 1, 1, '99999.99', 2, 20, 0, 0, 0, 0, 0, 0, '', 1);

-- --------------------------------------------------------

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
-- Contenu de la table `criminalite`
--

INSERT INTO `criminalite` (`id`, `indicMineur`, `crimeMineur`, `indicMoyen`, `crimeMoyen`, `indicGrave`, `crimeGrave`, `indicTerrorisme`, `crimeTerroriste`, `backup`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 0),
(2, 2, 2, 2, 2, 2, 2, 2, 2, 0),
(3, 3, 3, 3, 3, 3, 3, 3, 3, 0),
(4, 4, 4, 4, 4, 4, 4, 4, 4, 0),
(5, 5, 5, 5, 5, 5, 5, 5, 5, 0),
(6, 6, 6, 6, 6, 6, 6, 6, 6, 0),
(7, 7, 7, 7, 7, 7, 7, 7, 7, 0),
(8, 8, 8, 8, 8, 8, 8, 8, 8, 0),
(9, 9, 9, 9, 9, 9, 9, 9, 9, 0),
(10, 10, 10, 10, 10, 10, 10, 10, 10, 0);

-- --------------------------------------------------------

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
-- Contenu de la table `population`
--

INSERT INTO `population` (`id`, `fertilite`, `attractivite`, `popTab`, `backup`) VALUES
(1, 1, 1, '1000', 0),
(2, 2, 2, '15000', 0),
(3, 3, 3, '25000', 0),
(4, 4, 4, '40000', 0),
(5, 5, 5, '50000', 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`) VALUES
(1, 'Geoffrey', '0000'),
(2, 'Saverio', '0000'),
(3, 'Robin', '0000'),
(4, 'Ludovic', '0000');

--
-- Contraintes pour les tables exportées
--

--
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
