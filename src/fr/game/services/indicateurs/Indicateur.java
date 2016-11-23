package fr.game.services.indicateurs;

import fr.Dao.DAO;
import fr.game.services.gameControlor.AbstractGameEntity;
import fr.interfaces.IEntity;
import fr.interfaces.IGameEntity;

public abstract class Indicateur<T extends IEntity,D extends DAO<T, Integer>> extends AbstractGameEntity<T, D>{

	public Indicateur(T entity, D entityDao) {
		super(entity, entityDao);
	}
	
	
	
}