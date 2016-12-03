package fr.game.services.gameControllers;

import java.util.HashMap;
import java.util.Map;

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.Dao.UserDAO;
import fr.entities.User;
import fr.interfaces.IGameInstance;
import fr.splExceptions.GameInstanceException;

public class GameInstanceControlor {
	static Integer countGameInstance = 0;

	//respository
	private static BackupDAO backupDAO;
	private static UserDAO userDAO;
	private static BackupConstructionDAO backupConstructionDAO;
	
	//service
	private static GameInstanceControlor gameInstanceControlor = null;
	
	// la clef est l'utilisateur cad utilisateur 1 partie à la fois
	private static Map<User,IGameInstance> gameInstances = new HashMap<User,IGameInstance>();

	//stocke dans gameInstances
	public static void addGameInstance(User key,IGameInstance entity){
		GameInstanceControlor.countGameInstance++;
		GameInstanceControlor.gameInstances.put(key, entity);
	}
	// supprime de gameInstances
	public static void removeGameInstance(User key) throws GameInstanceException{
		try{
			GameInstanceControlor.gameInstances.remove(key);
		}catch(NullPointerException e){
			throw new GameInstanceException("Pas d'entité trouvé");
		}

	}
	
	// recupere l'instance courante de l'utitlisateur
	public static IGameInstance getGameInstance(User key) throws GameInstanceException{
		IGameInstance entity = null;

		try{
			entity = GameInstanceControlor.gameInstances.get(key);
		}catch(NullPointerException e){
			throw new GameInstanceException("Pas d'entité trouvé");
		}
		return entity;
	}
	
	// test si l'utilisateur à une partie
	public static boolean hasGameInstance(User key){
		IGameInstance entity = null;

		try{
			entity = GameInstanceControlor.gameInstances.get(key);
		}catch(NullPointerException e){
			return false;
		}

		return entity == null ? false : true;
	}
	
	// creation d'une instance si deja un ela remplace
	
//	public static IGameInstance createGameInstance(User user,Integer idBackup){
//		IGameInstance gameInstance = null;
//		// on test si il existe deja une partie
//
//		if(GameInstanceControlor.hasGameInstance(user)){
//			// log
//		}
//		else{
//			gameInstance = new GameInstance(user,GameInstanceControlor.getBackupDAO(),GameInstanceControlor.getBackupConstructionDAO(),idBackup);
//			GameInstanceControlor.addGameInstance(user, gameInstance);
//		}
//		
//		return gameInstance;
//	}

	public static Integer getCountGameInstance() {
		return countGameInstance;
	}

	public static void setCountGameInstance(Integer countGameInstance) {
		GameInstanceControlor.countGameInstance = countGameInstance;
	}

	public static BackupDAO getBackupDAO() {
		return backupDAO;
	}

	public static void setBackupDAO(BackupDAO backupDAO) {
		GameInstanceControlor.backupDAO = backupDAO;
	}

	public static UserDAO getUserDAO() {
		return userDAO;
	}

	public static void setUserDAO(UserDAO userDAO) {
		GameInstanceControlor.userDAO = userDAO;
	}


	public static BackupConstructionDAO getBackupConstructionDAO() {
		return backupConstructionDAO;
	}
	public static void setBackupConstructionDAO(BackupConstructionDAO backupConstructionDAO) {
		GameInstanceControlor.backupConstructionDAO = backupConstructionDAO;
	}
	public static GameInstanceControlor getGameInstanceControlor() {
		return gameInstanceControlor;
	}

	public static void setGameInstanceControlor(GameInstanceControlor gameInstanceControlor) {
		GameInstanceControlor.gameInstanceControlor = gameInstanceControlor;
	}

	public static Map<User, IGameInstance> getGameInstances() {
		return gameInstances;
	}

	public static void setGameInstances(Map<User, IGameInstance> gameInstances) {
		GameInstanceControlor.gameInstances = gameInstances;
	}
	
}
