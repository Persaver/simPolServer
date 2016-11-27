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

import fr.Dao.BackupConstructionDAO;
import fr.Dao.BackupDAO;
import fr.entities.Backup;
import fr.entities.User;
import fr.game.services.gameControllers.EntitiesController;
import fr.game.services.gameControllers.GameInstance;
import fr.game.services.gameControllers.GameInstanceControlor;
import fr.interfaces.IGameInstance;
import fr.splExceptions.BackupException;
import fr.splExceptions.GameInstanceException;
import fr.splExceptions.SplException;
import fr.tools.LoginTools;
import fr.tools.RestTools;


@WebServlet(urlPatterns={"/connexions","/connexions/*"})
public class ConnexionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConnexionSrv() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		User user = null;
		Backup backup = null;
		IGameInstance gameInstance = null;
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		RestTools.getId(request);
		try {
			LoginTools.checkBackup(request);
			user = (User) session.getAttribute("user");
			backup = (Backup) session.getAttribute("backup");
			
		} catch (BackupException e) {
			out.append("\"error\":"+e.getMessage());
		}
		
		gameInstance = new GameInstance(user, new BackupDAO(),new BackupConstructionDAO(), backup.getId());
		
		out.append(new Gson().toJson(gameInstance.getEntities()));

		out.close();

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
