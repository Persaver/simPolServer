package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.entities.User;

public class UserDAO extends DAO<User,Integer>{

	@Override
	public User get(Integer id) {
		ResultSet result;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM user WHERE id =?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result!= null){
				User user = new User(result.getInt("id"), result.getString("pseudo"), result.getString("password"));
				return user;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	public User checklogin(String login, String password){
		ResultSet results = null;

		try {
			PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM user WHERE login = ? and password = ?");
			prepare.setString(1, login);
			prepare.setString(2, password);

			results = prepare.executeQuery();
			if(results.next()){

				User user = new User(results.getInt("id"), results.getString("pseudo"), results.getString("password"));
				return user;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		// a modifier
		return null;
	}

	@Override
	public void save(User element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
		ResultSet result;
		PreparedStatement prepare;
		
		try {
			prepare = this.connect.prepareStatement("DELETE FROM User where id= ? ");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void update(User element) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
