package fr.game.services.user;

import fr.Dao.UserDAO;
import fr.entities.AbstractEntity;
import fr.entities.User;
import fr.game.services.gameControllers.AbstractGameEntity;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;

public class UserService extends AbstractGameEntity<User,UserDAO>{

	public UserService() {
		this(null,new UserDAO());
	}
	public UserService(User entity, UserDAO entityDao) {
		super(entity, entityDao);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User createUser(String login, String password) throws ServiceException{
		User user = null;
		
		try{
			// on cree un utilisateur sans id
			user = new User(null,login,password);
			user = entityDao.save(user);
			
		}catch(DAOException e){
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

}
