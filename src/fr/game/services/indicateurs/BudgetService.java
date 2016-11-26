package fr.game.services.indicateurs;

public class BudgetService extends AbstractIndicateursService{
	private int ageTravail = 19;				// L'age a partir duquel les citoyens travaillent 
	private int ageRetraite = 62;				// Age de la retraite
	private int chargeSalariale = 20;			// En pourcentage
	private int chargeCadre = 40;
	private int salaireStandard = 1250;
	private int salaireCadre = 2350;
	
		// Ces attributs doivent etre obtenus avec une fonction getSalaries et getCadres des constructions (avant, ils etaients incrementes lors de chaque construction ou evolution, maintenant, ce n'est plus le cas)
	private int nbSalaries = 0;					
	private int nbCadres = 0;
	
	public int pauvrete(PopulationService p){
		int a = p.nbIndiv(ageTravail, ageRetraite);										// On r�cup�re tous les citoyens en age de travailler
		double postEfficience = Math.min((double)(nbCadres + nbSalaries)/a, (double)a/(nbCadres + nbSalaries));			// On compare ce nombre au nombre de postes de la ville 
		int ratio = nbSalaries*100/a;													// On note le ratio Ouvrier / Cadres									
		double pauvreteO = Math.min((double)(salaireStandard*(100-chargeSalariale)/100-300)/6, 100.)*postEfficience;	// en dessous de 900, la pauvrete s'installe. a 300, elle est au maximum
		double pauvreteC = Math.min((double)(salaireCadre*(100-chargeCadre)/100-300)/6, 100.)*postEfficience;
		int pauvrete = (int)(pauvreteO*ratio+pauvreteC*(100-ratio))/100;				// On reunifie les chiffres des cadres et ceux des ouvriers, le r�sultat sera en %
		return 100-pauvrete;
	}
	
	
	public int getAgeTravail() {
		return ageTravail;
	}
	public void setAgeTravail(int ageTravail) {
		this.ageTravail = ageTravail;
	}
	public int getAgeRetraite() {
		return ageRetraite;
	}
	public void setAgeRetraite(int ageRetraite) {
		this.ageRetraite = ageRetraite;
	}
	public int getChargeSalariale() {
		return chargeSalariale;
	}
	public void setChargeSalariale(int chargeSalariale) {
		this.chargeSalariale = chargeSalariale;
	}
	public int getChargeCadre() {
		return chargeCadre;
	}
	public void setChargeCadre(int chargeCadre) {
		this.chargeCadre = chargeCadre;
	}
	public int getNbCadre() {
		return nbCadres;
	}
	public void setNbCadre(int nbCadre) {
		this.nbCadres += nbCadre;
	}
	public int getSalaireStandard() {
		return salaireStandard;
	}
	public void setSalaireStandard(int salaireStandard) {
		this.salaireStandard = salaireStandard;
	}
	public int getSalaireCadre() {
		return salaireCadre;
	}
	public void setSalaireCadre(int salaireCadre) {
		this.salaireCadre = salaireCadre;
	}
	public int getNbSalaries() {
		return this.nbSalaries;
	}
	public void setNbSalaries(int nbSalaries) {
		this.nbSalaries += nbSalaries;
	}
}
