package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import fr.Dao.UserDAO;
import fr.entities.User;
import fr.game.services.user.UserService;
import fr.splExceptions.LoginException;
import fr.splExceptions.ServiceException;
import fr.tools.RestTools;
import javafx.scene.control.TreeTableRow;

/**
 * Servlet implementation class UserSrv
 */
@WebServlet("/users")
public class UserSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		RestTools.getId(request);
		User user = null;
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		user = (User) session.getAttribute("user");
		if(request.getAttribute("id") != null){
			user = (User) session.getAttribute("user");


		}
		// si user == null return {error:{msg} }
		out.append(RestTools.getReturn( user, user == null));
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Verifie le login && token ;)
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		User user = null;
		String login,token;
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// on verifie si on le recupere
		if((request.getParameter("login") != null) && (request.getParameter("token") != null)){
			login = request.getParameter("login");
			token = request.getParameter("token");
			user = new UserDAO().checklogin(login, token);
			//System.out.println(user);

		}

		if(user != null){
			session.setAttribute("user",user);
			request.setAttribute("user", user);
			//out.append(new Gson().toJson(user));
		}
		out.append(RestTools.getReturn( user, user == null));
		out.close();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * Ajouter un utilisateur 
	 * requiere login and token
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		User user = null;
		String login,password;
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		login=(String) request.getAttribute("login");
		password=(String) request.getAttribute("password");

		// verifie les param dans un try catch pour recuperer les erreurs les erreurs
		try{
			if(login == null || login.equals("")){
				throw new LoginException("login obligatoire");
			}
			if(password == null || password.equals("")){
				throw new LoginException("password obligatoire");
			}
			// insere en bdd
			user = new UserService().createUser(login, password);
			out.append(RestTools.getReturn(user, user == null));
		}catch(LoginException | ServiceException e){
			out.append(RestTools.getReturn(e.getMessage(), true));
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
