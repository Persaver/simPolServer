package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.Dao.ConstructionDAO;
import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.entities.Construction;
import fr.game.services.gameControllers.EntitiesController;
import fr.interfaces.IEntity;
import fr.splExceptions.BackupException;
import fr.splExceptions.DAOException;
import fr.splExceptions.ServiceException;
import fr.tools.LoginTools;
import fr.tools.RestTools;

/**
 * Servlet implementation class ConstructionSrv
 */
@WebServlet(urlPatterns={ "/constructions", "/constructions/*" })
public class ConstructionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConstructionSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug(" COnstructions doGet ");
		List<Construction> constructions = null;
		Construction construction = null;
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
					construction= new ConstructionDAO().get( (Integer) request.getAttribute("id"));
				} catch (NumberFormatException | DAOException e) {
					out.append(RestTools.getReturn(e.getMessage(), true));
				}
				LOG.debug(" ConstructionSrv doGet /id {} return {}",request.getAttribute("id"),construction != null ? construction.getId() : "null");

				out.append(RestTools.getReturn(construction, construction == null));

			}else{
				//fr.entities.BackupConstruction bc = new fr.entities.BackupConstruction(null, 0, 0, null, null);
				try {
					constructions=new ConstructionDAO().getAll();
					LOG.debug(" COnstructions	 doGet backup {} return size {}",backup.getId(),constructions != null ? constructions.size() : "null");
					out.append(RestTools.getReturn(constructions, constructions == null));
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.append(RestTools.getReturn(e.getMessage(),true));
				}


			}

		}else{
			out.append(RestTools.getReturn(new BackupException("Pas de pas correspondant"), true));
		}
		out.close();
	}

}
