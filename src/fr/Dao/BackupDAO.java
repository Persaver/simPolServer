package fr.Dao;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Categorie;
import fr.entities.Construction;
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
				user = new UserDAO().get(result.getInt("user"));
				backup = new Backup(result.getInt("id"), result.getDate("date_creation"), result.getDate("date_last"),user);
				return backup;
			}
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<Backup> getByUser(int id){
		return null;
		// TODO Auto-generated method stub

	}
}


