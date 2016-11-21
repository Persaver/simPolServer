DROP DATABASE IF EXISTS simpol;
CREATE DATABASE simpol
DEFAULT CHARACTER SET utf8;

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
  user INT(3) NOT NULL
)ENGINE=InnoDB;

DROP TABLE IF EXISTS categorie;
CREATE TABLE categorie (
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  libelle VARCHAR(30) NOT NULL
)ENGINE=InnoDB;

DROP TABLE IF EXISTS construction;
CREATE TABLE construction (
  id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(30) NOT NULL,
  w INT(3) NOT NULL,
  h INT(3) NOT NULL,
  price DECIMAL(7,2) NOT NULL,
  categorie INT(3) NOT NULL
)ENGINE=InnoDB;

DROP TABLE IF EXISTS backup_construction;
CREATE TABLE backup_construction(
  backup INT(4) NOT NULL,
  construction INT(3) NOT NULL,
  x INT(5) NOT NULL,
  y INT(5) NOT NULL,
  nbSalarie INT(5),
  nbCadres INT(5),
  risque INT(5),
  budget INT(5),
  attractive INT(5),
  postePourvu INT(5),
  PRIMARY KEY (backup, construction)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS backup_categorie;
CREATE TABLE backup_categorie(
  backup INT(4) NOT NULL,
  categorie INT(3) NOT NULL,
  PRIMARY KEY (backup, categorie)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS sprite;
CREATE TABLE sprite (
  id INT(4) NOT NULL,
  url VARCHAR(50) NOT NULL,
  name VARCHAR(30) NOT NULL,
  x INT(5) NOT NULL,
  y INT(5) NOT NULL,
  w INT(5) NOT NULL,
  h INT(5) NOT NULL
)ENGINE=InnoDB;


/******ADD BY GEOFFREY*******/


DROP TABLE IF EXISTS commissariat;
CREATE TABLE commissariat (
  pInfluence INT NOT NULL,
  tolerance INT,
  recette INT,
  indicecom INT,
  /**Voir array list****/
)ENGINE=InnoDB;

DROP TABLE IF EXISTS ecole;
/*****pas d attribut*****/


DROP TABLE IF EXISTS batiment;
/*****pas d attribut*****/

DROP TABLE IF EXISTS budget;
CREATE TABLE budget (
 ageTravail INT,
 ageRetraite INT,
 chargeSalariale INT,
 chargeCadre INT,
 salaireStandard INT,
 salaireCadre INT,
 nbSalaries INT,
 nbCadres INT
  
)ENGINE=InnoDB;

DROP TABLE IF EXISTS criminalite;
CREATE TABLE criminalite (
indicMineur INT,
crimeMineur INT,
indicMoyen INT,
crimeMoyen INT,
indicGrave INT,
crimeGrave INT,
indicTerrorisme INT,
crimeTerroriste INT
  
)ENGINE=InnoDB;

DROP TABLE IF EXISTS population;
fertilite INT,
attractivite INT,
/**** manque un attribut tableau***/



ALTER TABLE backup
ADD FOREIGN KEY(user) REFERENCES user (id);
ALTER TABLE construction
ADD FOREIGN KEY(categorie) REFERENCES categorie (id);
ALTER TABLE backup_construction
ADD FOREIGN KEY(backup) REFERENCES backup (id);
ALTER TABLE backup_construction
ADD FOREIGN KEY(construction) REFERENCES construction (id);
ALTER TABLE backup_categorie
ADD FOREIGN KEY(categorie) REFERENCES categorie (id);
ALTER TABLE backup_categorie
ADD FOREIGN KEY(backup) REFERENCES backup (id);
