package fr.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.Dao.BackupDAO;
import fr.Dao.UserDAO;
import fr.entities.Backup;
import fr.entities.User;
import fr.game.services.backup.BackupService;
import fr.splExceptions.BackupException;
import fr.splExceptions.DAOException;
import fr.splExceptions.LoginException;
import fr.splExceptions.ServiceException;

public final class LoginTools {
	private static final Logger LOG = LogManager.getLogger();

	// verifie le login
	public static final User checkLogin(HttpServletRequest HttpReq) throws LoginException{
		HttpSession session = HttpReq.getSession(true);
		String login = null;
		String token = null;
		User user = null;

		// teste deans la session si deja enregistré
		if(session.getAttribute("user") == null){
			if((HttpReq.getParameter("login") != null) && (HttpReq.getParameter("token") != null)){
				login = HttpReq.getParameter("login");
				token = HttpReq.getParameter("token");
				try {
					user = new UserDAO().checklogin(login, token);
				} catch (DAOException e) {
					LoginTools.LOG.debug(" checkLogin by parameter  error ",e.getMessage());
					throw new LoginException(e.getMessage());
				}
				session.setAttribute("user", user);
				HttpReq.setAttribute("user", user);
				LoginTools.LOG.debug(" checkLogin by parameter  user = {} ",user != null ? user.getId() : null);

			}

			//hack debub
			else{
				throw new LoginException("recuperation user impossible");
			}
		}else{
			try{
				user = (User) session.getAttribute("user");
			}catch(Exception e){
				throw new LoginException(e.getMessage());
			}
			LoginTools.LOG.debug(" checkLogin by session  user = {} ",user != null ? user.getId() : null);

		}
		LoginTools.LOG.debug(" checkLogin by session  user = {} ",user != null ? user.getId() : null);
		//System.out.println(user);

		return user;
	}

	public static final Backup checkBackup(HttpServletRequest HttpReq) throws BackupException{
		HttpSession session = HttpReq.getSession(true);
		Backup backup = null;
		LoginTools.LOG.debug(" checkBackup ",backup != null ? backup.getId() : null);


		User user;
		// on verifie le user
		try {
			user = LoginTools.checkLogin(HttpReq);

		} catch (LoginException e) {
			throw new BackupException(e.getMessage());
		}

		// si user on test le backup
		if(user != null){
			if(session.getAttribute("backup") != null){
				try{
					backup = (Backup) session.getAttribute("backup");
					LoginTools.LOG.debug(" checkBackup by session  backup = {} ",backup != null ? backup.getId() : null);

				}catch(Exception e){
					throw new BackupException(e.getMessage());
				}
			}
			else if(HttpReq.getParameter("backup") != null ){
				Integer idbackup = Integer.parseInt(HttpReq.getParameter("backup"));
				LoginTools.LOG.debug(" checkBackup by params  idBackup = {} ",idbackup != null ? idbackup : "null");

				if(idbackup != null){
					try {
						BackupService backupService = new BackupService();
						backup = backupService.getBackupByUserIdBackup(user, idbackup);
						LoginTools.LOG.debug(" checkBackup by params  backup = {} ",backup != null ? backup.getId() : null);

					} catch (ServiceException e) {
						throw new BackupException(e.getMessage());
					}
				}
			}
		}
		// on a recuperé un backup on enregistre
		if(backup != null){
			session.setAttribute("backup", backup);
			HttpReq.setAttribute("backup", backup);
		}
		// sinon exception
		else{
			throw new BackupException("pas de backup");
		}

		return backup;
	}
}
