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
import fr.splExceptions.EntityException;
import fr.splExceptions.ServiceException;
import fr.tools.LoginTools;
import fr.tools.RestTools;

/**
 * Servlet implementation class BackupConstruction
 */
@WebServlet(urlPatterns={"/backupconstructions","/backupconstructions/*"})
public class BackupConstructionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BackupConstructionSrv() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<IEntity> bcs = null;
		BackupConstruction bc = null;
		Backup backup = null;
		// test si id	 ou all
		RestTools.getId(request);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			backup = LoginTools.checkBackup(request);
		} catch (BackupException e) {
			// TODO Auto-generated catch block
			RestTools.getReturn(e.getMessage(), true);
		}

		if(backup != null){
			if(request.getAttribute("id") != null){
				try {
					bc=(BackupConstruction) new EntitiesController(backup.getId()).getGameEntity(BackupConstruction.class.getName()+request.getAttribute("id"));
				} catch (EntityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}else{
				//fr.entities.BackupConstruction bc = new fr.entities.BackupConstruction(null, 0, 0, null, null); 
				try {
					bcs=new EntitiesController().getGameEntitiesFromDao(backup.getId());
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.append(new Gson().toJson(bcs));

			}
			
		}
		out.close();
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
