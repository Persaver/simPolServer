package fr.entities;

import java.util.Map;

public class BackupConstruction extends AbstractEntity<BackupConstruction> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer x;
	private Integer y;
	private Integer nbSalarie;
	private Integer nbCadre;
	private Integer risque; // pour 1000
	private Integer budget;
	private Integer attractivite; //
	private Integer postePourvu;
	private Map<String,Integer> specificite;
	private Construction construction;
	private Backup backup;


	public BackupConstruction() {
	}

	public BackupConstruction(Integer id) {
		this(id, null, null, null, null, null, null, null, null, null, null, null);
	}

	public BackupConstruction(Integer id, Integer x, Integer y, Integer nbSalarie, Integer nbCadre, Integer risque, Integer budget,
			Integer attractivite, Integer postePourvu, Map<String,Integer> specificite, Construction construction, Backup backup) {
		super(id);
		this.x = x;
		this.y = y;
		this.nbSalarie = nbSalarie;
		this.nbCadre = nbCadre;
		this.risque = risque;
		this.budget = budget;
		this.attractivite = attractivite;
		this.postePourvu = postePourvu;
		this.specificite = specificite;
		this.construction = construction;
		this.backup = backup;
	}

	public Integer getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return this.y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getNbSalarie() {
		return this.nbSalarie;
	}

	public void setNbSalarie(Integer nbSalarie) {
		this.nbSalarie = nbSalarie;
	}

	public Integer getNbCadre() {
		return this.nbCadre;
	}

	public void setNbCadre(Integer nbCadre) {
		this.nbCadre = nbCadre;
	}

	public Integer getRisque() {
		return this.risque;
	}

	public void setRisque(Integer risque) {
		this.risque = risque;
	}

	public Integer getBudget() {
		return this.budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public Integer getAttractivite() {
		return this.attractivite;
	}

	public void setAttractivite(Integer attractivite) {
		this.attractivite = attractivite;
	}

	public Integer getPostePourvu() {
		return this.postePourvu;
	}

	public void setPostePourvu(Integer postePourvu) {
		this.postePourvu = postePourvu;
	}


	public Map<String, Integer> getSpecificite() {
		return this.specificite;
	}

	public void setSpecificite(Map<String, Integer> specificite) {
		this.specificite = specificite;
	}

	public Construction getConstruction() {
		return this.construction;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
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
		builder.append("BackupConstruction [x=");
		builder.append(this.x);
		builder.append(", y=");
		builder.append(this.y);
		builder.append(", nbSalarie=");
		builder.append(this.nbSalarie);
		builder.append(", nbCadre=");
		builder.append(this.nbCadre);
		builder.append(", risque=");
		builder.append(this.risque);
		builder.append(", budget=");
		builder.append(this.budget);
		builder.append(", attractivite=");
		builder.append(this.attractivite);
		builder.append(", postePourvu=");
		builder.append(this.postePourvu);
		builder.append(", specificite=");
		builder.append(this.specificite);
		builder.append(", construction=");
		builder.append(this.construction);
		builder.append(", backup=");
		builder.append(this.backup);
		builder.append(", id=");
		builder.append(this.id);
		builder.append("]");
		return builder.toString();
	}

}
