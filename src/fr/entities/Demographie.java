package fr.entities;

public class Demographie {
	private int[][] popTab = new int[130][12];
	private int fertilite = 29;		// fertilite x 10 pour rester en int
	private int attractivite = 0;
	
	public int[][] getPopTab() {
		return popTab;
	}
	public void setPopTab(int[][] popTab) {
		this.popTab = popTab;
	}
	public int getPopTab(int lig, int col){
		return popTab[lig][col];
	}
	public void setPopTab(int lig, int col, int val){
		this.popTab[lig][col] = val;
	}
	public int getFertilite() {
		return fertilite;
	}
	public void setFertilite(int fertilite) {
		this.fertilite = fertilite;
	}
	public int getAttractivite() {
		return attractivite;
	}
	public void setAttractivite(int attractivite) {
		this.attractivite = attractivite;
	}
	
}
