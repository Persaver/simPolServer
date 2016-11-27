package fr.entities;

public class Education extends AbstractEntity<Education>{
	private Integer edTotale;		// L'Education apportee par les ecoles
	private Integer edSecurite;		// L'education distribuee aux commissariats
	private Integer edEntretien;	// L'education distribuee aux casernes
	private Integer edSante;		// L'education distribuee aux hopitaux
	private Integer edRecherche;	// L'education distribuee aux recherches
	private Integer edTourisme;		// L'education distribuee aux batiment touristiques
	
	public Education (){
		super();
	}
	
	public Education (Integer edTotale){
		super();
		this.setEdTotale(edTotale);
	}
	
	public Education (Integer edTot, Integer edSec, Integer edEnt, Integer edSan, Integer edRec, Integer edTou){
		super();
		this.setEdTotale(edTot);
		this.setEdSecurite(edSec);
		this.setEdEntretien(edEnt);
		this.setEdSante(edSan);
		this.setEdRecherche(edRec);
		this.setEdTourisme(edTou);
	}
	
	public Integer getEdTotale() {
		return edTotale;
	}
	public void setEdTotale(Integer edTotale) {
		this.edTotale = edTotale;
	}
	public Integer getEdSecurite() {
		return edSecurite;
	}
	public void setEdSecurite(Integer edSecurite) {
		this.edSecurite = edSecurite;
	}
	public Integer getEdEntretien() {
		return edEntretien;
	}
	public void setEdEntretien(Integer edEntretien) {
		this.edEntretien = edEntretien;
	}
	public Integer getEdSante() {
		return edSante;
	}
	public void setEdSante(Integer edSante) {
		this.edSante = edSante;
	}
	public Integer getEdRecherche() {
		return edRecherche;
	}
	public void setEdRecherche(Integer edRecherche) {
		this.edRecherche = edRecherche;
	}
	public Integer getEdTourisme() {
		return edTourisme;
	}
	public void setEdTourisme(Integer edTourisme) {
		this.edTourisme = edTourisme;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enseignement [edTotale=");
		builder.append(edTotale);
		builder.append(", edSecurite=");
		builder.append(edSecurite);
		builder.append(", edEntretien=");
		builder.append(edEntretien);
		builder.append(", edSante=");
		builder.append(edSante);
		builder.append(", edRecherche=");
		builder.append(edRecherche);
		builder.append(", edTourisme=");
		builder.append(edTourisme);
		builder.append("]");
		return builder.toString();
	}
	
	
}
