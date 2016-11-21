package fr.gameControlor;

import fr.entities.Backup;
import fr.entities.User;
import fr.gameEntities.EntitiesControlor;
import fr.interfaces.IGameEntity;
import fr.interfaces.IGameInstance;

public class GameInstance implements IGameInstance{

	
	private String key; 
	private User user = null;
	private EntitiesControlor entityControl = null;
	private Backup backup = null;
	
	public GameInstance(User user, Backup backup){
		if(user != null){
			this.user = user;
		}
		this.entityControl = new EntitiesControlor();
		this.backup = backup;
	}
	
	private void start(){
		
	}
}
