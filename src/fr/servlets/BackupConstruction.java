package fr.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tools.RestTools;

/**
 * Servlet implementation class BackupConstruction
 */
@WebServlet(urlPatterns={"/backupconstructions","/backupconstructions/*"})
public class BackupConstruction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackupConstruction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<fr.entities.BackupConstruction> bcs = null;
		fr.entities.BackupConstruction bc = null;
		// test si id	 ou all
		RestTools.getId(request);
		if(request.getAttribute("id") != null){
			response.setContentType("application/json;charset=UTF-8");
			
			response.getWriter().append("id" + request.getAttribute("id") +"Served at: &0àà@").append(request.getContextPath()).close();
			

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

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
