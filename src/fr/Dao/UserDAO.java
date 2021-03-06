package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.User;
import fr.splExceptions.DAOException;

public class UserDAO extends DAO<User,Integer>{

	@Override
	public User get(Integer id) throws DAOException{
		ResultSet result;
		User user = null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM user WHERE id =?");
			prepare.setInt(1, id.intValue());
			result = prepare.executeQuery();
			if((result!= null) && result.next()){
				user = new User(result.getInt("id"), result.getString("login"), result.getString("password"));
				return user;
			}
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
		return null;


	}

	public User checklogin(String login, String password) throws DAOException{
		ResultSet result = null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM user WHERE login LIKE ? and password = ?");
			prepare.setString(1, login);
			prepare.setString(2, password);

			result = prepare.executeQuery();
			if((result!= null) && result.next()){

				User user = new User(result.getInt("id"), result.getString("login"), result.getString("password"));
				return user;
			}
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
		// a modifier
		return null;
	}

	@Override
	public User save(User element) throws DAOException {
		try {
			String sql = "INSERT INTO user (login, password) VALUES (?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setString(2, element.getLogin());
			statement.setString(3, element.getPassword());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.first()) {
				element.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
			statement.close();
			generatedKeys.close();
			return element;
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}

	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public User update(User element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() throws DAOException {
		ResultSet result;
		List<User> users = new ArrayList<User>();
		try {
			String sql = "Select * from user";
			result = this.connect.createStatement().executeQuery(sql);
			while(result.next()){
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLogin(result.getString("login"));
				user.setPassword(result.getString("password"));
				users.add(user);
			}
			return users ;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}

	}
}
