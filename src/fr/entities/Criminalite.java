package fr.entities;

public class Criminalite extends AbstractEntity<Criminalite>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer indicMineur;
	private Integer crimeMineur;
	private Integer indicMoyen;
	private Integer crimeMoyen;
	private Integer indicGrave;
	private Integer crimeGrave;
	private Integer indicTerrorisme;
	private Integer crimeTerroriste;
	private Backup backup;

	public Integer getIndicMineur() {
		return indicMineur;
	}

	public void setIndicMineur(int indicMineur) {
		this.indicMineur = indicMineur;
	}

	public Integer  getCrimeMineur() {
		return crimeMineur;
	}

	public void setCrimeMineur(int crimeMineur) {
		this.crimeMineur = crimeMineur;
	}

	public Integer  getIndicMoyen() {
		return indicMoyen;
	}

	public void setIndicMoyen(int indicMoyen) {
		this.indicMoyen = indicMoyen;
	}

	public Integer  getCrimeMoyen() {
		return crimeMoyen;
	}

	public void setCrimeMoyen(int crimeMoyen) {
		this.crimeMoyen = crimeMoyen;
	}

	public Integer  getIndicGrave() {
		return indicGrave;
	}

	public void setIndicGrave(int indicGrave) {
		this.indicGrave = indicGrave;
	}

	public Integer  getCrimeGrave() {
		return crimeGrave;
	}

	public void setCrimeGrave(int crimeGrave) {
		this.crimeGrave = crimeGrave;
	}

	public Integer  getIndicTerrorisme() {
		return indicTerrorisme;
	}

	public void setIndicTerrorisme(int indicTerrorisme) {
		this.indicTerrorisme = indicTerrorisme;
	}

	public Integer  getCrimeTerroriste() {
		return crimeTerroriste;
	}

	public void setCrimeTerroriste(int crimeTerroriste) {
		this.crimeTerroriste = crimeTerroriste;
	}

	public Backup getBackup(){
		return this.backup;
	}

	public void setBackup(Backup backup){
		this.backup=backup;
	}
}
