package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Categorie;

public class CategorieDAO extends DAO<Categorie,Integer> {

	@Override
	public Categorie get(Integer id) {
		Categorie categorie= null;
		ResultSet result;
		try {
			String sql ="Select * From categorie where id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql);
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				categorie = new Categorie(result.getInt("id"), result.getString("libelle"));
				return categorie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Categorie save(Categorie element) {
		try {
			String sql = "INSERT INTO categorie (libelle) VALUES (?)";

			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setString(1, element.getLibelle());
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

	// TODO Auto-generated method stub

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Categorie element) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Categorie> getAll() {
		ResultSet result;
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			String sql="Select * from categorie";
			result = this.connect.createStatement().executeQuery(sql);
			while(result.next()){
				Categorie categorie = new Categorie(result.getInt("categorie.id"), result.getString("libelle"));
				categories.add(categorie);
			}
			return categories;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}


}
