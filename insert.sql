use simpol;

INSERT INTO user (`id`,`login`,`password`)
VALUES (1,'user1','0000');
	  
INSERT INTO backup(`id`,`date_creation`,`date_last`,`nbj`,`user`)
VALUES (1,'2016-12-01','2016-12-02', ???);


INSERT INTO `budget` (`id`, `ageTravail`, `ageRetraite`, `chargeSalariale`, `chargeCadre`, `salaireStandard`, `salaireCadre`, `nbSalaries`, `nbCadres`, `nbj`,`backup`) VALUES
(1, 14, 70, 0, 0, 1000, 1500, ?,?,?,?);
--(2, 18, 65, 0, 0, 1200, 2000, 6, 1, 1),
--(3, 16, 65, 0, 0, 1200, 2250, 15, 2, );

INSERT INTO `categorie` (`id`, `libelle`) VALUES
(1, 'education'),
(2, 'securite'),
(3, 'economie'),
(4, 'distraction'),
(5, 'sante');

INSERT INTO `sante` (`id`,`hygiene`,`nbMalades`,`nbAccidents`,`soins`,`echecs`,`nbj`,`backup`) VALUES 
VALUES ();


INSERT INTO `education` (`id`,`edTotale`,`edSecurite`,`edEntretien`,`edSante`,`edRecherche`,`edTourisme`,`nbj`,`backup`) VALUES;
VALUES ();


INSERT INTO `backup_construction` (`id`, backup,`construction`, `x`, `y`, `nbSalarie`, `nbCadres`, `risque`, `budget`, `attractive`, `postePourvu`, `specificite`) VALUES
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

INSERT INTO `criminalite` (`id`, `indicMineur`, `crimeMineur`, `indicMoyen`, `crimeMoyen`, `indicGrave`, `crimeGrave`, `indicTerrorisme`, `crimeTerroriste`, `nbj`, `backup`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
/**(2, 2, 2, 2, 2, 2, 2, 2, 2, 0),
(3, 3, 3, 3, 3, 3, 3, 3, 3, 0),
(4, 4, 4, 4, 4, 4, 4, 4, 4, 0),
(5, 5, 5, 5, 5, 5, 5, 5, 5, 0),
(6, 6, 6, 6, 6, 6, 6, 6, 6, 0),
(7, 7, 7, 7, 7, 7, 7, 7, 7, 0),
(8, 8, 8, 8, 8, 8, 8, 8, 8, 0),
(9, 9, 9, 9, 9, 9, 9, 9, 9, 0),
(10, 10, 10, 10, 10, 10, 10, 10, 10, 0)****/;

INSERT INTO `population` (`id`, `fertilite`, `attractivite`,`popTab`,`nbj`,`backup`) VALUES
(1, 1, 1, '1000', 1),
/*(2, 2, 2, '15000', 0),
(3, 3, 3, '25000', 0),
(4, 4, 4, '40000', 0),
(5, 5, 5, '50000', 0)*/;

INSERT INTO `user` (`id`, `login`, `password`) VALUES
(1, 'user1', '0000'),
);