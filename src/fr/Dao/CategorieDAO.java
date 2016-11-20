package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Categorie;

public class CategorieDAO extends DAO<Categorie,Integer> {

	@Override
	public Categorie get(Integer id) {
		ResultSet result;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * From categorie where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				Categorie categorie = new Categorie(result.getInt("id"), result.getString("libelle"));
				return categorie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(Categorie element) {
		// TODO Auto-generated method stub

	}

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
			result = this.connect.createStatement().executeQuery("Select * from categorie");
			while(result.next()){
				Categorie categorie = new Categorie(result.getInt("categorie.id"), result.getString("libelle"));
				categories.add(categorie);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories ;
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}


}
