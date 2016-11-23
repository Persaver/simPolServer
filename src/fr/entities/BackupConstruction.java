package fr.entities;

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
	private String specificites;
	private Construction construction;
	private Backup backup;

	public String getSpecificite() {
		return this.specificites;
	}

	public void setSpecificite(String specificites) {
		this.specificites = specificites;
	}

	public BackupConstruction(Integer id,Integer x, Integer y, Construction construction, Backup backup) {
		super(id);
		this.x = x;
		this.y = y;
		this.construction = construction;
		this.backup = backup;
	}


	public BackupConstruction(Integer x, Integer y, Integer nbSalarie, Integer nbCadre, Integer risque, Integer budget,
			Integer attractivite, Integer postePourvu, String specificites, Construction construction, Backup backup) {
		this.x = x;
		this.y = y;
		this.nbSalarie = nbSalarie;
		this.nbCadre = nbCadre;
		this.risque = risque;
		this.budget = budget;
		this.attractivite = attractivite;
		this.postePourvu = postePourvu;
		this.specificites = specificites;
		this.construction = construction;
		this.backup = backup;
	}

	public BackupConstruction(Integer id, Integer x, Integer y, Integer nbSalarie, Integer nbCadre, Integer risque, Integer budget,
			Integer attractivite, Integer postePourvu, String specificites) {
		super(id);
		this.x = x;
		this.y = y;
		this.nbSalarie = nbSalarie;
		this.nbCadre = nbCadre;
		this.risque = risque;
		this.budget = budget;
		this.attractivite = attractivite;
		this.postePourvu = postePourvu;
		this.specificites = specificites;
	}

	public BackupConstruction(Integer id, Integer x, Integer y, Integer nbSalarie, Integer nbCadre, Integer risque, Integer budget,
			Integer attractivite, Integer postePourvu, String specificites, Construction construction, Backup backup) {
		super(id);
		this.x = x;
		this.y = y;
		this.nbSalarie = nbSalarie;
		this.nbCadre = nbCadre;
		this.risque = risque;
		this.budget = budget;
		this.attractivite = attractivite;
		this.postePourvu = postePourvu;
		this.specificites = specificites;
		this.construction = construction;
		this.backup = backup;
	}

	public int getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(Integer y) {
		this.y = y;
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
	}public int getNbSalarie() {
		return this.nbSalarie;
	}

	public void setNbSalarie(Integer nbSalarie) {
		this.nbSalarie = nbSalarie;
	}

	public int getNbCadre() {
		return this.nbCadre;
	}

	public void setNbCadre(Integer nbCadre) {
		this.nbCadre = nbCadre;
	}

	public int getRisque() {
		return this.risque;
	}

	public void setRisque(Integer risque) {
		this.risque = risque;
	}

	public int getBudget() {
		return this.budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public int getAttractivite() {
		return this.attractivite;
	}

	public void setAttractivite(Integer attractivite) {
		this.attractivite = attractivite;
	}

	public int getPostePourvu() {
		return this.postePourvu;
	}

	public void setPostePourvu(Integer postePourvu) {
		this.postePourvu = postePourvu;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BackupConstruction [x=");
		builder.append(this.x);
		builder.append(", y=");
		builder.append(this.y);
		builder.append(", construction=");
		builder.append(this.construction);
		builder.append(", backup=");
		builder.append(this.backup);
		builder.append("]");
		return builder.toString();
	}


}
