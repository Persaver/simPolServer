package fr.game.services.gameControlor;

import fr.Dao.BackupDAO;
import fr.entities.Backup;
import fr.entities.User;
import fr.interfaces.IGameInstance;

public class GameInstance implements IGameInstance{

	private String key;
	private User user = null;
	private EntitiesControlor entityControl = null;
	private Backup backup = null;
	private BackupDAO backupDAO = null;

	public GameInstance(User user, BackupDAO backupDAO){
		if(user != null){
			this.user = user;
		}
		this.entityControl = new EntitiesControlor();
		this.backup = this.backup;
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

	public EntitiesControlor getEntityControl() {
		return this.entityControl;
	}

	public void setEntityControl(EntitiesControlor entityControl) {
		this.entityControl = entityControl;
	}

	public Backup getBackup() {
		return this.backup;
	}

	public void setBackup(Backup backup) {
		this.backup = backup;
	}



	private void start(){

	}
}
