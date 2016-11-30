package fr.entities;

public class Education extends AbstractEntity<Education>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer edTotale;		// L'Education apportee par les ecoles
	private Integer edSecurite;		// L'education distribuee aux commissariats
	private Integer edEntretien;	// L'education distribuee aux casernes
	private Integer edSante;		// L'education distribuee aux hopitaux
	private Integer edRecherche;	// L'education distribuee aux recherches
	private Integer edTourisme;		// L'education distribuee aux batiment touristiques
	private Backup backup;

	public Education (){
	}

	public Education (Integer id){
		this(id, null, null, null, null, null, null, null);
	}

	public Education (Integer id, Integer edTotale, Integer edSec, Integer edEnt, Integer edSan, Integer edRec, Integer edTou, Backup backup){
		super(id);
		this.setEdTotale(edTotale);
		this.setEdSecurite(edSec);
		this.setEdEntretien(edEnt);
		this.setEdSante(edSan);
		this.setEdRecherche(edRec);
		this.setEdTourisme(edTou);
		this.setBackup(backup);
	}

	public Integer getEdTotale() {
		return this.edTotale;
	}
	public void setEdTotale(Integer edTotale) {
		this.edTotale = edTotale;
	}
	public Integer getEdSecurite() {
		return this.edSecurite;
	}
	public void setEdSecurite(Integer edSecurite) {
		this.edSecurite = edSecurite;
	}
	public Integer getEdEntretien() {
		return this.edEntretien;
	}
	public void setEdEntretien(Integer edEntretien) {
		this.edEntretien = edEntretien;
	}
	public Integer getEdSante() {
		return this.edSante;
	}
	public void setEdSante(Integer edSante) {
		this.edSante = edSante;
	}
	public Integer getEdRecherche() {
		return this.edRecherche;
	}
	public void setEdRecherche(Integer edRecherche) {
		this.edRecherche = edRecherche;
	}
	public Integer getEdTourisme() {
		return this.edTourisme;
	}
	public void setEdTourisme(Integer edTourisme) {
		this.edTourisme = edTourisme;
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
		builder.append("Education [edTotale=");
		builder.append(this.edTotale);
		builder.append(", edSecurite=");
		builder.append(this.edSecurite);
		builder.append(", edEntretien=");
		builder.append(this.edEntretien);
		builder.append(", edSante=");
		builder.append(this.edSante);
		builder.append(", edRecherche=");
		builder.append(this.edRecherche);
		builder.append(", edTourisme=");
		builder.append(this.edTourisme);
		builder.append(", backup=");
		builder.append(this.backup);
		builder.append(", id=");
		builder.append(this.id);
		builder.append("]");
		return builder.toString();
	}

}
