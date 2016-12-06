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

import fr.Dao.BudgetDAO;
import fr.entities.Backup;
import fr.entities.Budget;
import fr.splExceptions.BackupException;
import fr.splExceptions.DAOException;
import fr.tools.LoginTools;
import fr.tools.RestTools;

/**
 * Servlet implementation class BudgetSrv
 */
@WebServlet(urlPatterns={"/budgets", "/budgets/*"})
public class BudgetSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BudgetSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Budget> budgets = null;
		Budget budget = null;
		Backup backup = null;
		response.setContentType("application/json;charset=UTF-8");
		RestTools.getId(request);
		PrintWriter out = response.getWriter();
		BudgetDAO budgetDao = new BudgetDAO();
		try {
			backup= LoginTools.checkBackup(request);
			if(request.getParameter("all").equals("1")){
				budget = budgetDao.getByBackup(backup);
				out.append(RestTools.getReturn( budget, budget == null));
			}
			else{
				budgets = budgetDao.getAllByBackup(backup);
				out.append(RestTools.getReturn( budgets, budgets == null));
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
