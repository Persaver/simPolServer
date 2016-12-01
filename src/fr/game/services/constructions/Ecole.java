package fr.game.services.constructions;

import java.util.Map;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;

public class Ecole extends AbstractConstructionService {
	private int pEducation; // Le potentiel d'education de l'ecole

	public Ecole(BackupConstruction entity, BackupConstructionDAO entityDao) {
		super(entity, entityDao);
		this.entity.setNbSalarie(40);
		this.entity.setNbCadre(10);
		this.entity.setRisque(3);
		this.entity.setAttractivite(1);
		//  a revoir pour sauvergarde construction this.getEntity().getConstruction().
		this.setpEducation(40);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void ameliore(){
		super.ameliore();
		this.pEducation += (int)(this.pEducation*.5);
	}
	public void education(){
		Map <String, Integer> educ = null;
		educ.put("education", (this.pEducation*this.potentiel())/100);
		this.entity.setSpecificite(educ);
	}
	public int getpEducation() {
		return this.pEducation;
	}
	public void setpEducation(int pEducation) {
		this.pEducation = pEducation;
	}
}
