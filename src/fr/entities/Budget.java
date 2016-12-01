package fr.entities;

public class Budget extends AbstractEntity<Budget> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer ageTravail;
	private Integer ageRetraite;
	private Integer chargeSalariale;
	private Integer chargeCadre;
	private Integer salaireStandard;
	private Integer salaireCadre;
	private Integer nbSalaries;
	private Integer nbCadres;
	private Integer nbj;
	private Backup backup;

	public Budget(){
	}

	public Budget(Integer id){
		this(id,null, null, null, null, null, null, null, null, null, null);
	}

	public Budget(Integer id, Integer ageTravail, Integer ageRetraite, Integer chargeSalariale,
			Integer chargeCadre, Integer salaireStandard, Integer salaireCadre, Integer nbSalaries, Integer nbCadres,
			Integer nbj, Backup backup) {
		super(id);
		this.ageTravail = ageTravail;
		this.ageRetraite = ageRetraite;
		this.chargeSalariale = chargeSalariale;
		this.chargeCadre = chargeCadre;
		this.salaireStandard = salaireStandard;
		this.salaireCadre = salaireCadre;
		this.nbSalaries = nbSalaries;
		this.nbCadres = nbCadres;
		this.setNbj(nbj);
		this.backup = backup;
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

	public Integer getNbj() {
		return nbj;
	}

	public void setNbj(Integer nbj) {
		this.nbj = nbj;
	}

	public Backup getBackup() {
		return this.backup;
	}

	public void setBackup(Backup backup) {
		this.backup = backup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Budget [ageTravail=");
		builder.append(ageTravail);
		builder.append(", ageRetraite=");
		builder.append(ageRetraite);
		builder.append(", chargeSalariale=");
		builder.append(chargeSalariale);
		builder.append(", chargeCadre=");
		builder.append(chargeCadre);
		builder.append(", salaireStandard=");
		builder.append(salaireStandard);
		builder.append(", salaireCadre=");
		builder.append(salaireCadre);
		builder.append(", nbSalaries=");
		builder.append(nbSalaries);
		builder.append(", nbCadres=");
		builder.append(nbCadres);
		builder.append(", nbj=");
		builder.append(nbj);
		builder.append(", backup=");
		builder.append(backup);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}
