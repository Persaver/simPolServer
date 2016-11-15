package fr.gameEntities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import fr.interfaces.IEntity;
import fr.interfaces.IGameEntity;
import fr.splExceptions.EntityException;

public class EntitiesControlor {

	private static int countEntities = 0; 
	
	private Map<String,IGameEntity> gameEntities = new HashMap<String,IGameEntity>();
	
	public EntitiesControlor(){
		super();
	}
	
	public Integer addGameEntity(String key,IGameEntity entity){
		EntitiesControlor.countEntities++;
		gameEntities.put(key, entity);
		return EntitiesControlor.countEntities;
	}
	
	public void removeGameEntities(String key) throws EntityException{
		try{
			this.gameEntities.remove(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}
		
	}
	public IEntity getGameEntity(String key) throws EntityException{
		IEntity entity = null;
		
		try{
			entity = (IEntity) this.gameEntities.get(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}
		
		return entity;

	}
		
}
