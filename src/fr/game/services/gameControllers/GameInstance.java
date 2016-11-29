package fr.game.services.gameControllers;

import java.util.List;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.entities.User;
import fr.interfaces.IEntity;
import fr.interfaces.IGameEntity;
import fr.interfaces.IGameInstance;
import fr.splExceptions.ServiceException;

public class GameInstance implements IGameInstance{

	private String key;
	private User user = null;
	private EntitiesController entityController = null;
	private Backup backup = null;
	private BackupDAO backupDAO = null;
	private BackupConstructionDAO backupConstructionDAO =null;


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
	public List<IEntity> getEntities() throws ServiceException {
		return this.entityController.getGameEntitiesFromDao(backup.getId());
	}


	private void start(){
		
	}
}
