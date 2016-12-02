package fr.game.services.gameControllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.Dao.BudgetDAO;
import fr.Dao.EducationDAO;
import fr.Dao.PopulationDAO;
import fr.Dao.SanteDAO;
import fr.entities.Backup;
import fr.entities.Budget;
import fr.entities.Population;
import fr.entities.Sante;
import fr.entities.User;
import fr.game.services.indicateurs.BudgetService;
import fr.game.services.indicateurs.PopulationService;
import fr.game.services.indicateurs.SanteService;
import fr.interfaces.IEntity;
import fr.interfaces.IGameInstance;
import fr.splExceptions.ServiceException;

public class GameInstance implements IGameInstance{
	private static final Logger LOG = LogManager.getLogger();

	private String key;
	private User user = null;
	private EntitiesController entityController = null;
	private Backup backup = null;
	private BackupDAO backupDAO = null;
	private BackupConstructionDAO backupConstructionDAO =null;
		/* Ajout Robin */
	private PopulationDAO populationDAO = null;
	private SanteDAO santeDAO = null;
	private EducationDAO educationDAO = null;
	private BudgetDAO budgetDAO = null;

	public GameInstance(User user, BackupDAO backupDAO,BackupConstructionDAO backupConstructionDAO,Integer IdBackup){
		if(user != null){
			this.user = user;
		}
		this.entityController = new EntitiesController();
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EntitiesController getentityController() {
		return this.entityController;
	}

	public void setentityController(EntitiesController entityController) {
		this.entityController = entityController;
	}

	public Backup getBackup() {
		return this.backup;
	}

	public void setBackup(Backup backup) {
		this.backup = backup;
	}
	// recup tous les batiments
	@Override
	public List<IEntity> getEntities() throws ServiceException {
		return this.entityController.getGameEntitiesFromDao(backup.getId());
	}
	
	
	


	private void start() throws ServiceException{
		PopulationService p = new PopulationService(new Population(), this.populationDAO, 200);
		SanteService s = new SanteService(new Sante(), this.santeDAO);
		BudgetService b = new BudgetService(new Budget(), this.budgetDAO);
		try {
			p.quotidien(this.backup);
			LOG.info("une population a bien ete cree");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		s.journeeMedicale(p, b, this.backup);
		LOG.info("l'equipe medicale est en place");		
	}
}
