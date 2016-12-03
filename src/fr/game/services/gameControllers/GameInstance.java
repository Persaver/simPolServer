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
import fr.splExceptions.DAOException;
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
	
	public GameInstance(User user,Backup backup){
		this(user,new BackupDAO(), new BackupConstructionDAO(),backup);
		LOG.debug("GameInstance ");
		
	}

	public GameInstance(User user, BackupDAO backupDAO,BackupConstructionDAO backupConstructionDAO,Backup backup){
		if(user != null){
			this.user = user;
		}
		this.backup = backup;
		this.backupDAO = backupDAO;
		this.backupConstructionDAO = backupConstructionDAO;
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

	public void start() throws ServiceException{
		LOG.debug("GameInstance start");
		PopulationDAO populationDAO= new PopulationDAO();
		SanteDAO santeDAO = new SanteDAO();
		BudgetDAO budgetDAO = new BudgetDAO();
		Population pop = null;
		Sante sante = null;
		Budget budget = null;
		LOG.debug("GameInstance Backup id : {}", this.backup != null ? this.backup.getId() : "null");
		try {
			pop = populationDAO.getByBackup(this.backup);
			LOG.debug("GameInstance pop {} class {}",pop != null ? pop.getPopTab() : "null",pop != null ? pop.getClass().getName() : "null");

			PopulationService p = new PopulationService(pop, populationDAO, 200);
			LOG.debug("GameInstance PopulationService {} ",p != null ? p.getClass().getName() : "null");
			sante = santeDAO.getByBackup(this.backup);
			SanteService s = new SanteService(sante, santeDAO);
			budget = budgetDAO.getByBackup(this.backup);
			BudgetService b = new BudgetService(budget, budgetDAO);
			LOG.debug("GameInstance pop {} sante {} budget {} ",p != null ? p.getClass().getName() : "null", s != null ? s.getClass().getName() : "null", b != null ? b.getClass().getName() : "null");
			p.quotidien(this.backup);
			s.journeeMedicale(p, b, this.backup);
			LOG.debug("une population a bien ete cree");
			
			// test sauvegarde
			p.saveEntity();
			LOG.debug("une population a bien ete sauvegarder");
			b.saveEntity();
			s.saveEntity();
			LOG.debug("une population a bien ete sauvegarder");
		} catch (ServiceException | DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		LOG.debug("l'equipe medicale est en place");
	}
}
