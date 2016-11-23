package fr.gameEntities.batiments;

import java.util.ArrayList;
import java.util.List;


import fr.gameEntities.batiments.Commissariat;
import fr.entities.BackupConstruction;
import fr.gameEntities.indicateurs.Budget;
import fr.gameEntities.indicateurs.Criminalite;
//import fr.gameEntities.indicateurs.Education;
import fr.gameEntities.indicateurs.Population;
import fr.interfaces.IBatiment;
import fr.interfaces.IEntity;

public class Commissariat extends Batiment<BackupConstruction>{
	private int pInfluence;
	private static int tolerance = 0;			// en %, quantite de crime qui ne sont pas verbalises -> influe sur la recette des contraventions et sur la satisfaction de la population
	private int recette;
	private static List<Commissariat> commissariats = new ArrayList<Commissariat>();
	private int indiceCom;
	
		// Constructeurs
	public Commissariat (){
		super(60, 20, 2, 1);					// Appelle le constructeur de la classe mere pour stocker le batiment cree dans la liste et confier l'indice a l'instance, utile pour �tre dettruite
		commissariats.add(this);				// Ajoute le commissariat dans la liste pour faciliter sa suppression ainsi que les action securisantes
		this.indiceCom = commissariats.size()-1;
		this.pInfluence = this.entity.getNbSalarie()*(10+this.entity.getNbCadre()/10)/100; //le Potentiel d'influence est l'attribut qui permet aux commissariat de nettoyer la racaille et les criminels. Moins efficace contre le terrorisme
	}
	public Commissariat (int niv){	// Possibilite de onstruire un commissariat plus grand
		this();
		for (int i=1; i<niv; i++)
			this.ameliore();
	}
	
		// Fonctions individuelles
	public void ameliore (){		// Permet d'augmenter le potentiel dd'un commissariat
		super.ameliore(0.4, 0.2, 2, 1);
		this.pInfluence = this.nbSalarie*(10+this.nbCadre/10)/100;
	}
	public void detruire(){
		super.detruire(this.indice);
		commissariats.remove(this.indiceCom);
		for (int i = this.indice; i < commissariats.size(); i++)
			commissariats.get(i).indiceCom --;
	}
	public void afficheCommissariat(){
		System.out.println("nb Salaries : " + this.nbSalarie/10);
		System.out.println("nb Cadres : " + this.nbCadre/10);
		System.out.println("Usure : " + this.risque + "/1000");
		System.out.println("Influence : " + this.pInfluence);
	}
	public void secure(){	// La capacite des commissariats depend de leur budget ainsi que de l'education / formation re�ue
		int influence = this.pInfluence*this.potentiel()/100*(300+Education.getEdSecurite())/500;		// N'est effectif qu'a 60% si l'education est nulle.
		System.out.println("Influence actuelle = " + influence);
		if (Criminalite.getCrimeMineur() < influence/2){			//50% de l'influence est dirigee vers les crimes mineurs
			influence -= Criminalite.getCrimeMineur();
			this.recette += Criminalite.getCrimeMineur()*(100-tolerance)*35/100;		// Recette des amendes mineurs
			Criminalite.cumulMineur(-Criminalite.getCrimeMineur());
		} else {
			influence = influence/2;
			this.recette += influence*(100-tolerance)*35/100;
			Criminalite.cumulMineur(influence);
		}
		if (Criminalite.getCrimeMoyen() < influence/2){				//25% de l'influence est dirigee vers les crimes moyens
			influence -= Criminalite.getCrimeMoyen();				// Si le crime mineur n'est pas assez repandu, les effectifs sont rediriges vers des gestions de crime plus graves
			this.recette += Criminalite.getCrimeMoyen()*(100-tolerance)*60/100;			// Recette des amendes intermediaires
			Criminalite.cumulMoyen(-Criminalite.getCrimeMoyen());
		} else {
			influence = influence/2;
			this.recette += influence*(100-tolerance)*60/100;
			Criminalite.cumulMoyen(influence);
		}
		if (Criminalite.getCrimeGrave() < influence*4/5){			//20% de l'influence est dirigee vers les crimes graves
			influence -= Criminalite.getCrimeGrave();
			this.recette += Criminalite.getCrimeGrave()*(100-tolerance)*120/100;			// Recette des amendes lourdes
			Criminalite.cumulGrave(-Criminalite.getCrimeGrave());
		} else {
			this.recette += influence*4*(100-tolerance)*120/100/5;
			Criminalite.cumulGrave(influence*4/5);
			influence = influence/5;
		}
		if (Criminalite.getCrimeTerroriste() < influence){									//5% de l'influence est dirigee vers le terrorisme
			this.recette += Criminalite.getCrimeTerroriste()*5000/100;						// Pas de tolerance pour le terrorrisme
			Population.retraitPopulation(Criminalite.getCrimeTerroriste(), 15, 95);					// On "evacue" les terroristes
			System.out.println(Criminalite.getCrimeTerroriste() + " Terroriste(s) a(ont) ete incarcere(s)");
			Criminalite.cumulTerreur(-Criminalite.getCrimeTerroriste());
		} else {
			this.recette += influence*5000/100;
			Criminalite.cumulTerreur(influence);
			Population.retraitPopulation(influence, 15, 95);						// On evacue les terroristes
			System.out.println(influence + " Terroriste(s) a(ont) ete incarcere(s)");
			
		}
	}
	public void afficheEfficacite(){
		System.out.println(this.pInfluence*this.potentiel()/100*(400+Education.getEdSecurite())/500);
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
	
		// Fonctions communes
	public static void allSecure(){
		for(int i=0; i<commissariats.size(); i++)
			commissariats.get(i).secure();
	}
	public static int leveRecettes(){
		int r=0;
		for(int i=0; i<commissariats.size(); i++)
			r += commissariats.get(i).getRecette();
		return r;
	}
	public static int Efficacites(){
		int eff = 0;
		for (int i = 0 ; i<commissariats.size(); i++)
			eff += commissariats.get(i).getpInfluence();
		return eff;
}
	public int getpInfluence() {
		return pInfluence;
	}
	public void setpInfluence(int pInfluence) {
		this.pInfluence = pInfluence;
	}
	public static List<Commissariat> getCommissariats() {
		return commissariats;
	}
	public static void setCommissariats(List<Commissariat> commissariats) {
		Commissariat.commissariats = commissariats;
	}
	public int getIndiceCom() {
		return indiceCom;
	}
	public void setIndiceCom(int indiceCom) {
		this.indiceCom = indiceCom;
	}
	public void setRecette(int recette) {
		this.recette = recette;
	}
}
