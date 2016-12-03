package fr.Dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.db.AccessDB;
import fr.splExceptions.DAOException;

public abstract class DAO<T,I>{
	private static final Logger LOG = LogManager.getLogger();
	
	public Connection connect = null;


	public DAO(){
		AccessDB adb = AccessDB.getInstance();
		try {
			if(adb != null){
				this.connect = adb.seConnecter();
				LOG.debug(" connect db {} ",this.getClass().getName());
			}
			else{
				LOG.debug(" connect db pas de AccessDB");
				throw new DAOException(" connect db pas de AccessDB");
			}
			

		} catch (SQLException | DAOException e) {
			e.printStackTrace();
		}
		//		System.out.println("Connecté");
	};
	public abstract T get(I id) throws DAOException;
	public abstract T save(T element) throws DAOException;
	public abstract void delete(I id);
	public abstract T update(T element) throws DAOException;
	public abstract List<T> getAll() throws DAOException;
	
	@Override
	public void finalize() throws SQLException{
		LOG.debug("DAO close connection");
		this.connect.close();
	}
}
