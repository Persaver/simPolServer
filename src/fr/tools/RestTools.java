package fr.tools;

import javax.servlet.http.HttpServletRequest;

public class RestTools {

public static void getId(HttpServletRequest request){
	
	String pathInfo = request.getRequestURL().toString(); 
	System.out.println("path" + pathInfo);
	String[] pathParts = pathInfo.split("/");
	if(pathParts.length	 > 5){
		String id = pathParts[5]; // {value  a traiter}
		request.setAttribute("id", id);
		System.out.println("id"+id);
	}
}
}
