package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Categorie;
import fr.splExceptions.DAOException;

public class CategorieDAO extends DAO<Categorie,Integer> {

	@Override
	public Categorie get(Integer id) throws DAOException {
		Categorie categorie= null;
		ResultSet result;
		try {
			String sql ="Select * From categorie where id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql);
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				result.first();
				categorie = new Categorie();
				categorie.setId(result.getInt("id"));
				categorie.setLibelle(result.getString("libelle"));
				return categorie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	@Override
	public Categorie save(Categorie categorie) throws DAOException {
		try {
			String sql = "INSERT INTO categorie (libelle) VALUES (?)";

			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setString(1, categorie.getLibelle());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.first()) {
				categorie.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating message failed, no ID obtained.");
			}
			statement.close();
			generatedKeys.close();
			return categorie;
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
	}

	// TODO Auto-generated method stub

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Categorie update(Categorie element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAll() throws DAOException {
		ResultSet result;
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			String sql="Select * from categorie";
			result = this.connect.createStatement().executeQuery(sql);
			while(result.next()){
				Categorie categorie = new Categorie();
				categorie.setId(result.getInt("id"));
				categorie.setLibelle(result.getString("libelle"));
				categories.add(categorie);
			}
			return categories;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	}


}
