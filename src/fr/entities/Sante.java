package fr.entities;

public class Sante  extends AbstractEntity<Sante> {
	private static final long serialVersionUID = 1L;
	private Integer hygiene = 80;	// en % parametre qui influe sur le nombre de malades | influence par les recherches en medecine et par les decrets
	private Integer nbMalades;
	private Integer nbAccidents;		// accident li�s au risque des batiments
	private Integer soins;			// Soins apport� par les hopitaux
	private Integer echecs = 5;// en % tous les soins ne sont pas fructueux, et parfois des gens meurent
	private Integer nbj;
	private Backup backup;

	public Sante (){
		super();
	}

	public Sante(Integer id){
		this(id, null, null, null, null, null, null, null);
	}

	public Sante(Integer id, Integer h, Integer nbM, Integer nbA, Integer sn, Integer e, Integer nbj, Backup backup){
		super(id);
		this.setHygiene(h);
		this.setNbMalades(nbM);
		this.setNbAccidents(nbA);
		this.setSoins(sn);
		this.setEchecs(e);
		this.setNbj(nbj);
		this.setBackup(backup);
	}

	public Integer getHygiene() {
		return this.hygiene;
	}

	public void setHygiene(Integer hygiene) {
		this.hygiene = hygiene;
	}

	public Integer getNbMalades() {
		return this.nbMalades;
	}

	public void setNbMalades(Integer nbMalades) {
		this.nbMalades = nbMalades;
	}

	public Integer getNbAccidents() {
		return this.nbAccidents;
	}

	public void setNbAccidents(Integer nbAccidents) {
		this.nbAccidents = nbAccidents;
	}

	public Integer getSoins() {
		return this.soins;
	}

	public void setSoins(Integer soins) {
		this.soins = soins;
	}

	public Integer getEchecs() {
		return this.echecs;
	}

	public void setEchecs(Integer echecs) {
		this.echecs = echecs;
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
		builder.append("Sante [hygiene=");
		builder.append(hygiene);
		builder.append(", nbMalades=");
		builder.append(nbMalades);
		builder.append(", nbAccidents=");
		builder.append(nbAccidents);
		builder.append(", soins=");
		builder.append(soins);
		builder.append(", echecs=");
		builder.append(echecs);
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
