package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Criminalite;



public class CriminaliteDAO extends DAO<Criminalite, Integer> {


	@Override
	public Criminalite get(Integer id) {
		ResultSet results;
		Criminalite criminalite = null;
		try{
			String req = "SELECT * FROM criminalite WHERE id=?";
			PreparedStatement statement = connect.prepareStatement(req);
			statement.setInt(1, id);
			results = statement.executeQuery();
			if(results != null){
				criminalite.setIndicMineur(results.getInt("indicMineur"));
				criminalite.setCrimeMineur(results.getInt("crimeMineur"));
				criminalite.setIndicMoyen(results.getInt("indicMoyen"));
				criminalite.setCrimeMoyen(results.getInt("crimeMoyen"));
				criminalite.setIndicGrave(results.getInt("indicGrave"));
				criminalite.setCrimeGrave(results.getInt("crimeGrave"));
				criminalite.setIndicTerrorisme(results.getInt("indicTerrorisme"));
				criminalite.setCrimeTerroriste(results.getInt("crimeTerroriste"));
				return criminalite;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Criminalite save(Criminalite element) {
		return element;
	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public void update(Criminalite element) {
	}


	public List<Criminalite> getByBackup(Backup backup) {
		List<Criminalite> historique = null;
		Criminalite crim = null;
		try{
			String req = "SELECT * FROM criminalite WHERE backup=? ORDER BY date DESC";
			PreparedStatement statement = connect.prepareStatement(req);
			statement.setInt(1, backup.getId());
			ResultSet results = statement.executeQuery();
			while ( results.next() ) {
				crim.setIndicMineur(results.getInt("indicMineur"));
				crim.setCrimeMineur(results.getInt("crimeMineur"));
				crim.setIndicMoyen(results.getInt("indicMoyen"));
				crim.setCrimeMoyen(results.getInt("crimeMoyen"));
				crim.setIndicGrave(results.getInt("indicGrave"));
				crim.setCrimeGrave(results.getInt("crimeGrave"));
				crim.setIndicTerrorisme(results.getInt("indicTerrorisme"));
				crim.setCrimeTerroriste(results.getInt("crimeTerroriste"));
				historique.add(crim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historique;
	}

	@Override
	public List<Criminalite> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
