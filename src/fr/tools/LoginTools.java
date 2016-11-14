package fr.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.Dao.UserDAO;
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
			}
			//hack debub
			else{
				return  new User("saver","AAAA");
			}
		}else{
			user = (User) session.getAttribute("user");
		}
		return user;
	}
}
