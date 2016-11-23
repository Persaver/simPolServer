package fr.interfaces;

import java.util.List;

public interface IConstruction extends IGameEntity{
	
	public void ameliore();
	
	public int getPostePourvu();
	
	public void prisePostes();
	
	public void ajoutPoste();

	public static void usures();
	
	public static int getNbActifs(){
		return getNbSalaries()+getNbCadres();
	}
	public static int getAttractivites(){
		int att = 0;
		for (int i = 0; i<constructions.size();i++)
			att += constructions.get(i).getAttractivite();
		return att;
	}
	public static int getBudgets(){
		int b= 0;
		for (int i = 0; i<constructions.size();i++)
			b += constructions.get(i).getBudget();
		return b;
	}
	public static int getPostesPourvus(){
		int p = 0;
		for (int i = 0; i<constructions.size();i++)
			p += constructions.get(i).getPostePourvu();
		return p;
	}
	public static void effectifs(){
		int total = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite());
		int effectif = getPostesPourvus();
		int i = (int)Math.random()*constructions.size();
		while(total < effectif){		// S'il y a plus de postes que d'employes, on supprime des postes pouvus
			effectif -= constructions.get(i).retirerPersonnel();
			i += ((int)Math.random()*10)%constructions.size();
		}
	}
	public static void sabotage(int amplitude){
		int i = (int)Math.random()*constructions.size();
		constructions.get(i).modifierRisque(amplitude);
	}
	
		// Getter & Setter
	public static List<Batiment> getConstructions() {
		return constructions;
	}
	public static void setConstructions(List<Batiment> constructions) {
		Batiment.constructions = constructions;
	}
}
