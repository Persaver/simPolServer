package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import fr.entities.Backup;
import fr.entities.Population;
import fr.splExceptions.DAOException;

public class PopulationDAO extends DAO<Population,Integer> {

	@Override
	public Population get(Integer id) throws DAOException{
		ResultSet result;
		Population population = new Population();
		Backup backup = null;
		// population recuperé en Gson
		Gson gson = null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from population where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				gson = new Gson();
				result.first();
				population = new Population();
				population.setId(result.getInt("id"));
				population.setFertilite(result.getInt("fertilite"));
				population.setAttractivite(result.getInt("attractivite"));
				// on recupere la pop que l'on met  dans un Integer[][] et on passe au setter
				population.setPopTab(gson.fromJson(result.getString("popTab"), Integer[][].class));
				backup = new Backup(result.getInt("id"));
				population.setBackup(backup);
				return population;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	public Population getByBackup(Backup backup) throws DAOException{
		ResultSet result;
		Population population = new Population();
		// population recuperé en Gson
		Gson gson = null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from population where backup = ?");
			prepare.setInt(1, backup.getId());
			result = prepare.executeQuery();
			if(result != null){
				gson = new Gson();
				result.last();
				population = new Population();
				population.setId(result.getInt("id"));
				population.setFertilite(result.getInt("fertilite"));
				population.setAttractivite(result.getInt("attractivite"));
				// on recupere la pop que l'on met  dans un Integer[][] et on passe au setter
				population.setPopTab(gson.fromJson(result.getString("popTab"), Integer[][].class));
				population.setBackup(backup);
				return population;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}
	@Override
	public Population save(Population element) throws DAOException {
		Gson gson = null;
		try {
			String req = "INSERT INTO population (repartitionPop, fertilite, attractivite, backup) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = this.connect.prepareStatement(req);
			gson = new Gson();
			String pop = gson.toJson(element.getPopTab());
			// fonction pour faire passer tab[][] en "repartitionPop"

			statement.setString(1, pop);
			statement.setInt(2, element.getFertilite());
			statement.setInt(3, element.getAttractivite());
			statement.setInt(4, element.getBackup().getId());
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.first()) {
				element.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating message failed, no ID obtained.");
			}
			statement.close();
			generatedKeys.close();
			return element;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}


	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Population update(Population element) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Population> getAllByBackup(Backup backup) throws DAOException{
		List<Population> populations = new ArrayList<Population>();
		Gson gson = new Gson();
		try {
			//this.connect = AccessDB.seConnecter(); // Connection deja etablie avec le parent?
			String req = "SELECT * FROM population WHERE backup=?";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, backup.getId());
			ResultSet results = statement.executeQuery();
			while ( results.next() ) {
				Population population = new Population();
				// fonction pour transformer "repartitionPop" en tab[][]
				// on recupere la pop que l'on met  dans un Integer[][] et on passe au setter
				population.setPopTab(gson.fromJson(results.getString("popTab"), Integer[][].class));
				population.setFertilite(results.getInt("fertilite"));
				population.setAttractivite(results.getInt("attractivite"));
				population.setNbj(results.getInt("nbj"));
				population.setBackup(backup);
				populations.add(population);
			}
			return populations;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<Population> getAll() throws DAOException{
		// TODO Auto-generated method stub
		return null;
	}

}
