package fr.entities;

public class Budget extends AbstractEntity<Budget,Integer> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer age;
	private Integer ageTravail;
	private Integer ageRetraite;
	private Integer chargeSalariale;
	private Integer chargeCadre;
	private Integer salaireStandard;
	private Integer salaireCadre;
	private Integer nbSalaries;
	private Integer nbCadres;
	private Backup backup;


	public Budget(){
	}

	public Budget(Integer id){
		super(id);
	}

	public Budget(Integer age, Integer ageRetraite, Integer chargeSalariale, Integer chargeCadre, Integer salaireStandard,
			Integer salaireCadre, Integer nbSalaries, Integer nbCadres, Backup backup) {
		this.age = age;
		this.ageRetraite = ageRetraite;
		this.chargeSalariale = chargeSalariale;
		this.chargeCadre = chargeCadre;
		this.salaireStandard = salaireStandard;
		this.salaireCadre = salaireCadre;
		this.nbSalaries = nbSalaries;
		this.nbCadres = nbCadres;
		this.backup = backup;
	}

	public Budget(Integer id, Integer age, Integer ageRetraite, Integer chargeSalariale, Integer chargeCadre, Integer salaireStandard,
			Integer salaireCadre, Integer nbSalaries, Integer nbCadres) {
		super(id);
		this.age = age;
		this.ageRetraite = ageRetraite;
		this.chargeSalariale = chargeSalariale;
		this.chargeCadre = chargeCadre;
		this.salaireStandard = salaireStandard;
		this.salaireCadre = salaireCadre;
		this.nbSalaries = nbSalaries;
		this.nbCadres = nbCadres;
	}

	public Budget(Integer id, Integer age, Integer ageRetraite, Integer chargeSalariale, Integer chargeCadre, Integer salaireStandard,
			Integer salaireCadre, Integer nbSalaries, Integer nbCadres, Backup backup) {
		super(id);
		this.age = age;
		this.ageRetraite = ageRetraite;
		this.chargeSalariale = chargeSalariale;
		this.chargeCadre = chargeCadre;
		this.salaireStandard = salaireStandard;
		this.salaireCadre = salaireCadre;
		this.nbSalaries = nbSalaries;
		this.nbCadres = nbCadres;
		this.backup = backup;
	}

	public Integer getAge() {
		return this.age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAgeTravail() {
		return this.ageTravail;
	}

	public void setAgeTravail(Integer ageTravail) {
		this.ageTravail = ageTravail;
	}

	public Integer getAgeRetraite() {
		return this.ageRetraite;
	}
	public void setAgeRetraite(Integer ageRetraite) {
		this.ageRetraite = ageRetraite;
	}
	public Integer getChargeSalariale() {
		return this.chargeSalariale;
	}
	public void setChargeSalariale(Integer chargeSalariale) {
		this.chargeSalariale = chargeSalariale;
	}
	public Integer getChargeCadre() {
		return this.chargeCadre;
	}
	public void setChargeCadre(Integer chargeCadre) {
		this.chargeCadre = chargeCadre;
	}
	public Integer getSalaireStandard() {
		return this.salaireStandard;
	}
	public void setSalaireStandard(Integer salaireStandard) {
		this.salaireStandard = salaireStandard;
	}
	public Integer getSalaireCadre() {
		return this.salaireCadre;
	}
	public void setSalaireCadre(Integer salaireCadre) {
		this.salaireCadre = salaireCadre;
	}
	public Integer getNbSalaries() {
		return this.nbSalaries;
	}
	public void setNbSalaries(Integer nbSalaries) {
		this.nbSalaries = nbSalaries;
	}
	public Integer getNbCadres() {
		return this.nbCadres;
	}
	public void setNbCadres(Integer nbCadres) {
		this.nbCadres = nbCadres;
	}
	public Backup getBackup() {
		return this.backup;
	}
	public void setBackup(Backup backup) {
		this.backup = backup;
	}

}
