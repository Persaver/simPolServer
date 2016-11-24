package fr.entities;

import java.util.Arrays;

import fr.interfaces.IEntity;

public class Population extends AbstractEntity<Population> implements IEntity{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer[][] popTab = new Integer[130][12];
	private Integer fertilite;		// fertilite x 10 pour rester en int
	private Integer attractivite;
	private Backup backup;

	public Population() {
	}

	public Population(Integer id) {
		this(id, null, null, null, null);
	}

	public Population(Integer id, Integer[][] popTab, Integer fertilite, Integer attractivite, Backup backup) {
		super(id);
		this.popTab = popTab;
		this.fertilite = fertilite;
		this.attractivite = attractivite;
		this.backup = backup;
	}

	public Integer[][] getPopTab() {
		return this.popTab;
	}

	public void setPopTab(Integer[][] popTab) {
		this.popTab = popTab;
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
		builder.append(Arrays.toString(this.popTab));
		builder.append(", fertilite=");
		builder.append(this.fertilite);
		builder.append(", attractivite=");
		builder.append(this.attractivite);
		builder.append(", backup=");
		builder.append(this.backup);
		builder.append(", id=");
		builder.append(this.id);
		builder.append("]");
		return builder.toString();
	}

}
