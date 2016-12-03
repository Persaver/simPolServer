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

import fr.entities.Backup;
import fr.entities.User;
import fr.game.services.backup.BackupService;
import fr.interfaces.IGameInstance;
import fr.splExceptions.BackupException;
import fr.splExceptions.ServiceException;
import fr.tools.LoginTools;
import fr.tools.RestTools;

/**
 * Servlet implementation class SrvBackup
 */
@WebServlet(urlPatterns={"/backups","/backups/*"})
public class SrvBackup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SrvBackup() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession(true);
			
			User user = null;
			Backup backup = null;
			IGameInstance gameInstance = null;
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();

			RestTools.getId(request);
			if(request.getAttribute("id") != null){
				if(session.getAttribute("backup") != null){
					backup = (Backup) session.getAttribute("backup");
				}
				else if(session.getAttribute("user") != null){
					
					try {
						user = (User) session.getAttribute("user");
						Integer idBk = (Integer) Integer.parseInt(request.getAttribute("id").toString());
						backup = new BackupService().getBackupByUserIdBackup(((User)session.getAttribute("user")), idBk);
					} catch (ServiceException e) {
						out.append("\"error\":"+e.getMessage());
					}
				}else{
					out.append("\"error\":"+"pb user");
				}
			}
			if(backup != null){
				session.setAttribute("backup",backup);
				out.append(new Gson().toJson(backup));
			}
			else{
				out.append("pas backup");
			}
			out.close();
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
