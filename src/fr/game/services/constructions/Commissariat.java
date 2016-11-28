package fr.game.services.constructions;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.game.services.indicateurs.BudgetService;
import fr.game.services.indicateurs.CriminaliteService;
import fr.game.services.indicateurs.EducationService;
import fr.game.services.indicateurs.PopulationService;


public class Commissariat extends AbstractConstructionService{
	private int pInfluence;
	private static int tolerance = 0;	/* en %, quantite de crime qui ne sont pas verbalises -> influe sur la recette des contraventions et sur la satisfaction de la population*/
	private int recette;
	
		// Constructeurs
	public Commissariat (BackupConstruction entity, BackupConstructionDAO entityDao){
		super(entity, entityDao);	// Appelle le constructeur de la classe mere pour stocker le batiment cree dans la liste et confier l'indice a l'instance, utile pour etre dettruite
		this.entity.setNbSalarie(120);
		this.entity.setNbCadre(20);
		this.entity.setRisque(4);
		this.entity.setAttractivite(1);
		this.pInfluence = this.entity.getNbSalarie()*(10+this.entity.getNbCadre()/10)/100; //le Potentiel d'influence est l'attribut qui permet aux commissariat de nettoyer la racaille et les criminels. Moins efficace contre le terrorisme
	}
	public Commissariat (int niv, BackupConstruction entity, BackupConstructionDAO entityDao){	// Possibilite de onstruire un commissariat plus grand
		this(entity, entityDao);
		for (int i=1; i<niv; i++)
			this.ameliore();
	}
	
		// Fonctions individuelles
	public void ameliore (){		// Permet d'augmenter le potentiel dd'un commissariat
		super.ameliore();
		this.pInfluence = this.entity.getNbSalarie()*(10+this.entity.getNbCadre()/10)/100;
	}
//	public void detruire(){
//		super.detruire(this.indice);
//		commissariats.remove(this.indiceCom);
//		for (int i = this.indice; i < commissariats.size(); i++)
//			commissariats.get(i).indiceCom --;
//	}
//	public void afficheCommissariat(){
//		System.out.println("nb Salaries : " + this.nbSalarie/10);
//		System.out.println("nb Cadres : " + this.nbCadre/10);
//		System.out.println("Usure : " + this.risque + "/1000");
//		System.out.println("Influence : " + this.pInfluence);
//	}
	public void secure(CriminaliteService c, PopulationService p, EducationService e, BudgetService b){	// La capacite des commissariats depend de leur budget ainsi que de l'education / formation re�ue
		int influence = this.pInfluence*this.potentiel(b)/100*(300+e.getEntity().getEdSecurite())/500;		// N'est effectif qu'a 60% si l'education est nulle.
		System.out.println("Influence actuelle = " + influence);
		if (c.getEntity().getCrimeMineur() < influence/2){			//50% de l'influence est dirigee vers les crimes mineurs
			influence -= c.getEntity().getCrimeMineur();
			this.recette += c.getEntity().getCrimeMineur()*(100-tolerance)*35/100;		// Recette des amendes mineurs
			c.cumulMineur(-c.getEntity().getCrimeMineur());
		} else {
			influence = influence/2;
			this.recette += influence*(100-tolerance)*35/100;
			c.cumulMineur(influence);
		}
		if (c.getEntity().getCrimeMoyen() < influence/2){				//50% du reste de l'influence est dirigee vers les crimes moyens
			influence -= c.getEntity().getCrimeMoyen();				// Si le crime mineur n'est pas assez repandu, les effectifs sont rediriges vers des gestions de crime plus graves
			this.recette += c.getEntity().getCrimeMoyen()*(100-tolerance)*60/100;			// Recette des amendes intermediaires
			c.cumulMoyen(-c.getEntity().getCrimeMoyen());
		} else {
			influence = influence/2;
			this.recette += influence*(100-tolerance)*60/100;
			c.cumulMoyen(influence);
		}
		if (c.getEntity().getCrimeGrave() < influence*4/5){			//20% de l'influence est dirigee vers les crimes graves
			influence -= c.getEntity().getCrimeGrave();
			this.recette += c.getEntity().getCrimeGrave()*(100-tolerance)*120/100;			// Recette des amendes lourdes
			c.cumulGrave(-c.getEntity().getCrimeGrave());
		} else {
			this.recette += influence*4*(100-tolerance)*120/100/5;
			c.cumulGrave(influence*4/5);
			influence = influence/5;
		}
		if (c.getEntity().getCrimeTerroriste() < influence){									//5% de l'influence est dirigee vers le terrorisme
			this.recette += c.getEntity().getCrimeTerroriste()*5000/100;						// Pas de tolerance pour le terrorrisme
			p.retraitPopulation(c.getEntity().getCrimeTerroriste(), 15, 95);					// On "evacue" les terroristes
			System.out.println(c.getEntity().getCrimeTerroriste() + " Terroriste(s) a(ont) ete incarcere(s)");
			c.cumulTerreur(-c.getEntity().getCrimeTerroriste());
		} else {
			this.recette += influence*5000/100;
			c.cumulTerreur(influence);
			p.retraitPopulation(influence, 15, 95);						// On evacue les terroristes
			System.out.println(influence + " Terroriste(s) a(ont) ete incarcere(s)");
		}
	}
	public void afficheEfficacite(BudgetService b){
		System.out.println(this.pInfluence*this.potentiel(b)/100*(400+EducationService.getEdSecurite())/500);
	}

	public int getRecette(){
		return this.recette;
	}
	public static int getTolerance(){
		return tolerance;
	}
	public static void setTolerance(int val){
		tolerance = val;
	}
	@Override
	public void prisePostes() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ajoutPoste() {
		// TODO Auto-generated method stub
		
	}
	
//		// Fonctions communes
//	public static void allSecure(){
//		for(int i=0; i<commissariats.size(); i++)
//			commissariats.get(i).secure();
//	}
//	public static int leveRecettes(){
//		int r=0;
//		for(int i=0; i<commissariats.size(); i++)
//			r += commissariats.get(i).getRecette();
//		return r;
//	}
//	public static int Efficacites(){
//		int eff = 0;
//		for (int i = 0 ; i<commissariats.size(); i++)
//			eff += commissariats.get(i).getpInfluence();
//		return eff;
//}
//	public int getpInfluence() {
//		return pInfluence;
//	}
//	public void setpInfluence(int pInfluence) {
//		this.pInfluence = pInfluence;
//	}
//	public static List<Commissariat> getCommissariats() {
//		return commissariats;
//	}
//	public static void setCommissariats(List<Commissariat> commissariats) {
//		Commissariat.commissariats = commissariats;
//	}
//	public int getIndiceCom() {
//		return indiceCom;
//	}
//	public void setIndiceCom(int indiceCom) {
//		this.indiceCom = indiceCom;
//	}
//	public void setRecette(int recette) {
//		this.recette = recette;
//	}
}
