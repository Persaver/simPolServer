package fr.interfaces;

import java.util.List;

import fr.splExceptions.ServiceException;

public interface IGameInstance {

public List<IEntity> getEntities() throws ServiceException;
}
