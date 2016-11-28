package fr.game.services.constructions;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.game.services.indicateurs.BudgetService;

public class Ecole extends AbstractConstructionService {
	private int pEducation; // Le potentiel d'education de l'ecole
	
	public Ecole(BackupConstruction entity, BackupConstructionDAO entityDao) {
		super(entity, entityDao);
		this.entity.setNbSalarie(40);
		this.entity.setNbCadre(10);
		this.entity.setRisque(3);
		this.entity.setAttractivite(1);;
		this.setpEducation(40);
		// TODO Auto-generated constructor stub
	}
	
	public void ameliore(){
		super.ameliore();
		this.pEducation += (int)(this.pEducation*.5);
	}
	
	public int education(BudgetService b){
		int e = this.pEducation*this.potentiel(b)*5/700;		// 5 jours par semaine
		return e;
	}
	
public int getpEducation() {
		return this.pEducation;
	}
	public void setpEducation(int pEducation) {
		this.pEducation = pEducation;
	}
}
