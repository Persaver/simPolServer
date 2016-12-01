package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Criminalite;
import fr.splExceptions.DAOException;



public class CriminaliteDAO extends DAO<Criminalite,Integer> {


	@Override
	public Criminalite get(Integer id) throws DAOException {
		ResultSet results;
		Criminalite criminalite = null;
		Backup backup = null;
		try{
			String req = "SELECT * FROM criminalite WHERE id=?";
			PreparedStatement statement = this.connect.prepareStatement(req);
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
				criminalite.setNbj(results.getInt("nbj"));
				backup = new Backup(results.getInt("backup"));
				criminalite.setBackup(backup);
				return criminalite;
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	public Criminalite getByBackup(Backup backup) throws DAOException {
		ResultSet results;
		Criminalite criminalite = new Criminalite();
		try{
			String req = "SELECT * FROM criminalite WHERE backup"
					+ "=?";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, backup.getId());
			results = statement.executeQuery();
			if(results != null){
				results.last();
				criminalite.setIndicMineur(results.getInt("indicMineur"));
				criminalite.setCrimeMineur(results.getInt("crimeMineur"));
				criminalite.setIndicMoyen(results.getInt("indicMoyen"));
				criminalite.setCrimeMoyen(results.getInt("crimeMoyen"));
				criminalite.setIndicGrave(results.getInt("indicGrave"));
				criminalite.setCrimeGrave(results.getInt("crimeGrave"));
				criminalite.setIndicTerrorisme(results.getInt("indicTerrorisme"));
				criminalite.setCrimeTerroriste(results.getInt("crimeTerroriste"));
				criminalite.setNbj(results.getInt("nbj"));
				criminalite.setBackup(backup);
				return criminalite;
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return null;
	}
	@Override
	public Criminalite save(Criminalite element) throws DAOException {
		try {
			String sql = "INSERT INTO criminalite (indicMineur, crimeMineur, indicMoyen, crimeMoyen, indicGrave, crimeGrave, indicTerrorisme, crimeTerroriste, nbj, backup) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setInt(1, element.getIndicMineur());
			statement.setInt(2, element.getCrimeMineur());
			statement.setInt(3, element.getIndicMoyen());
			statement.setInt(4, element.getCrimeMoyen());
			statement.setInt(5, element.getIndicGrave());
			statement.setInt(6, element.getCrimeGrave());
			statement.setInt(7, element.getIndicTerrorisme());
			statement.setInt(8, element.getCrimeTerroriste());
			statement.setInt(9, element.getNbj());
			statement.setInt(10, element.getBackup().getId());
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
	}

	@Override
	public Criminalite update(Criminalite element) {
		return element;
	}


	public List<Criminalite> getAllByBackup(Backup backup) throws DAOException {
		List<Criminalite> criminalites = new ArrayList<Criminalite>();
		try{
			String req = "SELECT * FROM criminalite WHERE backup=? ";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, backup.getId());
			ResultSet results = statement.executeQuery();
			while ( results.next() ) {
				Criminalite criminalite = new Criminalite();
				criminalite.setIndicMineur(results.getInt("indicMineur"));
				criminalite.setCrimeMineur(results.getInt("crimeMineur"));
				criminalite.setIndicMoyen(results.getInt("indicMoyen"));
				criminalite.setCrimeMoyen(results.getInt("crimeMoyen"));
				criminalite.setIndicGrave(results.getInt("indicGrave"));
				criminalite.setCrimeGrave(results.getInt("crimeGrave"));
				criminalite.setIndicTerrorisme(results.getInt("indicTerrorisme"));
				criminalite.setCrimeTerroriste(results.getInt("crimeTerroriste"));
				criminalite.setNbj(results.getInt("nbj"));
				criminalite.setBackup(backup);
				criminalites.add(criminalite);
			}
			return criminalites;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	@Override
	public List<Criminalite> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
