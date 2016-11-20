package fr.gameEntities.indicateurs;

public class Budget {
	private static int ageTravail = 19;			// L'age � partir duquel les citoyens travaillent 
	private static int ageRetraite = 62;			// Age de la retraite
	private static int chargeSalariale = 20;		// En pourcentage
	private static int chargeCadre = 40;
//	private static int prixVie = 900;				// Ce que doit toucher au minimum, un salari� pour vivre chaque mois.
	private static int salaireStandard = 1250;
	private static int salaireCadre = 1000;
//	private static int salaireHautDiplome = 1875;
//	private static int salaireSpecialiste = 4000;
//	private static int primeRisque = 125;			// 125% d'un salaire.
//	private static int salaireTotalVerse;
	private static int nbSalaries = 0;
	private static int nbCadres = 0;
	
	public static int pauvrete(Population p){
		int a = p.nbIndiv(ageTravail, ageRetraite);										// On r�cup�re tous les citoyens en age de travailler
//		System.out.println("a = " + a);
		double postEfficience = Math.min((double)(nbCadres + nbSalaries)/a, (double)a/(nbCadres + nbSalaries));			// On compare ce nombre au nombre de postes de la ville 
//		System.out.println("posteCitoyen = " + postEfficience);
		int ratio = nbSalaries*100/a;													// On note le ratio Ouvrier / Cadres
//		System.out.println("ratio = " + ratio);											
		double pauvreteO = Math.min((double)(salaireStandard*(100-chargeSalariale)/100-300)/6, 100.)*postEfficience;	// en dessous de 900, la pauvrete s'installe. a 300, elle est au maximum
//		System.out.println("pauvreteO = " + pauvreteO);
		double pauvreteC = Math.min((double)(salaireCadre*(100-chargeCadre)/100-300)/6, 100.)*postEfficience;
//		System.out.println("pauvreteC = " + pauvreteC);
		int pauvrete = (int)(pauvreteO*ratio+pauvreteC*(100-ratio))/100;				// On reunifie les chiffres des cadres et ceux des ouvriers, le r�sultat sera en %
		return 100-pauvrete;
	}
	
	
	public static int getAgeTravail() {
		return ageTravail;
	}
	public static void setAgeTravail(int ageTravail) {
		Budget.ageTravail = ageTravail;
	}
	public static int getAgeRetraite() {
		return ageRetraite;
	}
	public static void setAgeRetraite(int ageRetraite) {
		Budget.ageRetraite = ageRetraite;
	}
	public static int getChargeSalariale() {
		return chargeSalariale;
	}
	public static void setChargeSalariale(int chargeSalariale) {
		Budget.chargeSalariale = chargeSalariale;
	}
	public static int getChargeCadre() {
		return chargeCadre;
	}
	public static void setChargeCadre(int chargeCadre) {
		Budget.chargeCadre = chargeCadre;
	}
	public static int getNbCadre() {
		return nbCadres;
	}
	public static void setNbCadre(int nbCadre) {
		Budget.nbCadres += nbCadre;
	}
//	public static int getPrixVie() {
//		return prixVie;
//	}
//	public static void setPrixVie(int prixVie) {
//		Budget.prixVie = prixVie;
//	}
	public static int getSalaireStandard() {
		return salaireStandard;
	}
	public static void setSalaireStandard(int salaireStandard) {
		Budget.salaireStandard = salaireStandard;
	}
	public static int getSalaireCadre() {
		return salaireCadre;
	}
	public static void setSalaireCadre(int salaireCadre) {
		Budget.salaireCadre = salaireCadre;
	}
	public static int getNbSalaries() {
		return Budget.nbSalaries;
	}
	public static void setNbSalaries(int nbSalaries) {
		Budget.nbSalaries += nbSalaries;
	}
//	public static int getSalaireHautDiplome() {
//		return salaireHautDiplome;
//	}
//	public static void setSalaireHautDiplome(int salaireHautDiplome) {
//		Budget.salaireHautDiplome = salaireHautDiplome;
//	}
//	public static int getSalaireSpecialiste() {
//		return salaireSpecialiste;
//	}
//	public static void setSalaireSpecialiste(int salaireSpecialiste) {
//		Budget.salaireSpecialiste = salaireSpecialiste;
//	}
//	public static int getPrimeRisque() {
//		return primeRisque;
//	}
//	public static void setPrimeRisque(int primeRisque) {
//		Budget.primeRisque = primeRisque;
//	}
//	public static int getSalaireTotalVerse() {
//		return salaireTotalVerse;
//	}
//	public static void setSalaireTotalVerse(int salaireTotalVerse) {
//		Budget.salaireTotalVerse = salaireTotalVerse;
//	}
	
	

}
