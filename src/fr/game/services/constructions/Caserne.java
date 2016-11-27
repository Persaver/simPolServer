package fr.game.services.constructions;

import java.util.ArrayList;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.game.services.indicateurs.EducationService;

public class Caserne extends AbstractConstructionService {
	private int pEntretien;		// Potentiel d'entretien des batiments

	public Caserne(BackupConstruction entity, BackupConstructionDAO entityDao){
		super(entity,entityDao);
		this.pEntretien = 6;
	}
	public Caserne(int niv, BackupConstruction entity, BackupConstructionDAO entityDao) {
		this(entity, entityDao);
		for(int i = 0; i<niv; i++)
			this.ameliorer();
	}
	public void ameliorer(){
		super.ameliore(); //(0.3, 0.1, 0, 1);
		this.pEntretien = ((10+this.entity.getNbCadre()/3)*this.entity.getNbSalarie())/50;
	}
//	public void detruire(){
//		super.detruire(this.indice);
//		casernes.remove(this.indiceCas);
//		for (int i = this.indice; i < casernes.size(); i++)
//			casernes.get(i).indiceCas --;
//	}
	public int getEntretien(){
		return this.pEntretien;
	}
	
		// Les employes vont effectuer des inspection.
	public void entretien(EducationService ed){
//		int l = Batiment.constructions.size(); // Récupérer le nombre de batiment
		int e = this.pEntretien*this.potentiel()/100*(300+ed.getEntity().getEdSecurite())/500;
		int indice = (int)(Math.random()*l);
		while (e>0){		// Si un batiment est bien endommage, les ouvriers vont se concentrer dessus
			if (Batiment.constructions.get(indice).getRisque()>=10){
				if (e>=10){
					Batiment.constructions.get(indice).modifierRisque(10);
					e -= 10;
				} else {
					Batiment.constructions.get(indice).modifierRisque(e);
					e = 0;
				}				// Des qu'ils verront des defauts, les ouvriers vont apporter des reparation
			} else
				if (Batiment.constructions.get(indice).getRisque()>0){
				Batiment.constructions.get(indice).modifierRisque(1); // entre deux batiment, le deplacement leur prend du temps (et ca assure d'arriver a 'e=0'
				e--;
			} // Si tout va bien, on recommence jusqu'a ce qu'il soit l'heure d'arreter
			indice = (indice+1)%l;
			e--;
		}
	}

}
