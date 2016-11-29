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
import fr.tools.RestTools;

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
		out.append(RestTools.getReturn( user, user == null));
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		User user = null;
		String login,password;
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(request.getAttribute("id") != null){

		}
		else{
			if((request.getParameter("login") != null) && (request.getParameter("password") != null)){
				login = request.getParameter("login");
				password = request.getParameter("password");
				user = new UserDAO().checklogin(login, password);
				//System.out.println(user);

			}
		}
		if(user != null){
			session.setAttribute("user",user);
			request.setAttribute("user", user);
			out.append(new Gson().toJson(user));
		}
		else{
			out.append("pas backup");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
