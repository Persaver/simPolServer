package fr.game.services.indicateurs;

import fr.Dao.BudgetDAO;
import fr.entities.Budget;
import fr.game.services.gameControllers.AbstractGameEntity;

public class BudgetService extends AbstractGameEntity<Budget, BudgetDAO>{
	private int ageTravail = 19;				// L'age a partir duquel les citoyens travaillent
	private int ageRetraite = 62;				// Age de la retraite
	private int chargeSalariale = 20;			// En pourcentage
	private int chargeCadre = 40;
	private int salaireStandard = 1250;
	private int salaireCadre = 2350;
	private int nbSalaries = 0;
	private int nbCadres = 0;

	public BudgetService (Budget entity, BudgetDAO entityDao) {
		super(entity, entityDao);
	}
	public int pauvrete(PopulationService p){
		int a = p.nbIndiv(this.ageTravail, this.ageRetraite);										// On recupere tous les citoyens en age de travailler
		double postEfficience = Math.min((double)(this.nbCadres + this.nbSalaries)/a, (double)a/(this.nbCadres + this.nbSalaries));			// On compare ce nombre au nombre de postes de la ville
		int ratio = this.nbSalaries*100/a;													// On note le ratio Ouvrier / Cadres
		double pauvreteO = Math.min((double)(this.salaireStandard*(100-this.chargeSalariale)/100-300)/6, 100.)*postEfficience;	// en dessous de 900, la pauvrete s'installe. a 300, elle est au maximum
		double pauvreteC = Math.min((double)(this.salaireCadre*(100-this.chargeCadre)/100-300)/6, 100.)*postEfficience;
		int pauvrete = (int)(pauvreteO*ratio+pauvreteC*(100-ratio))/100;				// On reunifie les chiffres des cadres et ceux des ouvriers, le r�sultat sera en %
		return 100-pauvrete;
	}

	public int getAgeTravail() {
		return this.ageTravail;
	}
	public void setAgeTravail(int ageTravail) {
		this.ageTravail = ageTravail;
	}
	public int getAgeRetraite() {
		return this.ageRetraite;
	}
	public void setAgeRetraite(int ageRetraite) {
		this.ageRetraite = ageRetraite;
	}
	public int getChargeSalariale() {
		return this.chargeSalariale;
	}
	public void setChargeSalariale(int chargeSalariale) {
		this.chargeSalariale = chargeSalariale;
	}
	public int getChargeCadre() {
		return this.chargeCadre;
	}
	public void setChargeCadre(int chargeCadre) {
		this.chargeCadre = chargeCadre;
	}
	public int getNbCadre() {
		return this.nbCadres;
	}
	public void setNbCadre(int nbCadre) {
		this.nbCadres += nbCadre;
	}
	public int getSalaireStandard() {
		return this.salaireStandard;
	}
	public void setSalaireStandard(int salaireStandard) {
		this.salaireStandard = salaireStandard;
	}
	public int getSalaireCadre() {
		return this.salaireCadre;
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
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
