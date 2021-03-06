package fr.game.services.indicateurs;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fr.Dao.BackupConstructionDAO;
import fr.Dao.SanteDAO;
import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.entities.Sante;
import fr.game.services.gameControllers.AbstractGameEntity;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;

public class SanteService extends AbstractGameEntity<Sante, SanteDAO> {
	private static final Logger LOG = LogManager.getLogger();

	public SanteService(Sante entity, SanteDAO entityDao) {
		super(entity, entityDao);
	}

	public void journeeMedicale(PopulationService p, BudgetService b, Backup backup) throws ServiceException{
		this.tombeMalades(p);				// On comptabilise les nouveaux malades
		LOG.debug("{} gens sont malades",this.entity != null ? this.entity.getNbMalades() : "null");
		this.accidente(p, b, backup);		// de meme pour les accidentes
		LOG.debug("{} gens ont eu un accident",this.entity != null ? this.entity.getNbAccidents() : "null");
		this.mortalite(p, b);				// Parmi ces victimes, certaines ne se reveilleront jamais
		LOG.debug("{} ne sont pas encore morts",this.entity != null ? (this.entity.getNbAccidents() + this.entity.getNbMalades()): "null");
		//System.out.println(this.entity.getNbMalades()+this.entity.getNbAccidents() + " ne sont pas encore morts");
		this.recupSoins(backup);				// Heureusement, les medecins sont la avec leurs competences
		LOG.debug("{} patients vont �tre secourus",this.entity != null ? (this.entity.getNbAccidents() + this.entity.getNbMalades()): "null");
		//System.out.println(this.entity.getSoins() + " patients vont �tre secourus");
		this.apportMedicaux();				// Ces chevaliers de la sante sauvent autant de vies que possible
		LOG.debug("il reste {} malades et {} accidentes",this.entity != null ?  this.entity.getNbMalades(): "null",
														this.entity != null ? this.entity.getNbAccidents() : "null");	
		//System.out.println("il reste " + this.entity.getNbMalades() + " malades et " + this.entity.getNbAccidents() + " accidentes");
		this.guerison();					// Et puis, il y a ceux qui se soignent en mangeant bio
		LOG.debug("{} patients sont encore dans les hopitaux en fin de soiree",this.entity != null ? (this.entity.getNbAccidents() + this.entity.getNbMalades()): "null");
		//System.out.println(this.entity.getNbMalades() + this.entity.getNbAccidents() + " patients sont encore dans les hopitaux en fin de soiree");
	}

	public void tombeMalades (PopulationService p){
		int nbSains =  p.nbIndiv()- this.entity.getNbMalades();
		this.entity.setNbMalades(this.entity.getNbMalades() + (int)((Math.random()*(100-this.entity.getHygiene())*nbSains)/3000));	// actualisation quotidienne du nombre de malades
	}
	public void accidente (PopulationService p, BudgetService b, Backup backup) throws ServiceException{
		// int nbSaufs = p.nbIndiv(0, b.getAgeRetraite())- this.entity.getNbAccidents();	// Seuls ceux qui se deplacent et ne sont pas deja accidente peuvent subir un accident
		// a revoir modif avec list
		List<BackupConstruction> backupConstructions = null;
		int nbAcc = 0;
		try {
			backupConstructions = new BackupConstructionDAO().getAllByBackUpByConstruction(backup, BackupConstructionDAO.getIDHOPITAL());
			for (BackupConstruction element : backupConstructions){
				nbAcc += (int)(Math.random()*(element.getRisque()*element.getPostePourvu()));
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		this.entity.setNbAccidents(this.entity.getNbAccidents() + nbAcc/1000/30);
	}
	public void ajoutBlesse(int nb){			// Victimes d'agressions
		this.entity.setNbAccidents(this.entity.getNbAccidents() + nb);
	}
	public void mortalite (PopulationService p, BudgetService b){
		int mort = (int)(Math.random()*this.entity.getEchecs()*this.entity.getNbAccidents())/100;		// Tous les accidentes ne survivent pas.
		this.entity.setNbAccidents(this.entity.getNbAccidents() - mort);
		p.retraitPopulation(mort, 0, b.getAgeRetraite());
		mort = (int)(Math.random()*this.entity.getEchecs()*this.entity.getNbMalades())/100;			// Les malades non plus
		p.retraitPopulation(mort, 0, 129);
		this.entity.setNbMalades(this.entity.getNbMalades() - mort);
	}
	
		//Fonction a appeler quotidiennement
	public void recupSoins (Backup backup) throws ServiceException{
		List<BackupConstruction> backupConstructions = null;
		int soinsT = 0;
		try {
			backupConstructions = new BackupConstructionDAO().getAllByBackUpByConstruction(backup, BackupConstructionDAO.getIDHOPITAL());
			for (BackupConstruction element : backupConstructions){
				LOG.debug("SANTESREVICE recupSoins element.getSpecificite() {}", element.getSpecificite() != null ? element.getSpecificite() : "null" );
				soinsT += element.getSpecificite().get("soins");	// La clef des map Ecoles doit etre 'soins'
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		this.entity.setSoins(soinsT);
	}

	public void apportMedicaux(){
		int patients = this.entity.getNbAccidents()+this.entity.getNbMalades();
		int ratio = (this.entity.getNbMalades()*100)/patients;		// reparti les efforts medicaux
		if (((this.entity.getSoins()*ratio)/100) > this.entity.getNbMalades()){
			this.entity.setSoins(this.entity.getSoins() -this.entity.getNbMalades());
			this.entity.setNbMalades(0);
		} else {
			this.entity.setNbMalades(this.entity.getNbMalades() - ((this.entity.getSoins()*ratio)/100));
			this.entity.setSoins((this.entity.getSoins()*ratio)/100);
		}										// L'aide medicale pour les malades a deja ete utilisee, tout le reste est dedie aux accidents
		if (this.entity.getSoins() > this.entity.getNbAccidents()) {
			this.entity.setNbAccidents(0);
		} else {
			this.entity.setNbAccidents(this.entity.getNbAccidents() - this.entity.getSoins());
		}
		this.entity.setSoins(0);								// l'aide medicale qui n'a pas ete utilisee est perdue
	}

	public void guerison(){
		this.entity.setNbMalades(this.entity.getNbMalades() - (int)((Math.random()*this.entity.getHygiene()*this.entity.getNbMalades())/1000));		// parmi les malades entre 0 et 10% guerissent tout seuls en fonctio de l'hygiene
		this.entity.setNbAccidents(this.entity.getNbAccidents() - (int)((Math.random()*this.entity.getHygiene()*this.entity.getNbAccidents())/1000));	// idem pour les maladroits
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
