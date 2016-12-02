package fr.game.services.gameControllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.Dao.DAO;
import fr.interfaces.IEntity;
import fr.interfaces.IGameEntity;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;

//recois le type de l'entites a stocker

public abstract class AbstractGameEntity< T extends IEntity ,D extends DAO<T,Integer> > implements IGameEntity{

	protected T entity = null;
	protected D entityDao = null;
	protected boolean isModify = false;

	private static final Logger LOG = LogManager.getLogger();

	public AbstractGameEntity(T entity,D entityDao){
		this.entity = entity;
		this.entityDao = entityDao;
		AbstractGameEntity.LOG.debug(" Creation {} entity {} ",this.getClass().getName(), this.getEntity() != null ? this.getEntity().getClass().getName() : "null");
	}

	@Override
	public void setEntity(IEntity entity) throws ServiceException{
		try {
			this.entity =  this.entityDao.save((T)entity);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public T getEntity() {
		return this.entity;
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
	public void saveEntity() throws ServiceException{
		AbstractGameEntity.LOG.debug(" Sauvergarde {} entity {} ",this.getClass().getName(),this.getEntity().getClass());
		try {
			this.entityDao.save(this.entity);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}

}
