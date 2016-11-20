package fr.gameEntities;

import fr.interfaces.IEntity;
import fr.interfaces.IGameEntity;

//recois le type de l'entites a stocker

public abstract class AbstractGameEntity<T> implements IGameEntity{
	
	protected T entity = null;
	protected boolean isModify = false;
	
	public void setEntity(IEntity entity) {
		this.entity = (T) entity;
	}
	
	public IEntity getEntity() {
		return (IEntity) entity;
	}
	
	public boolean isModify() {
		return this.isModify;
	}
	
	public void setModify() {
		this.isModify = true;
	}
}
