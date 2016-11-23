package fr.game.services.batiments;

import fr.game.services.indicateurs.Budget;

public class Ecole extends Batiment {
	
	public Ecole (){
		this.nbSalarie = 40;
		this.nbCadre = 10;
		Budget.setNbCadre(1);
		Budget.setNbSalaries(4);
	}
	
	public Ecole (int niveau){
		this.nbSalarie = (int)(40*Math.pow(1.5, niveau-1));
		this.nbCadre = (int)(10*Math.pow(1.2, niveau-1));
		Budget.setNbCadre(this.nbCadre/10);
		Budget.setNbSalaries((this.nbSalarie+this.nbCadre)/10);
	}
	
	public void Ameliore (){
		int newSalarie = (int)(this.nbSalarie*0.5);
		int  newCadre =  (int)(this.nbCadre*0.2);
		if ((((this.nbCadre + newCadre)/10>(this.nbCadre)/10))){				// Pour garder une coherence dans les chiffres
			Budget.setNbCadre((this.nbCadre+newCadre)/10-this.nbCadre/10);
			System.out.println("nbCadres + " + newCadre/10);
		}
		this.nbCadre += newCadre;
		if ((this.nbSalarie + newSalarie)/10>(this.nbSalarie)/10){
			Budget.setNbSalaries((this.nbSalarie+newSalarie)/10-this.nbSalarie/10);
			System.out.println("nbPoste + " + (newSalarie)/10);
		}
		this.nbSalarie += newSalarie;
	}
}
