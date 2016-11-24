package fr.entities;

public class Enseignement {
	private int edTotale;		// L'Education apportee par les ecoles
	private int edSecurite;		// L'education distribuee aux commissariats
	private int edEntretien;		// L'education distribuee aux casernes
	private int edSante;			// L'education distribuee aux hopitaux
	private int edRecherche;		// L'education distribuee aux recherches
	private int edTourisme;		// L'education distribuee aux batiment touristiques
	
	
	public int getEdTotale() {
		return edTotale;
	}
	public void setEdTotale(int edTotale) {
		this.edTotale = edTotale;
	}
	public int getEdSecurite() {
		return edSecurite;
	}
	public void setEdSecurite(int edSecurite) {
		this.edSecurite = edSecurite;
	}
	public int getEdEntretien() {
		return edEntretien;
	}
	public void setEdEntretien(int edEntretien) {
		this.edEntretien = edEntretien;
	}
	public int getEdSante() {
		return edSante;
	}
	public void setEdSante(int edSante) {
		this.edSante = edSante;
	}
	public int getEdRecherche() {
		return edRecherche;
	}
	public void setEdRecherche(int edRecherche) {
		this.edRecherche = edRecherche;
	}
	public int getEdTourisme() {
		return edTourisme;
	}
	public void setEdTourisme(int edTourisme) {
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
