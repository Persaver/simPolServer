package fr.tools;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class RestTools {
	
	private static final Logger LOG = LogManager.getLogger();

	
	public static void getId(HttpServletRequest request){

		String pathInfo = request.getRequestURL().toString();
		Integer id =null;
		//System.out.println("path" + pathInfo);
		String[] pathParts = pathInfo.split("/");
		if(pathParts.length	 > 5){
			 id = Integer.parseInt(pathParts[5]); // {value  a traiter}
			request.setAttribute("id", id);
			
			//System.out.println("id"+id);
		}
		LOG.debug(" RestTools getId  path/ id = {} ",id);

	}
	// transforme en json
	public static String getReturn(Object obj,Boolean error){
		String rt = null;
		StringBuffer buffer = new StringBuffer();
		Gson gson = new Gson();

		buffer.append("{");

		if(error){
			buffer.append("\"error\":");
		}
		else{
			buffer.append("\"succes\":");
		}

		buffer.append(gson.toJson(obj));

		buffer.append("}");
		LOG.debug(" RestTools getreturn  obj {} error {} ",obj.getClass().getName() , error);
		return buffer.toString();
	}
}
