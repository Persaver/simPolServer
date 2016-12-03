package fr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccessDB {
	private static AccessDB adb;
	private  final Integer MAXCNX = 500;
	private  Integer currentCon = 0;
	
	private static String aUrl = "jdbc:mysql://localhost/simpol";
	private static String aLogin = "root";
	private static String aPassword = "root";
	

	private  ArrayList<Connection> cnx= null;
	
	private AccessDB(){
		this.cnx = new ArrayList<>();
	}
	public static AccessDB getInstance(){
		if(AccessDB.adb == null){
			AccessDB.adb = new AccessDB();
		}
		return AccessDB.adb;
	}
	// POOL de cnx 
	private  Connection getConnection() throws SQLException{
		Connection connection = null;
		Connection storeCon = null;
		if(this.cnx.size() == this.currentCon + 1){
			storeCon = this.cnx.get(this.currentCon);
		}
		if(storeCon != null){
			if(!storeCon.isClosed()){
				storeCon.close();
			}
		}
		else{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection(aUrl, aLogin, aPassword);
					this.cnx.add(connection);
				}catch (SQLException | ClassNotFoundException e){
					throw new SQLException("Problem de connection" + e.getMessage());
				}	
		}
		this.currentCon = this.cnx.size()%this.MAXCNX;
		return connection;
	}

	public  Connection seConnecter() throws SQLException{
		return getConnection();
	};
}
