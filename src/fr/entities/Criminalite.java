package fr.entities;

public class Criminalite extends AbstractEntity<Criminalite>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int indicMineur;
	private int crimeMineur;
	private int indicMoyen;
	private int crimeMoyen;
	private int indicGrave;
	private int crimeGrave;
	private int indicTerrorisme;
	private int crimeTerroriste;
	
	
	public int getIndicMineur() {
		return indicMineur;
	}
	public void setIndicMineur(int indicMineur) {
		this.indicMineur = indicMineur;
	}
	public int getCrimeMineur() {
		return crimeMineur;
	}
	public void setCrimeMineur(int crimeMineur) {
		this.crimeMineur = crimeMineur;
	}
	public int getIndicMoyen() {
		return indicMoyen;
	}
	public void setIndicMoyen(int indicMoyen) {
		this.indicMoyen = indicMoyen;
	}
	public int getCrimeMoyen() {
		return crimeMoyen;
	}
	public void setCrimeMoyen(int crimeMoyen) {
		this.crimeMoyen = crimeMoyen;
	}
	public int getIndicGrave() {
		return indicGrave;
	}
	public void setIndicGrave(int indicGrave) {
		this.indicGrave = indicGrave;
	}
	public int getCrimeGrave() {
		return crimeGrave;
	}
	public void setCrimeGrave(int crimeGrave) {
		this.crimeGrave = crimeGrave;
	}
	public int getIndicTerrorisme() {
		return indicTerrorisme;
	}
	public void setIndicTerrorisme(int indicTerrorisme) {
		this.indicTerrorisme = indicTerrorisme;
	}
	public int getCrimeTerroriste() {
		return crimeTerroriste;
	}
	public void setCrimeTerroriste(int crimeTerroriste) {
		this.crimeTerroriste = crimeTerroriste;
	}
	
}
