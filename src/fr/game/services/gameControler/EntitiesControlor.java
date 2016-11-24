package fr.game.services.gameControler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.interfaces.IGameEntity;
import fr.splExceptions.EntityException;

// il s'occupe des Game entities et de leur sauvegarde

public class EntitiesControlor {

	private static int countEntities = 0;

	private Map<String,IGameEntity> gameEntities = new HashMap<String,IGameEntity>();

	private BackupDAO backupDAO =null;
	private BackupConstructionDAO backupConstructionDAO= null;
	private Integer idBackup = null;

	public EntitiesControlor(BackupDAO backupDAO,BackupConstructionDAO backupConstructionDAO, Integer idBackup){
		super();
		this.backupDAO = backupDAO;
		this.backupConstructionDAO = backupConstructionDAO;
		this.idBackup = idBackup;
	}

	// ajoute a gameEntities
	public Integer addGameEntity(String key,IGameEntity entity){
		EntitiesControlor.countEntities++;
		this.gameEntities.put(key, entity);
		return EntitiesControlor.countEntities;
	}

	// supprime de gameEntities
	public void removeGameEntities(String key) throws EntityException{
		try{
			this.gameEntities.remove(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}

	}

	// recupere un entite par sa clef
	public IGameEntity getGameEntity(String key) throws EntityException{
		IGameEntity entity = null;

		try{
			entity = this.gameEntities.get(key);
		}catch(NullPointerException e){
			throw new EntityException("Pas d'entité trouvé");
		}

		return entity;

	}

	public Integer getIdBackup() {
		return this.idBackup;
	}

	public void setIdBackup(Integer idBackup) {
		this.idBackup = idBackup;
	}

	// recupere toute les entites
	public Map<String,IGameEntity> getGameEntities(){
		return this.gameEntities;
	}

	// lance la sauvergarde de toutes les entitées
	public void saveGameEntities(){

		IGameEntity entity;
		Iterator<String> itKey = this.getGameEntities().keySet().iterator();
		Iterator<IGameEntity> itValue = this.getGameEntities().values().iterator();

		while(itKey.next() != null){
			entity = itValue.next();
			entity.saveEntity();
		}
	}

	// lance la recuperation de toutes les entitées
	public void getGameEntitiesFromDao(){
		List<BackupConstruction> gameEntities = null;
		Backup backup = null;
		backup = this.backupDAO.get(this.idBackup);
		int ix = 0;

		// si on recupere corectement le backup
		if(backup != null){
			//on essaye de recuperer les constructions
			gameEntities = this.backupConstructionDAO.getAllByBackUp(backup);
		}
		// si il y en a
		if(gameEntities != null){
			// on les stocke dans la map
			for(BackupConstruction backupConstruction : gameEntities){
				this.gameEntities.put(backupConstruction.getClass().getName() + ix ,(IGameEntity) backupConstruction );
			}
		}
	}


}