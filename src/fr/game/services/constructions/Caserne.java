package fr.game.services.constructions;

import java.util.List;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.game.services.indicateurs.EducationService;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;

public class Caserne extends AbstractConstructionService {
	private int pEntretien;		// Potentiel d'entretien des batiments

	public Caserne(BackupConstruction entity, BackupConstructionDAO entityDao){
		super(entity,entityDao);
		this.entity.setNbSalarie(80);
		this.entity.setNbCadre(20);
		this.entity.setRisque(0);
		this.entity.setAttractivite(1);
		this.setEntretien(6);
	}
	public Caserne(int niv, BackupConstruction entity, BackupConstructionDAO entityDao) {
		this(entity, entityDao);
		for(int i = 0; i<niv; i++) {
			this.ameliorer();
		}

	}
	public void ameliorer(){
		super.ameliore();
		this.pEntretien = ((10+(this.entity.getNbCadre()/3))*this.entity.getNbSalarie())/50;
	}
	/*	public void detruire(){
	/		super.detruire(this.indice);
	*		casernes.remove(this.indiceCas);
	*		for (int i = this.indice; i < casernes.size(); i++)
	*			casernes.get(i).indiceCas --;
		}*/
	public int getEntretien(){
		return this.pEntretien;
	}
	public void setEntretien(int pEntretien) {
		this.pEntretien = pEntretien;
	}
	// Les employes vont effectuer des inspection.
	public void entretien() throws ServiceException{
		List<BackupConstruction> liste;
		try {
			liste = this.entityDao.getAll();
		
		int l = liste.size();
		int e = (((this.pEntretien*this.potentiel())/100)*(300+EducationService.getEdSecurite()))/500;
		int indice = (int)(Math.random()*l);
		while (e>0){		// Si un batiment est bien endommage, les ouvriers vont se concentrer dessus
			if (liste.get(indice).getRisque()>=10){
				if (e>=10){
					liste.get(indice).setRisque(liste.get(indice).getRisque()-10);
					e -= 10;
				} else {
					liste.get(indice).setRisque(liste.get(indice).getRisque()-e);
					e = 0;
				}				// Des qu'ils verront des defauts, les ouvriers vont apporter des reparation
			} else
				if (liste.get(indice).getRisque()>0){
					liste.get(indice).setRisque(liste.get(indice).getRisque()-1); // entre deux batiment, le deplacement leur prend du temps (et ca assure d'arriver a 'e=0'
					e--;
				} // Si tout va bien, on recommence jusqu'a ce qu'il soit l'heure d'arreter
			indice = (indice+1)%l;
			e--;
		}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
	}
}