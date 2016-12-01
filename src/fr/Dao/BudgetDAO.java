package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Budget;
import fr.splExceptions.DAOException;



public class BudgetDAO extends DAO<Budget,Integer>{

	@Override
	public Budget get(Integer id) throws DAOException {
		ResultSet result;
		Budget budget = new Budget();
		Backup backup;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * From budget where id = ?");
			prepare.setInt(1, id.intValue());
			result = prepare.executeQuery();

			if((result!= null) && result.next()){
				budget = new Budget();
				budget.setId(result.getInt("id"));
				budget.setAgeRetraite(result.getInt("ageRetraite"));
				budget.setChargeSalariale(result.getInt("chargeSalariale"));
				budget.setChargeCadre(result.getInt("chargeCadre"));
				budget.setSalaireStandard(result.getInt("SalaireStandard"));
				budget.setSalaireCadre(result.getInt("SalaireCadre"));
				budget.setNbSalaries(result.getInt("nbSalaries"));
				budget.setNbCadres(result.getInt("nbCadres"));
				budget.setNbj(result.getInt("nbj"));
				backup = new Backup(result.getInt("backup"));
				budget.setBackup(backup);
				return budget;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	public Budget getByBackup(Backup backup) throws DAOException {
		ResultSet result;
		Budget budget = new Budget();
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * From budget where backup = ?");
			prepare.setInt(1, backup.getId());
			result = prepare.executeQuery();

			if((result!= null) && result.next()){
				result.last();
				budget = new Budget();
				budget.setId(result.getInt("id"));
				budget.setAgeRetraite(result.getInt("ageRetraite"));
				budget.setChargeSalariale(result.getInt("chargeSalariale"));
				budget.setChargeCadre(result.getInt("chargeCadre"));
				budget.setSalaireStandard(result.getInt("SalaireStandard"));
				budget.setSalaireCadre(result.getInt("SalaireCadre"));
				budget.setNbSalaries(result.getInt("nbSalaries"));
				budget.setNbCadres(result.getInt("nbCadres"));
				budget.setNbj(result.getInt("nbj"));
				budget.setBackup(backup);
				return budget;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	@Override
	public Budget save(Budget budget) throws DAOException {
		try {
			String sql = "INSERT INTO Budget ("
					+"ageTravail,"
					+"ageRetraite,"
					+"chargeSalariale, "
					+"chargeCadre, "
					+"salaireStandard,"
					+"salaireCadre,"
					+"nbSalaries,"
					+"nbCadres,"
					+"nbj,"
					+"backup)"
					+"VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, budget.getId());
			statement.setInt(2, budget.getAgeTravail());
			statement.setInt(3, budget.getAgeRetraite());
			statement.setInt(4, budget.getChargeSalariale());
			statement.setInt(5, budget.getChargeCadre());
			statement.setInt(6, budget.getSalaireStandard());
			statement.setInt(6, budget.getSalaireCadre());
			statement.setInt(7, budget.getNbSalaries());
			statement.setInt(8, budget.getNbCadres());
			statement.setInt(9, budget.getNbj());
			statement.setInt(10, budget.getBackup().getId());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.first()) {
				budget.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating message failed, no ID obtained.");
			}
			statement.close();
			generatedKeys.close();
			return budget;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public Budget update(Budget budget) {

		return null;
	}

	@Override
	public List<Budget> getAll() throws DAOException {
		ResultSet result;
		List<Budget> budgets = new ArrayList<Budget>();
		try {
			String sql = "Select * from budget";
			result = this.connect.createStatement().executeQuery(sql);
			while(result.next()){
				Budget budget = new Budget();
				budget.setId(result.getInt("id"));
				budget.setAgeRetraite(result.getInt("ageRetraite"));
				budget.setChargeSalariale(result.getInt("chargeSalariale"));
				budget.setChargeCadre(result.getInt("chargeCadre"));
				budget.setSalaireStandard(result.getInt("SalaireStandard"));
				budget.setSalaireCadre(result.getInt("SalaireCadre"));
				budget.setNbSalaries(result.getInt("nbSalaries"));
				budget.setNbCadres(result.getInt("nbCadres"));
				budget.setNbj(result.getInt("nbj"));
				Backup backup = new Backup(result.getInt("backup"));
				budget.setBackup(backup);
				budgets.add(budget);
			}
			return budgets;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	}

	public List<Budget> getAllByBackup(Backup backup) throws DAOException {
		ResultSet result;
		List<Budget> budgetsByBackup = new ArrayList<Budget>();
		try {
			String sql= "Select * From budget Where backup = ? ";
			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.setInt(1, backup.getId());
			result = statement.executeQuery();
			while(result.next()){
				Budget budget = new Budget();
				budget.setId(result.getInt("id"));
				budget.setAgeRetraite(result.getInt("ageRetraite"));
				budget.setChargeSalariale(result.getInt("chargeSalariale"));
				budget.setChargeCadre(result.getInt("chargeCadre"));
				budget.setSalaireStandard(result.getInt("SalaireStandard"));
				budget.setSalaireCadre(result.getInt("SalaireCadre"));
				budget.setNbSalaries(result.getInt("nbSalaries"));
				budget.setNbCadres(result.getInt("nbCadres"));
				budget.setNbj(result.getInt("nbj"));
				budget.setBackup(backup);
				budgetsByBackup.add(budget);
			}
			return budgetsByBackup;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
	}
}