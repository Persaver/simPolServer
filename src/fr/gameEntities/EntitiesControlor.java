package fr.gameEntities;

import java.util.List;
import java.util.Vector;

import fr.interfaces.IGameEntity;

public class EntitiesControlor {

	private static int countEntities = 0; 
	
	private List<IGameEntity> gameEntities = new Vector<IGameEntity>();
	
	public Integer addGameEntity(IGameEntity entity){
		EntitiesControlor.countEntities++;
		gameEntities.add(entity);
		return EntitiesControlor.countEntities;
	}
	
	public void removeGameEntities(Integer index){
		if(index< this.gameEntities.size()){
			this.gameEntities.remove(index);
		}
	}
		
}
