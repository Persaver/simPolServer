DROP DATABASE IF EXISTS simpol;
CREATE DATABASE simpol DEFAULT CHARACTER SET utf8;

USE simpol;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(30) NOT NULL,
  password VARCHAR(50) NOT NULL
)ENGINE=InnoDB;

DROP TABLE IF EXISTS backup;
CREATE TABLE backup (
  id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
  date_last DATETIME DEFAULT CURRENT_TIMESTAMP,
  nbj INT NOT NULL DEFAULT 0,
  user INT(3) NOT NULL,
  budget INT
)ENGINE=InnoDB;

DROP TABLE IF EXISTS categorie;
CREATE TABLE categorie (
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  libelle VARCHAR(30) NOT NULL
)ENGINE=InnoDB;

DROP TABLE IF EXISTS construction;
CREATE TABLE construction (
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  designation VARCHAR(30) NOT NULL,
  w INT(3) NOT NULL,
  h INT(3) NOT NULL,
  price DECIMAL(7,2) NOT NULL,
  baseSalarie INT(5) NOT NULL,
  baseCadre INT(5) NOT NULL,
  baseRisque INT(5) DEFAULT 0,
  baseAttractivite INT(5) NOT NULL,
  modSalarie INT(5) NOT NULL,
  modCadre INT(5) NOT NULL,
  modRisque INT(5) NOT NULL,
  modAttractivite INT(5) NOT NULL,
  specificite TEXT,
  categorie INT(3) NOT NULL
)ENGINE=InnoDB;

DROP TABLE IF EXISTS backup_construction;
CREATE TABLE backup_construction(
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  backup INT(4) NOT NULL,
  construction INT(3) NOT NULL,
  x INT(5) NOT NULL,
  y INT(5) NOT NULL,
  nbSalarie INT(5),
  nbCadre INT(5),
  risque INT(5),
  budget INT(5),
  attractivite INT(5),
  postePourvu INT(5), /*** string representant les spe en format json */
  specificite TEXT,
  niveau INT(2)
  )ENGINE=InnoDB;

/******ADD BY GEOFFREY*******/

DROP TABLE IF EXISTS budget;
CREATE TABLE budget (
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ageTravail INT,
  ageRetraite INT,
  chargeSalariale INT,
  chargeCadre INT,
  salaireStandard INT,
  salaireCadre INT,
  nbSalaries INT,
  nbCadres INT,
  nbj INT,
  backup INT(4),
  budget INT
)ENGINE=InnoDB;

DROP TABLE IF EXISTS criminalite;
CREATE TABLE criminalite (
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  indicMineur INT,
  crimeMineur INT,
  indicMoyen INT,
  crimeMoyen INT,
  indicGrave INT,
  crimeGrave INT,
  indicTerrorisme INT,
  crimeTerroriste INT,
  nbj INT,
  backup INT(4)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS population;
CREATE TABLE population(
id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
fertilite INT DEFAULT 29,
attractivite INT DEFAULT 0,
popTab TEXT,
nbj INT,
backup INT(4)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS education;
CREATE TABLE education (
id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
edTotale INT,
edSecurite INT,
edEntretien INT,
edSante INT,
edRecherche INT,
edTourisme INT,
nbj INT,
backup INT(4),
ratioSecurite INT(4),
ratioEntretien INT(4),
ratioSante INT(4),
ratioRecherche INT(4),
ratioTourisme INT(4)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS sante;
CREATE TABLE sante (
id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
hygiene INT,
nbMalades INT,
nbAccidents INT,
soins INT,
echecs INT,
nbj INT,
backup INT(4)
)ENGINE=InnoDB;


ALTER TABLE budget ADD FOREIGN KEY(backup) REFERENCES backup (id);
ALTER TABLE criminalite ADD FOREIGN KEY(backup) REFERENCES backup (id);
ALTER TABLE population ADD FOREIGN KEY(backup) REFERENCES backup (id);
ALTER TABLE education ADD FOREIGN KEY(backup) REFERENCES backup(id);
ALTER TABLE sante ADD FOREIGN KEY(backup) REFERENCES backup(id);
ALTER TABLE backup ADD FOREIGN KEY(user) REFERENCES user (id);
ALTER TABLE construction ADD FOREIGN KEY(categorie) REFERENCES categorie (id);
ALTER TABLE backup_construction ADD FOREIGN KEY(backup) REFERENCES backup (id);
ALTER TABLE backup_construction ADD FOREIGN KEY(construction) REFERENCES construction (id);
