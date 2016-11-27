package fr.game.services.constructions;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;

public class Hopital extends AbstractConstructionService {
	private int pSoin;	// potentiel de soins prodigues par l'hopital
	
	public Hopital (BackupConstruction entity, BackupConstructionDAO entityDao){
		super(entity, entityDao);	//(70, 10, 3, 2);
		this.pSoin = this.entity.getNbSalarie()*(10+this.entity.getNbCadre()/5)/100;
	}
	public Hopital (int niv, BackupConstruction entity, BackupConstructionDAO entityDao){
		this(entity, entityDao);
		for (int i = 0; i<niv; i++)
			this.ameliorer();
	}
	
	public void ameliorer(){
		super.ameliore(); 	//(0.8, 0.8, 3, 2);
		this.pSoin = this.entity.getNbSalarie()*(10+this.entity.getNbCadre()/5)/100;
	}
	
	public void detruire (){
//		super.detruire(this.indice);
//		hopitaux.remove(this.indiceH);
//		for (int i = this.indiceH; i < hopitaux.size(); i++)
//			hopitaux.get(i).indiceH --;
	}
	
	public int soins (){
		return this.pSoin*this.potentiel()/100*(300+Education.getEdSante())/500;	// N'est qu'a 60% si l'education est nulle
	}
	
//	public static int soinsTotal(){
//		int sT = 0;
//		for (int i = 0; i<hopitaux.size(); i++)
//			sT += hopitaux.get(i).soins();
//		return sT;		
//	}

}
