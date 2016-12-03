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
		//this.prisePostes(null,null);
	}
	@Override
	public void ameliore(){
		this.getEntity().setNbCadre(this.getEntity().getNbCadre()*this.construction.getModCadre());
		this.getEntity().setNbSalarie(this.getEntity().getNbSalarie()*this.construction.getModSalarie());
		this.getEntity().setRisque(this.getEntity().getRisque()+this.construction.getModRisque());
		this.getEntity().setAttractivite(this.getEntity().getAttractivite()+this.construction.getModAttractivite());
		this.entity.setNiveau(this.entity.getNiveau()+1);
	}
	// fonction utilitaires
	// Le potentiel sera utilise pour chaque batiment et evaluer son rendement.
	public int potentiel() {		// Le budget influe directement sur les capacites du batiment a 30% du budget necessaire le batiment n'a plus de potentiel
		int ratioC = this.entity.getNbCadre()*100/this.getEntity().getNbCadre(); // en %
		int ratioS = this.entity.getNbSalarie()*100/this.getEntity().getNbSalarie();
		int ratio = this.getEntity().getNbSalarie() *100/(this.getEntity().getNbCadre()+this.getEntity().getNbSalarie());
		return ratioC*(100-ratio)/100+ratioS*(ratio)/100;
	}
	public void modifierRisque(int mod){
		this.entity.setRisque(this.entity.getRisque() + mod);
	}
	public void usure(){
		this.entity.setRisque(this.entity.getRisque()+this.construction.getModRisque());
	}
	
		// Tout ce qui concerne les employes
	@Override
	public int getPostePourvu(){
		return this.entity.getPostePourvu();
	}
	public int getPostesPourvus() throws ServiceException{
		List<BackupConstruction> liste;
		try {
			liste = this.constructionDAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		int nbPostes = 0;
		for (BackupConstruction element : liste){
			nbPostes += element.getPostePourvu();
		}
		return nbPostes;
	}
	public void prisePostes(PopulationService p, BudgetService b) throws ServiceException{
			// On verifie que les demandeurs d'emploi sont assez nombreux
		int pEmbauche = p.nbIndiv(b.getAgeTravail(), b.getAgeRetraite())-getPostesPourvus();
		if (pEmbauche <= 0) {
			this.entity.setPostePourvu(0);
		} else{
			if (pEmbauche > ((this.entity.getNbSalarie()+this.entity.getNbCadre())/10)) {
					// S'il y a des demandeurs d'emploi, mais pas assez pour remplir tous les postes a pourvoir, le batiment recrute tous ceux qu'il peut
				int cadres = (int)(Math.random()*pEmbauche/4+1);
				int salaries = pEmbauche - cadres;
				this.modifierCadre(b, cadres);
				this.modifierSalarie(b, salaries);
			} else {
					// Si les demandeurs d'emploi sont legion
				this.entity.setNbCadre(this.getEntity().getNbCadre());
				this.entity.setNbCadre(this.getEntity().getNbSalarie());
				this.entity.setPostePourvu(pEmbauche);
			}
		}
	}
	public void modifierCadre(BudgetService b, int n){
		if (n < 0){
			for (int i = 0; i > n; i--){
				this.retirerCadre(b);
			}
		} else {
			for (int i = 0; i > n; i--){
				this.ajouterCadre(b);
			}
		}
	}
	public void modifierSalarie(BudgetService b, int n){
		if (n < 0){
			for (int i = 0; i > n; i--){
				this.retirerSalarie(b);
			}
		} else {
			for (int i = 0; i > n; i--){
				this.ajouterSalarie(b);
			}
		}
	}
	public void retirerCadre(BudgetService b){
		if (this.entity.getNbCadre() > 9){			// >9 car les postes sont comptabilise en fonction des dizaines
			this.entity.setNbCadre(this.entity.getNbCadre()-10);
			this.entity.setBudget(this.entity.getBudget()-b.getSalaireCadre());
			this.entity.setPostePourvu(this.entity.getPostePourvu()-10);
		}
	}
	public void retirerSalarie(BudgetService b){
		if (this.entity.getNbSalarie() > 9){		// >9 car les postes sont comptabilise en fonction des dizaines
			this.entity.setNbSalarie(this.entity.getNbSalarie()-10);
			this.entity.setBudget(this.entity.getBudget()-b.getSalaireStandard());
			this.entity.setPostePourvu(this.entity.getPostePourvu()-10);
		}
	}
	public void ajouterCadre(BudgetService b){
			// la limite du nombre de Cadre varie en fonction du type de batiment et son niveau
		int limitPost = this.getEntity().getNbCadre()+(this.getEntity().getNbCadre()*this.construction.getModCadre()*(this.entity.getNiveau()-1));
		if (this.entity.getNbCadre() < limitPost){
			this.entity.setNbCadre(this.entity.getNbCadre()+10);
			this.entity.setBudget(this.entity.getBudget()+b.getSalaireCadre());
			this.entity.setPostePourvu(this.entity.getPostePourvu()+10);
		}
	}
	public void ajouterSalarie(BudgetService b){
			// la limite du nombre de Salarie varie en fonction du type de batiment et son niveau
		int limitPost = this.getEntity().getNbSalarie()+(this.getEntity().getNbSalarie()*this.construction.getModSalarie()*(this.entity.getNiveau()-1));	
		if (this.entity.getNbSalarie() < limitPost){
			this.entity.setNbSalarie(this.entity.getNbSalarie()+10);
			this.entity.setBudget(this.entity.getBudget()+b.getSalaireStandard());
			this.entity.setPostePourvu(this.entity.getPostePourvu()+10);
		}
	}
		// Recupere le nombre total de cadres
	public int nbTotCadres () throws ServiceException{
		int nbC = 0;
		try {
			List<BackupConstruction> liste = this.constructionDAO.getAll();
			for (BackupConstruction element : liste){
				nbC += element.getNbCadre();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		return nbC;
	}
		// Recupere le nombre total de salarie
	public int nbtoSalaries () throws ServiceException{
		int nbS = 0;
		try {
			List<BackupConstruction> liste = this.constructionDAO.getAll();
			for (BackupConstruction element : liste){
				nbS += element.getNbSalarie();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		return nbS;
	}
		// transfert les nombres de cadres et de salaries a la page du budget
	public void communicationInfosBudget (BudgetService b) throws ServiceException{
		b.setNbCadre(this.nbTotCadres());
		b.setNbSalaries(this.nbtoSalaries());
	}
		
	
	public void save() throws ServiceException{
		try {
			this.constructionDAO.save(this.entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String getName(){
		return this.getEntity().getConstruction().getDesignation() +"_" + this.getEntity().getConstruction();
	}
}

