package fr.tools;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

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
		return buffer.toString();
	}
}
