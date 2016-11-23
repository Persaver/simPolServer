package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.entities.Population;

public class PopulationDAO extends DAO<Population, Integer> {

	@Override
	public Population get(Integer id){
		Population population = null;
		try {
			//this.connect = AccessDB.seConnecter(); // Connection deja etablie avec le parent?
			String req = "SELECT * FROM population WHERE id=?";
			PreparedStatement statement = connect.prepareStatement(req);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if(result!= null){
			int tab[][] = new int[130][12];
			// fonction pour transformer "repartitionPop" en tab[][]
			population.setPopTab(tab);
			population.setFertilite(results.getInt("fertilite"));
			population.setAttractivite(results.getInt("attractivite"));
			return population;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Population save(Population element) {
		try {
		String req = "INSERT INTO population (repartitionPop, fertilite, attractivite) VALUES (?, ?, ?)";
			PreparedStatement statement = connect.prepareStatement(req);
			String pop = null;
			// fonction pour faire passer tab[][] en "repartitionPop"
			statement.setString(1, pop);
			statement.setInt(2, element.getFertilite());
			statement.setInt(3, element.getAttractivite());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Population element) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Population> getAll(Integer backup) {
		List<Population> historique = null;
		Population p = null;
		try {
			//this.connect = AccessDB.seConnecter(); // Connection deja etablie avec le parent?
			String req = "SELECT repartitionPop, fertilite, attractivite FROM population WHERE id=?";
			PreparedStatement statement = connect.prepareStatement(req);
			statement.setInt(1, backup);
			ResultSet results = statement.executeQuery();
			while ( results.next() ) {
				int tab[][] = new int[130][12];
				// fonction pour transformer "repartitionPop" en tab[][]
				p.setPopTab(tab);
				p.setFertilite(results.getInt("fertilite"));
				p.setAttractivite(results.getInt("attractivite"));
				historique.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historique;
	}

	@Override
	public List<Population> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
