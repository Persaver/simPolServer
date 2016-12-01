package fr.interfaces;

import fr.splExceptions.ServiceException;

public interface IGameEntity {

	public void setEntity(IEntity entity) throws ServiceException ;
	public void saveEntity() throws ServiceException;
	public IEntity getEntity();
	public boolean isModify();
	public void setModify();
	public String getName();

}
