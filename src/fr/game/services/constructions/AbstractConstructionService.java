package fr.game.services.constructions;

import java.util.List;

import fr.Dao.BackupConstructionDAO;
import fr.entities.BackupConstruction;
import fr.entities.Construction;
import fr.game.services.gameControllers.AbstractGameEntity;
import fr.game.services.indicateurs.BudgetService;
import fr.game.services.indicateurs.PopulationService;
import fr.interfaces.IConstruction;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;

public abstract class AbstractConstructionService extends AbstractGameEntity<BackupConstruction, BackupConstructionDAO> implements IConstruction{
	// on cree une varible construction
	// => this.entity = BackupConstruction
	//    this.construction = Construction
	protected Construction construction;
	protected BackupConstructionDAO constructionDAO;

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
	// Le potentiel sera utilise pour chaque batiment et evaluer son rendement.
	public int potentiel(BudgetService b) {		// Le budget influe directement sur les capacites du batiment a 30% du budget necessaire le batiment n'a plus de potentiel
		int potentiel =  (this.entity.getBudget()*100)/(((this.entity.getNbSalarie()/10)*b.getSalaireStandard())+((this.entity.getNbCadre()/10)*b.getSalaireCadre()));
		potentiel = (int)Math.max(((potentiel*100)-3000)/70., 0.);
		return potentiel;
	}
	public void modifierRisque(int mod){
		this.entity.setRisque(this.entity.getRisque() + mod);
	}
	@Override
	public int getPostePourvu(){
		return this.entity.getPostePourvu();
	}
	public int getPostesPourvus(List<AbstractConstructionService> liste){
		int nbPostes = 0;
		for (AbstractConstructionService element : liste){
			nbPostes += element.getPostePourvu();
		}
		return nbPostes;
	}

	public void prisePostes(PopulationService p, BudgetService b, List<AbstractConstructionService> liste){
		int pEmbauche = p.nbIndiv(b.getAgeTravail(), b.getAgeRetraite())-this.getPostesPourvus(liste);
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

	public void ajoutPoste(PopulationService p, BudgetService b, List<AbstractConstructionService> liste){
		int pEmbauche = p.nbIndiv(b.getAgeTravail(), b.getAgeRetraite())-this.getPostesPourvus(liste);
		if (pEmbauche > ((this.entity.getNbSalarie()+this.entity.getNbCadre())/10)) {
			this.entity.setPostePourvu((this.entity.getNbSalarie()+this.entity.getNbCadre())/10);
		} else {
			this.entity.setPostePourvu(this.entity.getPostePourvu() + pEmbauche);
		}
	}
	public int retirerPersonnel(BudgetService b){
		int nbEmploye = this.entity.getNbSalarie()+this.entity.getNbCadre();
		int budgetMax = ((this.entity.getNbSalarie()/10)*b.getSalaireStandard())+((this.entity.getNbCadre()/10)*b.getSalaireCadre());
		if (this.entity.getBudget() > (budgetMax/nbEmploye)){
			this.entity.setBudget(this.entity.getBudget() - (budgetMax/nbEmploye));
			return 1;	// Une personne a bien ete viree
		}
		else {
			return 0;	// Personne n'a ete vire
		}
	}
	public int retirerPersonnel(BudgetService b, int n){
		boolean possible = true;
		int verif;
		while ((n > 0) && possible){
			verif = this.retirerPersonnel(b);
			if (verif == 1) {
				n--;
			} else {
				possible = false;
			}
		}
		return n;
	}

	public void save() throws ServiceException{
		try {
			this.constructionDAO.save(this.entity);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String getName(){
		return this.getEntity().getConstruction().getDesignation() +"_" + this.getEntity().getConstruction();
	}
}

