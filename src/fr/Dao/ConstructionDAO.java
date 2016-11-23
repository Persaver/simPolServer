package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public Construction save(Construction element) {
		try {
			String sql = "INSERT INTO backup_construction (h,w, url, basSalarie, baseCadre, baseRisque, baseAttractivite, modSalarie, modCadre, modRisque, modAttractivite, specificite, categorie) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setInt(1, element.getH());
			statement.setInt(2, element.getW());
			statement.setString(3, element.getUrl());
			statement.setInt(4, element.getBaseSalarie());
			statement.setInt(5, element.getBaseCadre());
			statement.setInt(6, element.getBaseRisque());
			statement.setInt(7, element.getBaseAttractivite());
			statement.setInt(8, element.getModSalarie());
			statement.setInt(9, element.getModCadre());
			statement.setInt(10, element.getModRisque());
			statement.setInt(11, element.getModAttractivite());
			statement.setString(12, element.getSpecificite());
			statement.setInt(13, element.getCategorie().getId());
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
