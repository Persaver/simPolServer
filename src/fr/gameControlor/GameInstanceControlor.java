package fr.gameControlor;

import java.util.HashMap;
import java.util.Map;

import fr.entities.Backup;
import fr.entities.User;
import fr.interfaces.IGameInstance;
import fr.splExceptions.EntityException;
import fr.splExceptions.GameInstanceException;

public class GameInstanceControlor {
	static Integer countGameInstance = 0;


	// la clef est l'utilisateur cad utilisateur 1 partie à la fois 
	private static Map<User,IGameInstance> gameInstances = new HashMap<User,IGameInstance>();

	public static void addGameInstance(User key,IGameInstance entity){
		GameInstanceControlor.countGameInstance++;
		GameInstanceControlor.gameInstances.put(key, entity);
	}

	public static void removeGameInstance(User key) throws GameInstanceException{
		try{
			GameInstanceControlor.gameInstances.remove(key);
		}catch(NullPointerException e){
			throw new GameInstanceException("Pas d'entité trouvé");
		}

	}
	public static IGameInstance getGameInstance(User key) throws GameInstanceException{
		IGameInstance entity = null;

		try{
			entity = (IGameInstance) GameInstanceControlor.gameInstances.get(key);
		}catch(NullPointerException e){
			throw new GameInstanceException("Pas d'entité trouvé");
		}
		return entity;
	}

	public static boolean hasGameInstance(User key){
		IGameInstance entity = null;

		try{
			entity = (IGameInstance) GameInstanceControlor.gameInstances.get(key);
		}catch(NullPointerException e){
			return false;
		}

		return entity == null ? false : true;
	}
	public static IGameInstance createGameInstance(User user,Backup backup){
		IGameInstance gameInstance = null;
		// on test si il existe deja une partie
		
		if(GameInstanceControlor.hasGameInstance(user)){
			gameInstance = new GameInstance(user,backup);
			GameInstanceControlor.addGameInstance(user, gameInstance);
		}
		
		return gameInstance;
	}
}
