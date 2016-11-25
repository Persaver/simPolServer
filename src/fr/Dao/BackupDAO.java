package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.User;


public class BackupDAO extends DAO<Backup,Integer> {

	@Override
	public Backup get(Integer id) {
		ResultSet result;
		Backup backup = null;
		User user= null;
		try {
			String sql = "SELECT * FROM backup WHERE id =?";
			PreparedStatement prepare = this.connect.prepareStatement(sql);
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result!= null){
				backup = new Backup();
				backup.setId(result.getInt("id"));
				backup.setDate_creation(result.getString("date_creation"));
				backup.setDate_last(result.getString("date_last"));
				backup.setNbj(result.getInt("nbj"));
				user = new User(result.getInt("user"));
				backup.setUser(user);
			}
			return backup;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return backup;
	}

	public Backup getByUser(User user){
		ResultSet result;
		Backup backup = null;
		try {
			String sql = "SELECT * FROM backup WHERE user =?";
			PreparedStatement prepare = this.connect.prepareStatement(sql);
			prepare.setInt(1, user.getId());
			result = prepare.executeQuery();
			if(result!= null){
				backup = new Backup();
				backup.setId(result.getInt("id"));
				backup.setDate_creation(result.getString("date_creation"));
				backup.setDate_last(result.getString("date_last"));
				backup.setNbj(result.getInt("nbj"));
				backup.setUser(user);
			}
			return backup;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return backup;
	}

	@Override
	public Backup save(Backup element) {
		try {
			String sql = "INSERT INTO backup (date_creation, nbj, user) VALUES (?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setString(1, element.getDate_creation());
			statement.setInt(2, element.getNbj());
			statement.setInt(3, element.getUser().getId());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.first()) {
				element.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating message failed, no ID obtained.");
			}
			statement.close();
			generatedKeys.close();
			return element;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
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
				Backup backup = new Backup();
				backup.setId(result.getInt("id"));
				backup.setDate_creation(result.getString("date_creation"));
				backup.setDate_last(result.getString("date_last"));
				backup.setNbj(result.getInt("nbj"));
				User user = new User(result.getInt("user"));
				backup.setUser(user);
				backups.add(backup);
			}
			return backups;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
