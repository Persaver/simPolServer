package fr.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.Dao.UserDAO;
import fr.entities.Backup;
import fr.entities.User;

public final class LoginTools {
	
	// verifie le login
	public static final User checkLogin(HttpServletRequest HttpReq){
		HttpSession session = HttpReq.getSession(true);
		String login = null;
		String password = null;
		User user = null;
		
		
		if(session.getAttribute("user") == null){
			if(HttpReq.getParameter("login") != null && HttpReq.getParameter("password") != null){
				login = HttpReq.getParameter("login");
				password = HttpReq.getParameter("password");
				user = new UserDAO().checklogin(login, password);
				session.setAttribute("user", user);
				HttpReq.setAttribute("user", user);
				System.out.println(user);

			}

			//hack debub
			else{
				user = new UserDAO().checklogin("saver","saver");
			}
		}else{
			user = (User) session.getAttribute("user");
		}
		System.out.println(user);

		return user;
	}
	
	public static final Backup checkBackup(HttpServletRequest HttpReq){
		HttpSession session = HttpReq.getSession(true);
		Backup backup = null;

		if(session.getAttribute("user") != null){
			if(session.getAttribute("backup") != null){
				backup = (Backup) session.getAttribute("backup");
			}
		}
		
		return backup;
	}
}
