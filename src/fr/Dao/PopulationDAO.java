package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import fr.entities.Backup;
import fr.entities.Population;

public class PopulationDAO extends DAO<Population,Integer> {

	@Override
	public Population get(Integer id){
		ResultSet result;
		Population population = null;
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
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Population save(Population element) {
		try {
			String req = "INSERT INTO population (repartitionPop, fertilite, attractivite) VALUES (?, ?, ?)";
			PreparedStatement statement = this.connect.prepareStatement(req);
			String pop = null;
			// fonction pour faire passer tab[][] en "repartitionPop"
			statement.setString(1, pop);
			statement.setInt(2, element.getFertilite());
			statement.setInt(3, element.getAttractivite());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return element;

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Population element) {
		// TODO Auto-generated method stub

	}


	public List<Population> getAllByBackup(Integer backup) {
		List<Population> populations = new ArrayList<Population>();
		Gson gson = new Gson();
		try {
			//this.connect = AccessDB.seConnecter(); // Connection deja etablie avec le parent?
			String req = "SELECT repartitionPop, fertilite, attractivite FROM population WHERE backup=?";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, backup);
			ResultSet results = statement.executeQuery();
			while ( results.next() ) {
				Population population = new Population();
				// fonction pour transformer "repartitionPop" en tab[][]
				// on recupere la pop que l'on met  dans un Integer[][] et on passe au setter
				population.setPopTab(gson.fromJson(results.getString("popTab"), Integer[][].class));
				population.setFertilite(results.getInt("fertilite"));
				population.setAttractivite(results.getInt("attractivite"));
				populations.add(population);
			}
			return populations;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Population> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
