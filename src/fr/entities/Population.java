package fr.entities;

public class Population extends AbstractEntity<Population>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer[][] popTab = new int[130][12];
	private Integer fertilite;		// fertilite x 10 pour rester en int
	private Integer attractivite;
	private Backup backup;

	public Integer[][] getPopTab() {
		return popTab;
	}
	public void setPopTab(int[][] popTab) {
		this.popTab = popTab;
	}
	public Integer getPopTab(int lig, int col){
		return popTab[lig][col];
	}
	public void setPopTab(int lig, int col, int val){
		this.popTab[lig][col] = val;
	}
	public Integer getFertilite() {
		return fertilite;
	}
	public void setFertilite(int fertilite) {
		this.fertilite = fertilite;
	}
	public Integer getAttractivite() {
		return attractivite;
	}
	public void setAttractivite(int attractivite) {
		this.attractivite = attractivite;
	}

	public Backup getBackup(){
		return this.backup;
	}

	public void setBackup(Backup backup) {
		this.backup = backup;
	}

}
