package fr.game.services.constructions;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.entities.Budget;
import fr.entities.Construction;
import fr.entities.Population;
import fr.game.services.gameControllers.AbstractGameEntity;
import fr.interfaces.IConstruction;

public abstract class AbstractConstructionService extends AbstractGameEntity<BackupConstruction, BackupConstructionDAO> implements IConstruction{

	// on cree une varible construction
	// => this.entity = BackupConstruction
	//    this.construction = Construction
	private Construction construction;
	private BackupConstructionDAO constructionDAO;


	public AbstractConstructionService(BackupConstruction entity, BackupConstructionDAO entityDao,int baseS, int baseC, int baseR, int att) {
		super(entity, entityDao);
		this.setEntity(this.getEntity());
		this.construction.setBaseSalarie(baseS);
		this.construction.setBaseCadre(baseC);
		this.construction.setModRisque(baseR);
		this.construction.setBaseAttractivite(att);
		this.prisePostes();
	}

	@Override
	public void ameliore(){
		this.getEntity().setNbSalarie(6);
	}

	@Override
	public int getPostePourvu(){
		return this.entity.getPostePourvu();
	}
	@Override
	public void prisePostes(){
		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		if (pEmbauche <= 0) {
			this.entity.setPostePourvu(0);
		} else{
			if (pEmbauche > ((this.entity.getNbSalarie()+this.entity.getNbCadre())/10)) {
				this.entity.setPostePourvu((this.entity.getNbSalarie()+this.entity.getNbCadre())/10);
			} else {
				this.entity.setPostePourvu(pEmbauche);
			}
		}
	}
	@Override
	public void ajoutPoste(){
		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		if (pEmbauche > ((this.entity.getNbSalarie()+this.entity.getNbCadre())/10)) {
			this.entity.setPostePourvu((this.entity.getNbSalarie()+this.entity.getNbCadre())/10);
		} else {
			this.entity.setPostePourvu(this.entity.getPostePourvu() + pEmbauche);
		}
	}

	public void save(){
		this.constructionDAO.save(this.entity);
	}
}

