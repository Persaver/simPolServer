package fr.game.services.backup;

import java.util.List;

import fr.Dao.BackupDAO;
import fr.Dao.UserDAO;
import fr.entities.Backup;
import fr.entities.User;
import fr.game.services.gameControllers.AbstractGameEntity;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;

public class BackupService extends AbstractGameEntity<Backup, BackupDAO>{

	private UserDAO userDAO;

	public BackupService() {
		this(new Backup(), new BackupDAO());
	}

	public BackupService(Backup entity, BackupDAO entityDao) {
		super(entity, entityDao);
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Backup> getBackupsByUser(User user) throws ServiceException{
		List<Backup> backups = null;
		try {
			backups = new BackupDAO().getByUser(user);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		return backups;
	}

	public Backup getBackupByUserIdBackup(User user,Integer idBackup) throws ServiceException{
		if((user == null) || (idBackup == null)){
			throw new ServiceException("pas de user u backup");
		}
		Backup backup = null;
		try {
			backup = new BackupDAO().get(idBackup);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		//		System.out.println("user"+user);
		//		System.out.println("idbackup"+idBackup);
		//		System.out.println("backup"+backup);
		//		System.out.println("backup.getUser().getId()"+backup.getUser().getId());
		//		System.out.println("user.getId()"+user.getId());



		if(!(backup.getUser().getId().equals(user.getId()))){
			throw new ServiceException("pas de backup correspondant"+ user.toString() + idBackup.toString());
		}
		return backup;
	}


}
