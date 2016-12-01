package fr.entities;

public class Criminalite extends AbstractEntity<Criminalite> {
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
	private Integer nbj;
	private Backup backup;

	public Criminalite() {
	}

	public Criminalite(Integer id){
		this(id, null, null, null, null, null, null, null, null, null, null);
	}
	public Criminalite(Integer id, Integer indicMineur, Integer crimeMineur, Integer indicMoyen, Integer crimeMoyen,
			Integer indicGrave, Integer crimeGrave, Integer indicTerrorisme, Integer crimeTerroriste, Integer nbj, Backup backup) {
		super(id);
		this.indicMineur = indicMineur;
		this.crimeMineur = crimeMineur;
		this.indicMoyen = indicMoyen;
		this.crimeMoyen = crimeMoyen;
		this.indicGrave = indicGrave;
		this.crimeGrave = crimeGrave;
		this.indicTerrorisme = indicTerrorisme;
		this.crimeTerroriste = crimeTerroriste;
		this.nbj = nbj;
		this.backup = backup;
	}

	public Integer getIndicMineur() {
		return this.indicMineur;
	}

	public void setIndicMineur(Integer indicMineur) {
		this.indicMineur = indicMineur;
	}

	public Integer getCrimeMineur() {
		return this.crimeMineur;
	}

	public void setCrimeMineur(Integer crimeMineur) {
		this.crimeMineur = crimeMineur;
	}

	public Integer getIndicMoyen() {
		return this.indicMoyen;
	}

	public void setIndicMoyen(Integer indicMoyen) {
		this.indicMoyen = indicMoyen;
	}

	public Integer getCrimeMoyen() {
		return this.crimeMoyen;
	}

	public void setCrimeMoyen(Integer crimeMoyen) {
		this.crimeMoyen = crimeMoyen;
	}

	public Integer getIndicGrave() {
		return this.indicGrave;
	}

	public void setIndicGrave(Integer indicGrave) {
		this.indicGrave = indicGrave;
	}

	public Integer getCrimeGrave() {
		return this.crimeGrave;
	}

	public void setCrimeGrave(Integer crimeGrave) {
		this.crimeGrave = crimeGrave;
	}

	public Integer getIndicTerrorisme() {
		return this.indicTerrorisme;
	}

	public void setIndicTerrorisme(Integer indicTerrorisme) {
		this.indicTerrorisme = indicTerrorisme;
	}

	public Integer getCrimeTerroriste() {
		return this.crimeTerroriste;
	}

	public void setCrimeTerroriste(Integer crimeTerroriste) {
		this.crimeTerroriste = crimeTerroriste;
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
		builder.append("Criminalite [indicMineur=");
		builder.append(indicMineur);
		builder.append(", crimeMineur=");
		builder.append(crimeMineur);
		builder.append(", indicMoyen=");
		builder.append(indicMoyen);
		builder.append(", crimeMoyen=");
		builder.append(crimeMoyen);
		builder.append(", indicGrave=");
		builder.append(indicGrave);
		builder.append(", crimeGrave=");
		builder.append(crimeGrave);
		builder.append(", indicTerrorisme=");
		builder.append(indicTerrorisme);
		builder.append(", crimeTerroriste=");
		builder.append(crimeTerroriste);
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
