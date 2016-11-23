package fr.game.services.gameControlor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.interfaces.IGameEntity;
import fr.splExceptions.EntityException;

// il s'occupe des Game entities et de leur sauvegarde

public class EntitiesControlor {

	private static int countEntities = 0;

	private Map<String,IGameEntity> gameEntities = new HashMap<String,IGameEntity>();

	private BackupDAO backupDAO =null;
	private BackupConstructionDAO backupConstructionDAO= null;

	public EntitiesControlor(BackupDAO backupDAO){
		super();
		this.backupDAO = backupDAO;
	}

	public Integer addGameEntity(String key,IGameEntity entity){
		EntitiesControlor.countEntities++;
		this.gameEntities.put(key, entity);
		return EntitiesControlor.countEntities;
	}

	public void removeGameEntities(String key) throws EntityException{
		try{
			this.gameEntities.remove(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}

	}
	public IGameEntity getGameEntity(String key) throws EntityException{
		IGameEntity entity = null;

		try{
			entity = this.gameEntities.get(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}

		return entity;

	}

	public Map<String,IGameEntity> getGameEntities(){
		return this.gameEntities;
	}

	// lance la sauvergarde de toutes les entitées
	public void saveGameEntities(){

		IGameEntity entity;
		Iterator itKey = this.getGameEntities().keySet().iterator();
		Iterator itValue = this.getGameEntities().values().iterator();

		while(itKey.hasNext()){
			entity = (IGameEntity) itValue.next();
			entity.saveEntity();
			itKey.next();
		}
	}

	// lance la sauvergarde de toutes les entitées
	public void getGameEntitiesFromDao(){

	}


}
