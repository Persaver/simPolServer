package fr.entities;

import java.io.Serializable;
import java.util.Map;

public class BackupConstruction extends AbstractEntity<Integer> {
	private int x;
	private int y;
	private int nbSalarie;
	private int nbCadre;
	private int risque; // pour 1000
	private int budget;
	private int attractivite; //
	private int postePourvu;
	private Map<String,Integer> specificite;
	private Construction construction;
	private Backup backup;

	public Map<String, Integer> getSpecificite() {
		return this.specificite;
	}

	public void setSpecificite(Map<String, Integer> specificite) {
		this.specificite = specificite;
	}

	public BackupConstruction(Integer id,int x, int y, Construction construction, Backup backup) {
		super(id);
		this.x = x;
		this.y = y;
		this.construction = construction;
		this.backup = backup;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Construction getConstruction() {
		return construction;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public Backup getBackup() {
		return backup;
	}

	public void setBackup(Backup backup) {
		this.backup = backup;
	}public int getNbSalarie() {
		return nbSalarie;
	}

	public void setNbSalarie(int nbSalarie) {
		this.nbSalarie = nbSalarie;
	}

	public int getNbCadre() {
		return nbCadre;
	}

	public void setNbCadre(int nbCadre) {
		this.nbCadre = nbCadre;
	}

	public int getRisque() {
		return risque;
	}

	public void setRisque(int risque) {
		this.risque = risque;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getAttractivite() {
		return attractivite;
	}

	public void setAttractivite(int attractivite) {
		this.attractivite = attractivite;
	}

	public int getPostePourvu() {
		return postePourvu;
	}

	public void setPostePourvu(int postePourvu) {
		this.postePourvu = postePourvu;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BackupConstruction [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", construction=");
		builder.append(construction);
		builder.append(", backup=");
		builder.append(backup);
		builder.append("]");
		return builder.toString();
	}


}
