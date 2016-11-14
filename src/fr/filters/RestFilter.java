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

/**
 * Servlet Filter implementation class RestFilter
 */
//@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
//					, urlPatterns = { "/backupconstructions","/backupconstructions/*" })
public class RestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RestFilter() {
        // TODO Auto-generated constructor stub
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
		// on recupere le chemin pour voir si all ou id
		
		String pathInfo = ((HttpServletRequest) request).getPathInfo(); 
		System.out.println("path" + pathInfo);
		String[] pathParts = pathInfo.split("/");
		if(pathParts.length	 > 1){
			String id = pathParts[1]; // {value  a traiter}
			request.setAttribute("id", id);
		}
		System.out.println("pathInfo");
		chain.doFilter((ServletRequest)request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
