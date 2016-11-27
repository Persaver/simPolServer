package fr.entities;

public class Sante  extends AbstractEntity<Sante> {
	private static final long serialVersionUID = 1L;
	private Integer hygiene = 80;	// en % parametre qui influe sur le nombre de malades | influence par les recherches en medecine et par les decrets
	private Integer nbMalades;
	private Integer nbAccidents;		// accident li�s au risque des batiments
	private Integer soins;			// Soins apport� par les hopitaux
	private Integer echecs = 5;		// en % tous les soins ne sont pas fructueux, et parfois des gens meurent
	
	public Sante (){
		super();
	}
	public Sante(Integer id){
		this(id, null, null, null, null, null);
	}
	public Sante(Integer id, Integer h, Integer nbM, Integer nbA, Integer sn, Integer e){
		super(id);
		this.setHygiene(h);
		this.setNbMalades(nbM);
		this.setNbAccidents(nbA);
		this.setSoins(sn);
		this.setEchecs(e);
	}
	public Integer getHygiene() {
		return hygiene;
	}
	public void setHygiene(Integer hygiene) {
		this.hygiene = hygiene;
	}
	public Integer getNbMalades() {
		return nbMalades;
	}
	public void setNbMalades(Integer nbMalades) {
		this.nbMalades = nbMalades;
	}
	public Integer getNbAccidents() {
		return nbAccidents;
	}
	public void setNbAccidents(Integer nbAccidents) {
		this.nbAccidents = nbAccidents;
	}
	public Integer getSoins() {
		return soins;
	}
	public void setSoins(Integer soins) {
		this.soins = soins;
	}
	public Integer getEchecs() {
		return echecs;
	}
	public void setEchecs(Integer echecs) {
		this.echecs = echecs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
