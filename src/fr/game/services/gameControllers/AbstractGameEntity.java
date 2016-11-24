package fr.game.services.gameControllers;

import fr.Dao.DAO;
import fr.interfaces.IEntity;
import fr.interfaces.IGameEntity;

//recois le type de l'entites a stocker

public abstract class AbstractGameEntity< T extends IEntity ,D extends DAO<T,Integer> > implements IGameEntity{

	protected T entity = null;
	protected D entityDao = null;
	protected boolean isModify = false;

	public AbstractGameEntity(T entity,D entityDao){
		this.entity = entity;
		this.entityDao = entityDao;
	}
	
	@Override
	public void setEntity(IEntity entity) {
		this.entity =  (T) entity;
	}

	@Override
	public IEntity getEntity() {
		return (IEntity) this.entity;
	}

	@Override
	public boolean isModify() {
		return this.isModify;
	}

	@Override
	public void setModify() {
		this.isModify = true;
	}

	@Override
	public void saveEntity(){
		this.entityDao.save(this.entity);
	}


}
