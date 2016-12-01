use simpol;

INSERT INTO user (`id`,`login`,`password`)
VALUES (1,'user1','0000');
	  
INSERT INTO backup(`id`,`date_creation`,`date_last`,`nbj`,`user`)
VALUES (1,'2016-12-01','2016-12-02',`1`,`1`);

INSERT INTO `budget` (`id`, `ageTravail`, `ageRetraite`, `chargeSalariale`, `chargeCadre`, `salaireStandard`, `salaireCadre`, `nbSalaries`, `nbCadres`, `nbj`,`backup`) VALUES
(1, 14, 70, 0, 0, 1000, 1500, 100, 20,1,1);

INSERT INTO `categorie` (`id`, `libelle`) VALUES
(1, 'education'),
(2, 'securite'),
(3, 'economie'),
(4, 'distraction'),
(5, 'sante');

INSERT INTO `sante` (`id`,`hygiene`,`nbMalades`,`nbAccidents`,`soins`,`echecs`,`nbj`,`backup`) VALUES 
VALUES (1,1,20,2,5,1,1,1);

INSERT INTO `construction`(id,designation,w,h,price,baseSalarie,baseCadre,baseRisque,baseAttractivite,modSalarie,modCadre,modRisque,modAttractivite,specificite,categorie)
VALUES 
(1,'maison',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',3),
(2,'musee',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',1),
(3,'restaurant',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',4),
(4,'bureau',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',3),
(5,'commissariat',1,1,1000.0,120,20,4,1,0.5,0.5,2,1,'ras',2),
(6,'hopital',1,1,1000.0,70,10,3,2,0.7,0.8,1,2,'ras',5),
(7,'entrepot',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',3),
(8,'eglise',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',4),
(9,'night_club',1,1,1000.0,40,10,2,5,0.7,0.7,1,3,'ras',4),
(10,'temple',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',4),
(11,'temple_tower',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',4),
(12,'ferme',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',3),
(13,'Rescue_tower',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',2),
(14,'Circus',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',4),
(15,'Television',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',4),
(16,'Salle_de_spectacle',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',4),
(17,'Caserne',1,1,1000.0,80,20,0,1,0.3,0.1,0,1,'ras',2),
(18,'Tour_militaire',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',2),
(19,'usine',1,1,1000.0,0,0,0,0,0,0,0,0,'ras',3),
--ecole
(20,'universite',1,1,1000.0,40,10,3,1,0.5,0.2,2,1,'ras',1)
;

INSERT INTO `education` (`id`,`edTotale`,`edSecurite`,`edEntretien`,`edSante`,`edRecherche`,`edTourisme`,`nbj`,`backup`) 
VALUES (1,10,10,10,10,10,10,1,1);
--
--spe '{"truc":1}'
INSERT INTO `backup_construction` (`id`, backup,`construction`, `x`, `y`, `nbSalarie`, `nbCadres`, `risque`, `budget`, `attractive`, `postePourvu`, `specificite`) VALUES
(1,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(2,1,6, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(3,1,17, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(4,1,5, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(5,1,20, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(6,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(7,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(8,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(9,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(10,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(11,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(12,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(13,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(14,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}'),
(15,1,1, 1, 1, 0,0,1,1,1,0,'{"truc":1}');


INSERT INTO `criminalite` (`id`, `indicMineur`, `crimeMineur`, `indicMoyen`, `crimeMoyen`, `indicGrave`, `crimeGrave`, `indicTerrorisme`, `crimeTerroriste`, `nbj`, `backup`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

INSERT INTO `population` (`id`, `fertilite`, `attractivite`,`popTab`,`nbj`,`backup`) VALUES
(1, 1, 1, '1000',1, 1);
--
INSERT INTO `user` (`id`, `login`, `password`) VALUES
(1, 'user1', '0000');