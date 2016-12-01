package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.User;
import fr.splExceptions.DAOException;


public class BackupDAO extends DAO<Backup,Integer> {

	@Override
	public Backup get(Integer id) throws DAOException {
		ResultSet result;
		Backup backup = null;
		User user= null;
		try {
			String sql = "SELECT * FROM backup WHERE id =?";
			PreparedStatement prepare = this.connect.prepareStatement(sql);
			prepare.setInt(1, id.intValue());
			result = prepare.executeQuery();
			if((result!= null) && result.next()){
				backup = new Backup();
				backup.setId(result.getInt("id"));
				backup.setDate_creation(result.getString("date_creation"));
				backup.setDate_last(result.getString("date_last"));
				backup.setNbj(result.getInt("nbj"));
				user = new UserDAO().get(result.getInt("user"));
				backup.setUser(user);
				return backup;
			}
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
		return null;

	}

	public List<Backup> getByUser(User user) throws DAOException{
		ResultSet result;
		List<Backup> backups = null;
		Backup backup = null;
		try {
			String sql = "SELECT * FROM backup WHERE user =?";
			PreparedStatement prepare = this.connect.prepareStatement(sql);
			prepare.setInt(1, user.getId());
			result = prepare.executeQuery();
			if(result!= null){
				backups = new ArrayList<Backup>(); {
				};
				while(result.next()){
					backup = new Backup();
					backup.setId(result.getInt("id"));
					backup.setDate_creation(result.getString("date_creation"));
					backup.setDate_last(result.getString("date_last"));
					backup.setNbj(result.getInt("nbj"));
					backup.setUser(user);
					backups.add(backup);
				}
				return backups;
			}
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
		return null;

	}

	@Override
	public Backup save(Backup backup) throws DAOException {
		try {
			String sql = "INSERT INTO backup (date_creation, nbj, user) VALUES (?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setString(1, backup.getDate_creation());
			statement.setInt(2, backup.getNbj());
			statement.setInt(3, backup.getUser().getId());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.first()) {
				backup.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating message failed, no ID obtained.");
			}
			statement.close();
			generatedKeys.close();
			return backup;
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Backup update(Backup element) throws DAOException{
		Backup backup = null;
		// pour verifie si modifie
		Integer cptRow = null;
		try {
			String sql = "UPDATE backup "
					+ "SET"
					+"date_creation = ?,"
					+ "nbj = ?, "
					+ "user = ?"
					+ "WHERE id = ?";
			PreparedStatement statement = this.connect.prepareStatement( sql);
			statement.setString(1, backup.getDate_creation());
			statement.setInt(2, backup.getNbj());
			statement.setInt(3, backup.getUser().getId());
			statement.setInt(4, element.getId());
			cptRow = statement.executeUpdate();
			// si plus petit que 1 pas de modif effectué
			if ((cptRow == null) || (cptRow < 1)){
				throw new SQLException("Creating message failed, no ID obtained.");
			}
			statement.close();
			return backup;
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}

	}

	@Override
	public List<Backup> getAll() throws DAOException {
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
			throw new DAOException(e.getMessage());
		}
	}

}
