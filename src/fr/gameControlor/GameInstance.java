package fr.gameControlor;

import fr.entities.User;
import fr.gameEntities.EntitiesControlor;
import fr.interfaces.IGameEntity;
import fr.interfaces.IGameInstance;

public class GameInstance implements IGameInstance{

	
	private String key; 
	private User user = null;
	private EntitiesControlor entityControl = null;
	
	public GameInstance(User user){
		if(user != null){
			this.user = user;
		}
		this.entityControl = new EntitiesControlor();
	}
	
	private void start(){
		
	}
}
