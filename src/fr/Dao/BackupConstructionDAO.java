package fr.Dao;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.entities.Backup;
import fr.entities.BackupConstruction;
import fr.entities.Construction;
import fr.splExceptions.DAOException;



public class BackupConstructionDAO extends DAO<BackupConstruction,Integer> {

	private static final Logger LOG = LogManager.getLogger();
	private final static Integer IDECOLE = 17;
	private final static Integer IDHOPITAL = 6;
	private final static Integer IDCOMMISSARIAT = 5;
	private final static Integer IDCASERNE = 20;


	public static Integer getIdcommissariat() {
		return BackupConstructionDAO.IDCOMMISSARIAT;
	}

	public static Integer getIdcaserne() {
		return BackupConstructionDAO.IDCASERNE;
	}


	@Override
	public BackupConstruction get(Integer id) throws DAOException {
		BackupConstructionDAO.LOG.debug("BackupConstructionDAO get(Integer id) {} ",id);

		ResultSet result;
		BackupConstruction backupConstruction;
		Construction construction;
		Backup backup;
		Gson gson = null;
		try
		{
			PreparedStatement prepare = this.connect.prepareStatement("Select * from backup_construction where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result != null){
				result.first();
				backupConstruction = new BackupConstruction();
				backupConstruction.setId(result.getInt("id"));
				backupConstruction.setX(result.getInt("x"));
				backupConstruction.setY(result.getInt("y"));
				backupConstruction.setNbSalarie(result.getInt("nbSalarie"));
				backupConstruction.setNbCadre(result.getInt("nbCadre"));
				backupConstruction.setRisque(result.getInt("risque"));
				backupConstruction.setBudget(result.getInt("budget"));
				backupConstruction.setAttractivite(result.getInt("attractivite"));
				backupConstruction.setPostePourvu(result.getInt("postePourvu"));
				backupConstruction.setNiveau(result.getInt("niveau"));
				// gson
				gson = new Gson();
				// on recupere le type de la Map pour Gson
				Type stringIntegerMap = new TypeToken<Map<String,Integer>>(){}.getType();
				backupConstruction.setSpecificite(gson.fromJson(result.getString("specificite"), stringIntegerMap));
				BackupConstructionDAO.LOG.debug("BackupConstructionDAO setSpecificite  {} ",result.getString("specificite"));
				construction = new Construction(result.getInt("construction"));
				backup = new Backup(result.getInt("backup"));
				backupConstruction.setConstruction(construction);
				backupConstruction.setBackup(backup);
				BackupConstructionDAO.LOG.debug("BackupConstructionDAO get(Integer id {} return {}",id,backupConstruction != null ? backupConstruction.getId() : "null");
				return backupConstruction;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	public static Integer getIDHOPITAL() {
		return BackupConstructionDAO.IDHOPITAL;
	}


	public static Integer getIDECOLE() {
		return BackupConstructionDAO.IDECOLE;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public BackupConstruction save(BackupConstruction element) throws DAOException {
		BackupConstructionDAO.LOG.debug("BackupConstructionDAO save(Integer id) {} ",element.getId());
		Gson gson = new Gson();
		try {
			String sql = "INSERT INTO backup_construction ("
					+"x,"
					+"y,"
					+"nbSalarie,"
					+"nbCadre,"
					+"risque,"
					+"budget,"
					+"attractivite,"
					+"postePourvu,"
					+"specificite,"
					+"construction,"
					+"backup) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			statement.setInt(1, element.getX());
			statement.setInt(2, element.getY());
			statement.setInt(3, element.getNbSalarie());
			statement.setInt(4, element.getNbCadre());
			statement.setInt(5, element.getRisque());
			statement.setInt(6, element.getBudget());
			statement.setInt(7, element.getAttractivite());
			statement.setInt(8, element.getPostePourvu());
			statement.setInt(9,  element.getNiveau());
			// gson au boulot on json tt ca
			statement.setString(9, gson.toJson(element.getSpecificite()));
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
			BackupConstructionDAO.LOG.debug("BackupConstructionDAO save(Integer id) {} return {}",element != null ? element.getId() : "null");

			return element;
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public BackupConstruction update(BackupConstruction element) throws DAOException {
		BackupConstructionDAO.LOG.debug("BackupConstructionDAO update(Integer id) {} ",element.getId());

		Gson gson = null;
		Integer cptRow = null;
		try {
			String sql = "Update backup_construction "
					+ "Set "
					+"x=?, "
					+"y=?, "
					+"nbSalarie=?, "
					+"nbCadre=?, "
					+"risque=?, "
					+"budget=?, "
					+"attractivite=?, "
					+"postePourvu=?, "
					+"specificite=?, "
					+"construction=?, "
					+"backup = ? ,"
					+"niveau = ?"
					+"WHERE id= ?";
			PreparedStatement statement = this.connect.prepareStatement( sql);
			statement.setInt(1, element.getX());
			statement.setInt(2, element.getY());
			statement.setInt(3, element.getNbSalarie());
			statement.setInt(4, element.getNbCadre());
			statement.setInt(5, element.getRisque());
			statement.setInt(6, element.getBudget());
			statement.setInt(7, element.getAttractivite());
			statement.setInt(8, element.getPostePourvu());
			statement.setInt(9,  element.getNiveau());
			// gson au boulot on json tt ca
			gson = new Gson();
			statement.setString(9, gson.toJson(element.getSpecificite()));
			statement.setInt(10, element.getConstruction().getId());
			statement.setInt(11, element.getBackup().getId());
			cptRow = statement.executeUpdate();
			if ((cptRow == null) || (cptRow < 1)){
				throw new SQLException("Creating message failed, no ID obtained.");
			}
			BackupConstructionDAO.LOG.debug("BackupConstructionDAO update(Integer id) {} return {}",element != null ? element.getId() : "null");
			statement.close();
			return element;
		}catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<BackupConstruction> getAll() throws DAOException {
		ResultSet result;
		List<BackupConstruction> backupConstructions = new ArrayList<BackupConstruction>();
		try {
			result = this.connect.createStatement().executeQuery("Select * from backup_construction");
			while(result.next()){
				BackupConstruction backupConstruction = new BackupConstruction();
				backupConstruction.setId(result.getInt("id"));
				backupConstruction.setX(result.getInt("x"));
				backupConstruction.setY(result.getInt("y"));
				backupConstruction.setNbSalarie(result.getInt("nbSalarie"));
				backupConstruction.setNbCadre(result.getInt("nbCadre"));
				backupConstruction.setRisque(result.getInt("risque"));
				backupConstruction.setBudget(result.getInt("budget"));
				backupConstruction.setAttractivite(result.getInt("attractivite"));
				backupConstruction.setPostePourvu(result.getInt("postePourvu"));
				backupConstruction.setNiveau(result.getInt("niveau"));
				// gson
				Gson gson = new Gson();
				Type stringIntegerMap = new TypeToken<Map<String,Integer>>(){}.getType();
				backupConstruction.setSpecificite(gson.fromJson(result.getString("specificite"), stringIntegerMap));
				BackupConstructionDAO.LOG.debug("BackupConstructionDAO setSpecificite  {} ",result.getString("specificite"));
				Construction construction = new Construction(result.getInt("construction"));
				Backup backup = new Backup(result.getInt("backup"));
				backupConstruction.setConstruction(construction);
				backupConstruction.setBackup(backup);
				backupConstructions.add(backupConstruction);
			}

			return backupConstructions ;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	}

	public List<BackupConstruction> getAllByBackUp(Backup backup) throws DAOException {
		ResultSet result;
		List<BackupConstruction> backupConstructionsByBackup = new ArrayList<BackupConstruction>();
		BackupConstructionDAO.LOG.debug("BackupConstructionDAO getAllByBackUp(Backup backup) {} ",backup.getId());

		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from backup_construction where backup = ?");
			prepare.setInt(1, backup.getId());
			result = prepare.executeQuery();
			while(result.next()){
				BackupConstruction backupConstruction = new BackupConstruction();
				backupConstruction.setId(result.getInt("id"));
				backupConstruction.setX(result.getInt("x"));
				backupConstruction.setY(result.getInt("y"));
				backupConstruction.setNbSalarie(result.getInt("nbSalarie"));
				backupConstruction.setNbCadre(result.getInt("nbCadre"));
				backupConstruction.setRisque(result.getInt("risque"));
				backupConstruction.setBudget(result.getInt("budget"));
				backupConstruction.setAttractivite(result.getInt("attractivite"));
				backupConstruction.setPostePourvu(result.getInt("postePourvu"));
				backupConstruction.setNiveau(result.getInt("niveau"));
				// gson
				Gson gson = new Gson();
				Type stringIntegerMap = new TypeToken<Map<String,Integer>>(){}.getType();
				backupConstruction.setSpecificite(gson.fromJson(result.getString("specificite"), stringIntegerMap));
				BackupConstructionDAO.LOG.debug("BackupConstructionDAO setSpecificite  {} ",result.getString("specificite"));
				Construction construction = new Construction(result.getInt("construction"));
				backupConstruction.setConstruction(construction);
				backupConstruction.setBackup(backup);
				backupConstructionsByBackup.add(backupConstruction);
			}
			LOG.debug("BackupConstructionDAO getAllByBackUp(Backup backup) {} return {}",backup.getId(), backupConstructionsByBackup != null ? backupConstructionsByBackup.size() : "null");
			return backupConstructionsByBackup;
		}catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	public List<BackupConstruction> getAllByBackUpByConstruction(Backup backup, Integer idConstruction) throws DAOException {
		ResultSet result;
		List<BackupConstruction> backupConstructionsByBackup = new ArrayList<BackupConstruction>();
		BackupConstructionDAO.LOG.debug("BackupConstructionDAO getAllByBackUpByConstruction(Backup backup, Integer idConstruction) {}  {} ",backup.getId(),idConstruction);

		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * from backup_construction where backup = ? and construction=?");
			prepare.setInt(1, backup.getId());
			prepare.setInt(2, idConstruction);
			result = prepare.executeQuery();
			while(result.next()){
				BackupConstruction backupConstruction = new BackupConstruction();
				backupConstruction.setId(result.getInt("id"));
				backupConstruction.setX(result.getInt("x"));
				backupConstruction.setY(result.getInt("y"));
				backupConstruction.setNbSalarie(result.getInt("nbSalarie"));
				backupConstruction.setNbCadre(result.getInt("nbCadre"));
				backupConstruction.setRisque(result.getInt("risque"));
				backupConstruction.setBudget(result.getInt("budget"));
				backupConstruction.setAttractivite(result.getInt("attractivite"));
				backupConstruction.setPostePourvu(result.getInt("postePourvu"));
				backupConstruction.setNiveau(result.getInt("niveau"));
				// gson
				Gson gson = new Gson();
				Type stringIntegerMap = new TypeToken<Map<String,Integer>>(){}.getType();
				backupConstruction.setSpecificite(gson.fromJson(result.getString("specificite"), stringIntegerMap));
				BackupConstructionDAO.LOG.debug("BackupConstructionDAO id {} result.getString(\"specificite\")  {} ",backupConstruction.getId(), result.getString("specificite"));
				BackupConstructionDAO.LOG.debug("BackupConstructionDAO id {} getSpecificite  {} ",backupConstruction.getId(), backupConstruction.getSpecificite());
				Construction construction = new Construction(result.getInt("construction"));
				backupConstruction.setConstruction(construction);
				backupConstruction.setBackup(backup);
				backupConstructionsByBackup.add(backupConstruction);
			}
			BackupConstructionDAO.LOG.debug("BackupConstructionDAO getAllByBackUpByConstruction(Backup backup, Integer idConstruction) {}  {} return {}",backup.getId(),idConstruction, backupConstructionsByBackup != null ? backupConstructionsByBackup.size() : "null");
			return backupConstructionsByBackup;
		}catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}
}
