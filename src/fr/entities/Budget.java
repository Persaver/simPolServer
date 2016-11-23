package fr.entities;


import java.sql.Date;

public class Budget {

	private int id;
	private int age;
	private int ageTravail;
	private int ageRetraite;
	private int chargeSalariale;
	private int chargeCadre;
	private int salaireStandard;
	private int salaireCadre;
	private int nbSalaries;
	private int nbCadres;
	private int backup;
	private Date date;


	public Budget(){

	}

	public Budget(int id, int age, int ageRetraite, int chargeSalariale, int chargeCadre, int salaireStandard,
			int salaireCadre, int nbSalaries, int nbCadres, int backup, Date date) {
		super();
		this.id = id;
		this.age = age;
		this.ageRetraite = ageRetraite;
		this.chargeSalariale = chargeSalariale;
		this.chargeCadre = chargeCadre;
		this.salaireStandard = salaireStandard;
		this.salaireCadre = salaireCadre;
		this.nbSalaries = nbSalaries;
		this.nbCadres = nbCadres;
		this.backup = backup;
		this.date = date;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeTravail() {
		return this.ageTravail;
	}

	public void setAgeTravail(int ageTravail) {
		this.ageTravail = ageTravail;
	}

	public int getAgeRetraite() {
		return this.ageRetraite;
	}
	public void setAgeRetraite(int ageRetraite) {
		this.ageRetraite = ageRetraite;
	}
	public int getChargeSalariale() {
		return this.chargeSalariale;
	}
	public void setChargeSalariale(int chargeSalariale) {
		this.chargeSalariale = chargeSalariale;
	}
	public int getChargeCadre() {
		return this.chargeCadre;
	}
	public void setChargeCadre(int chargeCadre) {
		this.chargeCadre = chargeCadre;
	}
	public int getSalaireStandard() {
		return this.salaireStandard;
	}
	public void setSalaireStandard(int salaireStandard) {
		this.salaireStandard = salaireStandard;
	}
	public int getSalaireCadre() {
		return this.salaireCadre;
	}
	public void setSalaireCadre(int salaireCadre) {
		this.salaireCadre = salaireCadre;
	}
	public int getNbSalaries() {
		return this.nbSalaries;
	}
	public void setNbSalaries(int nbSalaries) {
		this.nbSalaries = nbSalaries;
	}
	public int getNbCadres() {
		return this.nbCadres;
	}
	public void setNbCadres(int nbCadres) {
		this.nbCadres = nbCadres;
	}
	public int getBackup() {
		return this.backup;
	}
	public void setBackup(int backup) {
		this.backup = backup;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}