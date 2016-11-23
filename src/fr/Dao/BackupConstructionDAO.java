package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.entities.Construction;

public class BackupConstructionDAO extends DAO<BackupConstruction,Integer> {

	@Override
	public BackupConstruction get(Integer id) {
		ResultSet result;
		try
		{
			PreparedStatement prepare = this.connect.prepareStatement("Select * from backupconstruction where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				result.first();
				BackupConstruction backupConstruction = new BackupConstruction(
						result.getInt("id"),
						result.getInt("x"),
						result.getInt("y"),
						result.getInt("nbSalarie"),
						result.getInt("nbCadre"),
						result.getInt("risque"),
						result.getInt("budget"),
						result.getInt("attractivite"),
						result.getInt("postePourvu"),
						result.getString("specificites"));
				Construction construction = new Construction(result.getInt("construction"));
				Backup backup = new Backup(result.getInt("backup"));
				backupConstruction.setConstruction(construction);
				backupConstruction.setBackup(backup);
				return backupConstruction;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public BackupConstruction save(BackupConstruction element) {
		try {
			String sql = "INSERT INTO backup_construction (x,y,nbSalarie,nbCadre,risque,budget,attractivite,postePourvu,specificite, construction, backup) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setInt(1, element.getX());
			statement.setInt(2, element.getY());
			statement.setInt(3, element.getNbSalarie());
			statement.setInt(4, element.getNbCadre());
			statement.setInt(5, element.getRisque());
			statement.setInt(6, element.getBudget());
			statement.setInt(7, element.getAttractivite());
			statement.setInt(8, element.getPostePourvu());
			statement.setString(9, element.getSpecificite());
			statement.setInt(10, element.getConstruction().getId());
			statement.setInt(11, element.getBackup().getId());
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
	public void update(BackupConstruction element) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BackupConstruction> getAll() {
		ResultSet result;
		List<BackupConstruction> backupConstructions = new ArrayList<BackupConstruction>();
		try {
			result = this.connect.createStatement().executeQuery("Select * from backupconstruction");
			while(result.next()){
				BackupConstruction backupConstruction = new BackupConstruction(
						result.getInt("id"),
						result.getInt("x"),
						result.getInt("y"),
						result.getInt("nbSalarie"),
						result.getInt("nbCadre"),
						result.getInt("risque"),
						result.getInt("budget"),
						result.getInt("attractivite"),
						result.getInt("postePourvu"),
						result.getString("specificites"));
				Construction construction = new Construction(result.getInt("construction"));
				Backup backup = new Backup(result.getInt("backup"));
				backupConstruction.setConstruction(construction);
				backupConstruction.setBackup(backup);
				backupConstructions.add(backupConstruction);
			}
			return backupConstructions ;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

	public List<BackupConstruction> getAllByBackUp(Integer backupId) {
		ResultSet result;
		List<BackupConstruction> backupConstructionsByBackup = new ArrayList<BackupConstruction>();
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from backupconstruction where backup = ?");
			prepare.setInt(1, backupId);
			result = prepare.executeQuery();
			while(result.next()){
				BackupConstruction backupConstruction = new BackupConstruction(
						result.getInt("id"),
						result.getInt("x"),
						result.getInt("y"),
						result.getInt("nbSalarie"),
						result.getInt("nbCadre"),
						result.getInt("risque"),
						result.getInt("budget"),
						result.getInt("attractivite"),
						result.getInt("postePourvu"),
						result.getString("specificites"));
				Construction construction = new Construction(result.getInt("construction"));
				Backup backup = new Backup(backupId);
				backupConstruction.setConstruction(construction);
				backupConstruction.setBackup(backup);
				backupConstructionsByBackup.add(backupConstruction);
			}
			return backupConstructionsByBackup ;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

}
