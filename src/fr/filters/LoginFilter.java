package fr.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import fr.entities.User;
import fr.splExceptions.BackupException;
import fr.splExceptions.LoginException;
import fr.tools.LoginTools;
import fr.tools.RestTools;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	
	private static final Logger LOG = LogManager.getLogger();


    /**
     * Default constructor. 
     */
    public LoginFilter() {
		;

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = null;
		try {
			user = LoginTools.checkLogin((HttpServletRequest) request);
		
		LOG.debug(" Login filter user = {} ",user != null ? user.getId() : null);
		if (user != null){
			request.setAttribute("user", user);
			LoginTools.checkBackup((HttpServletRequest)request);
			chain.doFilter(request, response);

		}
		else{
			throw new LoginException("Verif user imposible user");
		}
		} catch (LoginException | BackupException e) {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setContentType("application/json");
			resp.getWriter().append(RestTools.getReturn(e.getMessage(), true)).close();		
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
