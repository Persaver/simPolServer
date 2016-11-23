package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.User;


public class BackupDAO extends DAO<Backup,Integer>{

	@Override
	public Backup get(Integer id) {
		ResultSet result;
		Backup backup = null;
		User user= null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM backup WHERE id =?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result!= null){
				backup = new Backup(
						result.getInt("id"),
						result.getDate("date_creation"),
						result.getDate("date_last"),
						result.getInt("nbj"));
				user = new User(result.getInt("user"));
				backup.setUser(user);
			}
			return backup;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return backup;
	}

	public Backup getByUser( Integer userId){
		ResultSet result;
		Backup backup = null;
		User user= null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM backup WHERE user =?");
			prepare.setInt(1, userId);
			result = prepare.executeQuery();
			if(result!= null){
				backup = new Backup(
						result.getInt("id"),
						result.getDate("date_creation"),
						result.getDate("date_last"),
						result.getInt("nbj"));
				user = new User(userId);
				backup.setUser(user);
			}
			return backup;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return backup;
	}

	@Override
	public void save(Backup element) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Backup element) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Backup> getAll() {
		ResultSet result;
		List<Backup> backups = new ArrayList<Backup>();
		try {
			result = this.connect.createStatement().executeQuery("Select * from backup");
			while(result.next()){
				Backup backup = new Backup(
						result.getInt("id"),
						result.getDate("date_creation"),
						result.getDate("date_last"),
						result.getInt("nbj"));
				User user = new User(result.getInt("user"));
				backup.setUser(user);
				backups.add(backup);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return backups ;
	}

	public List<Backup> getByUser(int id){
		return null;
		// TODO Auto-generated method stub

	}
}


