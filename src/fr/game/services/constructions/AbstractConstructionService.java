package fr.game.services.constructions;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.entities.Construction;
import fr.game.services.gameControllers.AbstractGameEntity;
import fr.game.services.indicateurs.BudgetService;
import fr.interfaces.IConstruction;

public abstract class AbstractConstructionService extends AbstractGameEntity<BackupConstruction, BackupConstructionDAO> implements IConstruction{

	// on cree une varible construction
	// => this.entity = BackupConstruction
	//    this.construction = Construction
	private Construction construction;
	private BackupConstructionDAO constructionDAO;


	public AbstractConstructionService(BackupConstruction entity, BackupConstructionDAO entityDao) {
		super(entity, entityDao);
		this.prisePostes();
	}

	@Override
	public void ameliore(){
		this.getEntity().setNbCadre(this.entity.getNbCadre()*this.construction.getModCadre());
		this.getEntity().setNbSalarie(this.entity.getNbSalarie()*this.construction.getModSalarie());
//		this.getEntity().setNbSalarie(6);  //A quoi sert ce code? (Robin)
	}
	
		// fonction utilitaires
	public int potentiel(BudgetService b) {		// Le budget influe directement sur les capacites du batiment a 30% du budget necessaire le batiment n'a plus de potentiel
		int potentiel =  this.entity.getBudget()*100/(this.entity.getNbSalarie()/10*b.getSalaireStandard()+this.entity.getNbCadre()/10*b.getSalaireCadre());
		potentiel = (int)Math.max((potentiel*100-3000)/70., 0.);
		return potentiel;
	}
	public void modifierRisque(int mod){
		this.entity.setRisque(this.entity.getRisque() + mod);
	}

	@Override
	public int getPostePourvu(){
		return this.entity.getPostePourvu();
	}
	@Override
	public void prisePostes(){
		//		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		//		if (pEmbauche <= 0) {
		//			this.entity.setPostePourvu(0);
		//		} else{
		//			if (pEmbauche > ((this.entity.getNbSalarie()+this.entity.getNbCadre())/10)) {
		//				this.entity.setPostePourvu((this.entity.getNbSalarie()+this.entity.getNbCadre())/10);
		//			} else {
		//				this.entity.setPostePourvu(pEmbauche);
		//			}
		//		}
	}
	@Override
	public void ajoutPoste(){
		//		int pEmbauche = Population.nbIndiv(Budget.getAgeTravail(), Budget.getAgeRetraite())-getPostesPourvus();
		//		if (pEmbauche > ((this.entity.getNbSalarie()+this.entity.getNbCadre())/10)) {
		//			this.entity.setPostePourvu((this.entity.getNbSalarie()+this.entity.getNbCadre())/10);
		//		} else {
		//			this.entity.setPostePourvu(this.entity.getPostePourvu() + pEmbauche);
		//		}
	}

	public void save(){
		this.constructionDAO.save(this.entity);
	}

	@Override
	public String getName(){
		return this.getEntity().getConstruction().getDesignation() +"_" + this.getEntity().getConstruction();

	}
}

