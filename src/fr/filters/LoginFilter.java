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

import fr.entities.User;
import fr.splExceptions.LoginException;
import fr.tools.LoginTools;

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
		} catch (LoginException e) {
			e.printStackTrace();
		}
		LOG.debug(" Login filter user = {} ",user != null ? user.getId() : null);
		if (user != null){
			request.setAttribute("user", user);
			chain.doFilter(request, response);

		}
		else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setContentType("application/json");
			resp.getWriter().append("not log").close();
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
