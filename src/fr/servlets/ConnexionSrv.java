package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.entities.User;
import fr.gameControlor.GameInstance;
import fr.gameControlor.GameInstanceControlor;
import fr.interfaces.IGameInstance;
import fr.splExceptions.SplException;
import fr.tools.RestTools;


@WebServlet(urlPatterns={"/connexions","/connexions/*"})
public class ConnexionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConnexionSrv() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		RestTools.getId(request);
		IGameInstance gameInstance = null;

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getAttribute("id") != null){
			out.append("id" + request.getAttribute("id") +"Served at: &0àà@").append(request.getContextPath());
			// si les filtres retourne un user
			if(request.getAttribute("user") != null){
				user = (User) request.getAttribute("user");
				// on teste si deja une instance en court
				if(GameInstanceControlor.hasGameInstance(user)){
					try{
					GameInstanceControlor.removeGameInstance(user);
					out.append(" a une'instance");
					}catch(SplException e){
						// on fait quoi?
					}
				}
				
				// on cree est on enregistre l'instance
				gameInstance = GameInstanceControlor.createGameInstance(user);
				out.append(" instance crée");

				
				out.append(" user "+ user.getPseudo() );
			}
			out.close();

		}else{
			//fr.entities.BackupConstruction bc = new fr.entities.BackupConstruction(null, 0, 0, null, null); 
			response.getWriter().append("id"  +"Served at: ").append(request.getContextPath()).close();

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
