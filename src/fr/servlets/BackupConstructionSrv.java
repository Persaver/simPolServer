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
import fr.entities.BackupConstruction;
import fr.game.services.gameControllers.EntitiesController;
import fr.interfaces.IEntity;
import fr.splExceptions.BackupException;
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
	 *
	 * renvois le backupConstuction si id sinon liste des backupConstuctions du backup
	 * /backupconstructions","/backupconstructions/{id}
	 *
	 */
	@Override
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
					bc=(BackupConstruction) new EntitiesController().getGameEntity(Integer.parseInt(request.getAttribute("id").toString()));
				} catch (NumberFormatException | ServiceException e) {
					// TODO Auto-generated catch block
					out.append(RestTools.getReturn(e.getMessage(), true));
				}

				out.append(RestTools.getReturn(bc, bc == null));

			}else{
				//fr.entities.BackupConstruction bc = new fr.entities.BackupConstruction(null, 0, 0, null, null);
				try {
					bcs=new EntitiesController().getGameEntitiesFromDao(backup.getId());
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.append(RestTools.getReturn(bcs, bcs == null));

			}

		}else{
			out.append(RestTools.getReturn(new BackupException("Pas de pas correspondant"), true));
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Mettre a jour une construction
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BackupConstruction bc = null;
		Backup backup = null;
		// test si id	 ou all
		RestTools.getId(request);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			backup = LoginTools.checkBackup(request);

			if(backup == null){
				out.append(RestTools.getReturn(new BackupException("Pas de pas correspondant"), true));
			}
			else{
				if(request.getAttribute("backupConstruction") != null){
					bc = new Gson().fromJson( (String) request.getAttribute("backupConstruction"), BackupConstruction.class);
				}
				bc = (BackupConstruction) new EntitiesController().saveGameEntity(bc);

			}
		} catch (BackupException | ServiceException e) {
			// TODO Auto-generated catch block
			RestTools.getReturn(e.getMessage(), true);
		}

		out.append(RestTools.getReturn(bc, bc == null));
		out.close();


	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * 
	 * creer une construction
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BackupConstruction bc = null;
		Backup backup = null;
		// test si id	 ou all
		RestTools.getId(request);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			backup = LoginTools.checkBackup(request);

			if(backup == null){
				out.append(RestTools.getReturn(new BackupException("Pas de pas correspondant"), true));
			}
			else{
				if(request.getAttribute("backupConstruction") != null){
					
					bc = new Gson().fromJson( (String) request.getAttribute("backupConstruction"), BackupConstruction.class);
				}
				bc = (BackupConstruction) new EntitiesController().addGameEntity(bc);

			}
		} catch (BackupException | ServiceException e) {
			// TODO Auto-generated catch block
			RestTools.getReturn(e.getMessage(), true);
		}

		out.append(RestTools.getReturn(bc, bc == null));
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
