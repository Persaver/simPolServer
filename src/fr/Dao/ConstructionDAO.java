package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Categorie;
import fr.entities.Construction;

public class ConstructionDAO extends DAO<Construction,Integer>{

	@Override
	public Construction get(Integer id) {
		ResultSet result;
		Construction construction = null;
		Categorie categorie = null;
		try
		{
			PreparedStatement prepare = this.connect.prepareStatement("Select * from construction where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				result.first();
				construction = new Construction(
						result.getInt("id"),
						result.getString("designation"),
						result.getString("url"),
						result.getInt("h"),
						result.getInt("w"),
						result.getInt("baseSalarie"),
						result.getInt("baseCadre"),
						result.getInt("baseRisque"),
						result.getInt("baseAttractivite"),
						result.getInt("modSalarie"),
						result.getInt("modCadre"),
						result.getInt("modRisque"),
						result.getInt("modAttractivite"),
						result.getString("specificites"));
				categorie = new Categorie(result.getInt("id"));
				construction.setCategorie(categorie);
				return construction;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(Construction element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Construction element) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Construction> getAll() {
		ResultSet result;
		List<Construction> constructions = new ArrayList<Construction>();
		try {
			result = this.connect.createStatement().executeQuery("Select * from construction");
			while(result.next()){
				Construction construction = new Construction(
						result.getInt("id"),
						result.getString("designation"),
						result.getString("url"),
						result.getInt("h"),
						result.getInt("w"),
						result.getInt("baseSalarie"),
						result.getInt("baseCadre"),
						result.getInt("baseRisque"),
						result.getInt("baseAttractivite"),
						result.getInt("modSalarie"),
						result.getInt("modCadre"),
						result.getInt("modRisque"),
						result.getInt("modAttractivite"),
						result.getString("specificites"));
				Categorie categorie = new Categorie(result.getInt("id"));
				construction.setCategorie(categorie);
				constructions.add(construction);
			}
			return constructions;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return constructions;
	}

}
