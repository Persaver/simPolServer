package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.Dao.CriminaliteDAO;
import fr.entities.Backup;
import fr.entities.Criminalite;
import fr.splExceptions.BackupException;
import fr.splExceptions.DAOException;
import fr.tools.LoginTools;
import fr.tools.RestTools;

/**
 * Servlet implementation class CiminaliteSrv
 */
@WebServlet(urlPatterns={"/criminalites","/criminalites/*"})
public class CiminaliteSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CiminaliteSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Criminalite> criminalites = null;
		Criminalite criminalite = null;
		Backup backup = null;
		response.setContentType("application/json;charset=UTF-8");
		RestTools.getId(request);
		PrintWriter out = response.getWriter();
		CriminaliteDAO criminaliteDao = new CriminaliteDAO();
		try {
			backup= LoginTools.checkBackup(request);
			if(request.getParameter("all").equals("1")){
				criminalite = criminaliteDao.getByBackup(backup);
				out.append(RestTools.getReturn( criminalite, criminalite == null));
			}
			else{
				criminalites = criminaliteDao.getAllByBackup(backup);
				out.append(RestTools.getReturn( criminalites, criminalites == null));
			}
		} catch (DAOException | BackupException e) {
			// TODO Auto-generated catch block
			out.append(RestTools.getReturn(e.getMessage(), true));
		}
		// si user == null return {error:{msg} }
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
