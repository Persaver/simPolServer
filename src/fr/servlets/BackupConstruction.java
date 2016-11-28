package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.entities.Backup;
import fr.game.services.gameControllers.EntitiesController;
import fr.interfaces.IEntity;
import fr.splExceptions.BackupException;
import fr.tools.LoginTools;
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
		List<IEntity> bcs = null;
		fr.entities.BackupConstruction bc = null;
		Backup backup = null;
		// test si id	 ou all
		RestTools.getId(request);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			backup = LoginTools.checkBackup(request);
		} catch (BackupException e) {
			// TODO Auto-generated catch block
			out.append("\"error\":"+e.getMessage());
		}
		
		if(backup != null){
		if(request.getAttribute("id") != null){
			
			out.append("id" + request.getAttribute("id") +"Served at: &0àà@").append(request.getContextPath());
			
		}else{
			//fr.entities.BackupConstruction bc = new fr.entities.BackupConstruction(null, 0, 0, null, null); 
			bcs=new EntitiesController(backup.getId()).getGameEntitiesFromDao(backup.getId());
			out.append(new Gson().toJson(bcs));

		}
		out.close();
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
