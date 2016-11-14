package fr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccessDB {
//	private static AccessDB adb;
//	private static final int MAXCNX = 10;
//	private ArrayList<Connection> cnx= new ArrayList<>();
//	
//	private AccessDB(){
//
//	}
//	public static AccessDB getInstance(){
//		if(adb != null){
//			adb = new AccessDB();
//		}
//		return new AccessDB();
//	}
	private static Connection cxt;
	private static String aUrl = "jdbc:mysql://localhost/SimPol";
	private static String aLogin = "root";
	private static String aPassword = "root";
	
	public static Connection seConnecter() throws SQLException{
		if(cxt == null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cxt= DriverManager.getConnection(aUrl, aLogin, aPassword);
			}catch (SQLException e){
				e.printStackTrace();
			} catch (Throwable e) {
				throw new SQLException("Problem de driver");
			}	
		}
		return cxt;
	};
}
