package fr.game.services.indicateurs;;

public class Criminalite {
	private static int indicMineur;
	private static int crimeMineur;
	private static int indicMoyen;
	private static int crimeMoyen;
	private static int indicGrave;
	private static int crimeGrave;
	private static int indicTerrorisme;
	private static int crimeTerroriste;
	
	// Une fonction pour calculer la valeur quotidienne du crime mineur
	public static void actuIndicMin(Population p){
		indicMineur = (int)((9*Budget.pauvrete(p)/*+insatisfaction(p)*/)*15*Math.sqrt((double)p.nbIndiv(0, 129))/3000);
	}	
	public static void cumulMineur(int modif){
		crimeMineur += modif;
		if (crimeMineur < 0)
			crimeMineur = 0;
	}
	
	// Une fonction pour calculer la valeur quotidienne du crime moyen
	public static void actuIndicMo(Population p){
		indicMineur = (int)((6.5*Budget.pauvrete(p)/*+3.5*insatisfaction(p)*/)*10*Math.sqrt((double)p.nbIndiv(0, 129))/3000);
	}	
	public static void cumulMoyen(int modif){
		crimeMoyen += modif;
		if (crimeMoyen < 0)
			crimeMoyen = 0;
	}
	
	// Une fonction pour calculer la valeur quotidienne du crime grave
	public static void actuIndicG(Population p){
		indicGrave = (int)((3.5*Budget.pauvrete(p)/*+6.5*insatisfaction(p)*/)*5*Math.sqrt((double)p.nbIndiv(0, 129))/3000);
	}	
	public static void cumulGrave(int modif){
		crimeGrave += modif;
		if (crimeGrave < 0)
			crimeGrave = 0;
	}
	
	// Une fonction pour calculer la valeur quotidienne du terrorisme
	public static void actuIndicT(Population p){
		indicTerrorisme = (int)((Budget.pauvrete(p)/*+9*insatisfaction(p)*/)*Math.sqrt((double)p.nbIndiv(0, 129))/3000);
	}	
	public static void cumulTerreur(int modif){
		crimeTerroriste += modif;
		if (crimeTerroriste < 0)
			crimeTerroriste = 0;
	}
	public static int getIndicMineur() {
		return indicMineur;
	}
	public static void setIndicMineur(int indicMineur) {
		Criminalite.indicMineur = indicMineur;
	}
	public static int getCrimeMineur() {
		return crimeMineur;
	}
	public static void setCrimeMineur(int crimeMineur) {
		Criminalite.crimeMineur = crimeMineur;
	}
	public static int getIndicMoyen() {
		return indicMoyen;
	}
	public static void setIndicMoyen(int indicMoyen) {
		Criminalite.indicMoyen = indicMoyen;
	}
	public static int getCrimeMoyen() {
		return crimeMoyen;
	}
	public static void setCrimeMoyen(int crimeMoyen) {
		Criminalite.crimeMoyen = crimeMoyen;
	}
	public static int getIndicGrave() {
		return indicGrave;
	}
	public static void setIndicGrave(int indicGrave) {
		Criminalite.indicGrave = indicGrave;
	}
	public static int getCrimeGrave() {
		return crimeGrave;
	}
	public static void setCrimeGrave(int crimeGrave) {
		Criminalite.crimeGrave = crimeGrave;
	}
	public static int getIndicTerrorisme() {
		return indicTerrorisme;
	}
	public static void setIndicTerrorisme(int indicTerrorisme) {
		Criminalite.indicTerrorisme = indicTerrorisme;
	}
	public static int getCrimeTerroriste() {
		return crimeTerroriste;
	}
	public static void setCrimeTerroriste(int crimeTerroriste) {
		Criminalite.crimeTerroriste = crimeTerroriste;
	}
}
