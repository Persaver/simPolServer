package fr.game.services.constructions;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.game.services.indicateurs.BudgetService;
import fr.game.services.indicateurs.EducationService;
import fr.game.services.indicateurs.PopulationService;

public class Cinema  extends AbstractConstructionService {
	private int nbPlaces;
	private int prixPlace = 5;

	public Cinema(BackupConstruction entity, BackupConstructionDAO entityDao) {
		super(entity, entityDao); 	//(40, 10, 3, 3)
		this.entity.setNbSalarie(40);
		this.entity.setNbCadre(10);
		this.entity.setRisque(2);
		this.entity.setAttractivite(5);
		this.setNbPlaces(120);
	}
	public Cinema(int niv, BackupConstruction entity, BackupConstructionDAO entityDao){
		this(entity, entityDao);
		for (int i=0; i<niv; i++)
			this.ameliore();
	}
	@Override
	public void ameliore(){
		super.ameliore();
		this.setNbPlaces(this.getNbPlaces()*15/10);
	}
/* Le cinema est, en cas optimal, occupe entre 80 et 100%
 * La pauvrete diminue l'efficacite des Cinema
 * L'education favorise la communication et influe donc sur les recettes
 * 8 fois par jour 
 */
	public int gains(EducationService e, BudgetService b, PopulationService p){	
		return (this.getNbPlaces()-((int)Math.random()*20*this.getNbPlaces()/100))*b.pauvrete(p)/100*this.prixPlace*this.potentiel()/100*(400+EducationService.getEdSecurite())/500*8;
	}
	public int getPrixPlace() {
		return prixPlace;
	}
	public void setPrixPlace(int prixPlace) {
		this.prixPlace = prixPlace;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
}
