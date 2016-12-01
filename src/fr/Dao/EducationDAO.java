package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Education;
import fr.splExceptions.DAOException;

public class EducationDAO extends DAO<Education,Integer>{

	@Override
	public Education get(Integer id) throws DAOException{
		ResultSet result;
		Education education = new Education();
		Backup backup = null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from education where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				result.first();
				education = new Education();
				education.setId(result.getInt("id"));
				education.setEdTotale(result.getInt("edTotale"));
				education.setEdSecurite(result.getInt("edSecurite"));
				education.setEdEntretien(result.getInt("edEntretien"));
				education.setEdSante(result.getInt("edSante"));
				education.setEdRecherche(result.getInt("edRecherche"));
				education.setEdTourisme(result.getInt("edTourisme"));
				education.setNbj(result.getInt("nbj"));
				backup = new Backup(result.getInt("id"));
				education.setBackup(backup);
				return education;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	public Education getByBackup(Backup backup) throws DAOException{
		ResultSet result;
		Education education = new Education();
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from education where backup = ?");
			prepare.setInt(1, backup.getId());
			result = prepare.executeQuery();
			if(result != null){
				result.first();
				education = new Education();
				education.setId(result.getInt("id"));
				education.setEdTotale(result.getInt("edTotale"));
				education.setEdSecurite(result.getInt("edSecurite"));
				education.setEdEntretien(result.getInt("edEntretien"));
				education.setEdSante(result.getInt("edSante"));
				education.setEdRecherche(result.getInt("edRecherche"));
				education.setEdTourisme(result.getInt("edTourisme"));
				education.setNbj(result.getInt("nbj"));
				education.setBackup(backup);
				return education;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	@Override
	public Education save(Education element) throws DAOException {
		try {
			String req = "INSERT INTO sante (edTotale, edSecurite, edEntretien, edSante, edRecherche, edTourisme, nbj, backup) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, element.getEdTotale());
			statement.setInt(2, element.getEdSecurite());
			statement.setInt(3, element.getEdEntretien());
			statement.setInt(3, element.getEdSante());
			statement.setInt(5, element.getEdRecherche());
			statement.setInt(6, element.getEdTourisme());
			statement.setInt(7, element.getNbj());
			statement.setInt(8, element.getBackup().getId());
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
	public void update(Education element) {
		// TODO Auto-generated method stub

	}

	public List<Education> getAllByBackup(Backup backup) throws DAOException{
		List<Education> educations = new ArrayList<Education>();
		try {
			//this.connect = AccessDB.seConnecter(); // Connection deja etablie avec le parent?
			String req = "SELECT * FROM sante WHERE backup=?";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, backup.getId());
			ResultSet result = statement.executeQuery();
			while ( result.next() ) {
				Education education = new Education();
				education.setId(result.getInt("id"));
				education.setEdTotale(result.getInt("edTotale"));
				education.setEdSecurite(result.getInt("edSecurite"));
				education.setEdEntretien(result.getInt("edEntretien"));
				education.setEdSante(result.getInt("edSante"));
				education.setEdRecherche(result.getInt("edRecherche"));
				education.setEdTourisme(result.getInt("edTourisme"));
				education.setNbj(result.getInt("nbj"));
				education.setBackup(backup);
				educations.add(education);
			}
			return educations;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<Education> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
