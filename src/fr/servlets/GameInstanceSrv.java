package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.entities.Backup;
import fr.entities.User;
import fr.game.services.gameControllers.GameInstance;
import fr.splExceptions.BackupException;
import fr.splExceptions.ServiceException;
import fr.tools.LoginTools;
import fr.tools.RestTools;

/**
 * Servlet implementation class GameInstance
 */
@WebServlet(urlPatterns={"/gameinstances","/gameinstances/*"})
public class GameInstanceSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameInstanceSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		GameInstanceSrv.LOG.debug(" GameInstanceSrv doGet ");
		
		GameInstance instance = null;
		User user = null;
		Backup backup = null;
		// test si id	 ou all
		RestTools.getId(request);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			backup = LoginTools.checkBackup(request);

			if(backup == null){
				throw new ServiceException("Pas de backup correspondant");
			}
			user = (User) session.getAttribute("user");
			// on recup l'instance
			instance = new GameInstance(user, backup);
			
			// on lance le start
			
			instance.start();
//			Integer[][] tab = {{2,1,1,2,1,3,5,8,4,9,7,4},{2,1,1,2,1,3,5,8,4,9,7,4},{2,1,1,2,1,3,5,8,4,9,7,4},{2,1,1,2,1,3,5,8,4,9,7,4},{2,1,1,2,1,3,5,8,4,9,7,4},{2,1,1,2,1,3,5,8,4,9,7,4},{2,1,1,2,1,3,5,8,4,9,7,4},{2,1,1,2,1,3,5,8,4,9,7,4}};
//			out.append(new Gson().toJson(tab));
			
			

		} catch (BackupException | ServiceException e) {
			// TODO Auto-generated catch block
			RestTools.getReturn(e.getMessage(), true);
		}

		//out.append(RestTools.getReturn(bc, bc == null));
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
