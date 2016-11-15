package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.entities.User;
import fr.tools.RestTools;

/**
 * Servlet implementation class ConnexionSrv
 */
@WebServlet(urlPatterns={"/connexions","/connexions/*"})
public class ConnexionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		RestTools.getId(request);

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getAttribute("id") != null){
			out.append("id" + request.getAttribute("id") +"Served at: &0àà@").append(request.getContextPath());
			if(request.getAttribute("user") != null){
				user = (User) request.getAttribute("user");
				//out.append("user"+ )
			}

		}else{
			//fr.entities.BackupConstruction bc = new fr.entities.BackupConstruction(null, 0, 0, null, null); 
			response.getWriter().append("id"  +"Served at: ").append(request.getContextPath()).close();

		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
