package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Sante;
import fr.splExceptions.DAOException;

public class SanteDAO extends DAO<Sante,Integer> {

	@Override
	public Sante get(Integer id) throws DAOException{
		ResultSet result;
		Sante sante = null;
		Backup backup = null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from sante where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				result.first();
				sante = new Sante();
				sante.setId(result.getInt("id"));
				sante.setHygiene(result.getInt("hygiene"));
				sante.setNbMalades(result.getInt("nbMalades"));
				sante.setNbAccidents(result.getInt("nbAccidents"));
				sante.setSoins(result.getInt("Soins"));
				sante.setEchecs(result.getInt("echecs"));
				sante.setNbj(result.getInt("nbj"));
				backup = new Backup(result.getInt("id"));
				sante.setBackup(backup);
				return sante;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	public Sante getByBackup(Backup backup) throws DAOException{
		ResultSet result;
		Sante sante = null;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from sante where backup = ?");
			prepare.setInt(1, backup.getId());
			result = prepare.executeQuery();
			if(result != null){
				result.last();
				sante = new Sante();
				sante.setId(result.getInt("id"));
				sante.setHygiene(result.getInt("hygiene"));
				sante.setNbMalades(result.getInt("nbMalades"));
				sante.setNbAccidents(result.getInt("nbAccidents"));
				sante.setSoins(result.getInt("Soins"));
				sante.setEchecs(result.getInt("echecs"));
				sante.setNbj(result.getInt("nbj"));
				sante.setBackup(backup);
				return sante;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}
	@Override
	public Sante save(Sante element) throws DAOException {
		try {
			String req = "INSERT INTO sante (hygiene, nbMalalades, nbAccidents, soins, echecs, nbj, backup) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, element.getHygiene());
			statement.setInt(2, element.getNbMalades());
			statement.setInt(3, element.getNbAccidents());
			statement.setInt(3, element.getSoins());
			statement.setInt(5, element.getEchecs());
			statement.setInt(6, element.getNbj());
			statement.setInt(7, element.getBackup().getId());
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
	public Sante update(Sante element) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Sante> getAllByBackup(Backup backup) throws DAOException{
		List<Sante> santes = new ArrayList<Sante>();
		try {
			//this.connect = AccessDB.seConnecter(); // Connection deja etablie avec le parent?
			String req = "SELECT * FROM sante WHERE backup=?";
			PreparedStatement statement = this.connect.prepareStatement(req);
			statement.setInt(1, backup.getId());
			ResultSet result = statement.executeQuery();
			while ( result.next() ) {
				Sante sante = new Sante();
				sante.setId(result.getInt("id"));
				sante.setHygiene(result.getInt("hygiene"));
				sante.setNbMalades(result.getInt("nbMalades"));
				sante.setNbAccidents(result.getInt("nbAccidents"));
				sante.setSoins(result.getInt("Soins"));
				sante.setEchecs(result.getInt("echecs"));
				sante.setNbj(result.getInt("nbj"));
				santes.add(sante);
			}
			return santes;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<Sante> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
