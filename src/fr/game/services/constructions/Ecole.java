package fr.game.services.constructions;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.game.services.batiments.Batiment;
import fr.game.services.indicateurs.Budget;

public class Ecole extends AbstractConstructionService {
	
	
	public Ecole(BackupConstruction entity, BackupConstructionDAO entityDao){
		super(entity,entityDao);
	}
	
//	public Ecole (int niveau){
//		this.nbSalarie = (int)(40*Math.pow(1.5, niveau-1));
//		this.nbCadre = (int)(10*Math.pow(1.2, niveau-1));
//		Budget.setNbCadre(this.nbCadre/10);
//		Budget.setNbSalaries((this.nbSalarie+this.nbCadre)/10);
//	}
	
	public void Ameliore (){
		super.ameliore();
		
//		int newSalarie = (int)(this.entity.getNbSalarie()*0.5);
//		int  newCadre =  (int)(this.entity.getNbCadre()*0.2);
////		if ((((this.entity.getNbCadre() + newCadre)/10>(this.entity.getNbCadre())/10))){				// Pour garder une coherence dans les chiffres
////			Budget.setNbCadre((this.entity.getNbCadre()+newCadre)/10-this.entity.getNbCadre()/10);
////			System.out.println("nbCadres + " + newCadre/10);
////		}
//		this.entity.setNbCadre(this.entity.getNbCadre()+newCadre);
////		if ((this.nbSalarie + newSalarie)/10>(this.nbSalarie)/10){
////			Budget.setNbSalaries((this.nbSalarie+newSalarie)/10-this.nbSalarie/10);
////			System.out.println("nbPoste + " + (newSalarie)/10);
////		}
//		this.entity.setNbSalarie(this.entity.getNbSalarie() + newSalarie);
	}
}
