package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Categorie;
import fr.entities.Construction;
import fr.splExceptions.DAOException;

public class ConstructionDAO extends DAO<Construction,Integer>{

	@Override
	public Construction get(Integer id) throws DAOException {
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
				construction = new Construction();
				construction.setId(result.getInt("id"));
				construction.setDesignation(result.getString("designation"));
				construction.setH(result.getInt("h"));
				construction.setW(result.getInt("w"));
				construction.setBaseSalarie(result.getInt("baseSalarie"));
				construction.setBaseCadre(result.getInt("baseCadre"));
				construction.setBaseRisque(result.getInt("baseRisque"));
				construction.setBaseAttractivite(result.getInt("baseAttractivite"));
				construction.setModSalarie(result.getInt("modSalarie"));
				construction.setModCadre(result.getInt("modCadre"));
				construction.setModAttractivite(result.getInt("modAttractivite"));
				construction.setSpecificite(result.getString("specificites"));
				categorie = new Categorie(result.getInt("id"));
				construction.setCategorie(categorie);
				return construction;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	@Override
	public Construction save(Construction element) throws DAOException {
		try {
			String sql = "INSERT INTO backup_construction (h,w,basSalarie, baseCadre, baseRisque, baseAttractivite, modSalarie, modCadre, modRisque, modAttractivite, specificite, categorie) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setInt(1, element.getH());
			statement.setInt(2, element.getW());
			statement.setInt(3, element.getBaseSalarie());
			statement.setInt(4, element.getBaseCadre());
			statement.setInt(5, element.getBaseRisque());
			statement.setInt(6, element.getBaseAttractivite());
			statement.setInt(7, element.getModSalarie());
			statement.setInt(8, element.getModCadre());
			statement.setInt(9, element.getModRisque());
			statement.setInt(10, element.getModAttractivite());
			statement.setString(11, element.getSpecificite());
			statement.setInt(12, element.getCategorie().getId());
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
			throw new DAOException(e.getMessage());
		}
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
	public List<Construction> getAll() throws DAOException {
		ResultSet result;
		List<Construction> constructions = new ArrayList<Construction>();
		try {
			result = this.connect.createStatement().executeQuery("Select * from construction");
			while(result.next()){
				Construction construction = new Construction();
				construction = new Construction();
				construction.setId(result.getInt("id"));
				construction.setDesignation(result.getString("designation"));
				construction.setH(result.getInt("h"));
				construction.setW(result.getInt("w"));
				construction.setBaseSalarie(result.getInt("baseSalarie"));
				construction.setBaseCadre(result.getInt("baseCadre"));
				construction.setBaseRisque(result.getInt("baseRisque"));
				construction.setBaseAttractivite(result.getInt("baseAttractivite"));
				construction.setModSalarie(result.getInt("modSalarie"));
				construction.setModCadre(result.getInt("modCadre"));
				construction.setModAttractivite(result.getInt("modAttractivite"));
				construction.setSpecificite(result.getString("specificites"));
				Categorie categorie = new Categorie(result.getInt("id"));
				construction.setCategorie(categorie);
				constructions.add(construction);
			}
			return constructions;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	}

}
