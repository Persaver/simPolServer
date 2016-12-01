package fr.entities;

import java.util.Arrays;

public class Population extends AbstractEntity<Population> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer[][] popTab = new Integer[130][12];
	private Integer fertilite;		// fertilite x 10 pour rester en int
	private Integer attractivite;
	private Integer nbj;
	private Backup backup;

	public Population() {
	}

	public Population(Integer id) {
		this(id, null, null, null, null, null);
	}

	public Population(Integer id, Integer[][] popTab, Integer fertilite, Integer attractivite, Integer nbj, Backup backup) {
		super(id);
		this.popTab = popTab;
		this.fertilite = fertilite;
		this.attractivite = attractivite;
		this.nbj = nbj;
		this.backup = backup;
	}

	public Integer[][] getPopTab() {
		return this.popTab;
	}
	public Integer getPopTab(int l, int c) {
		return this.popTab[l][c];
	}

	public void setPopTab(Integer[][] popTab) {
		this.popTab = popTab;
	}
	public void setPopTab(int c, int l, int val){
		this.popTab[c][l] = val;
	}

	public Integer getFertilite() {
		return this.fertilite;
	}

	public void setFertilite(Integer fertilite) {
		this.fertilite = fertilite;
	}

	public Integer getAttractivite() {
		return this.attractivite;
	}

	public void setAttractivite(Integer attractivite) {
		this.attractivite = attractivite;
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
		builder.append("Population [popTab=");
		builder.append(Arrays.toString(popTab));
		builder.append(", fertilite=");
		builder.append(fertilite);
		builder.append(", attractivite=");
		builder.append(attractivite);
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
