package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Budget;



public class BudgetDAO extends DAO<Budget,Integer>{

	@Override
	public Budget get(Integer id) {
		ResultSet result;
		Budget budget;
		Backup backup;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * From budget where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();

			while(result.next()){
				budget = new Budget();
				budget.setId(result.getInt("id"));
				budget.setAgeRetraite(result.getInt("ageRetraite"));
				budget.setChargeSalariale(result.getInt("chargeSalariale"));
				budget.setChargeCadre(result.getInt("chargeCadre"));
				budget.setSalaireStandard(result.getInt("SalaireStandard"));
				budget.setSalaireCadre(result.getInt("SalaireCadre"));
				budget.setNbSalaries(result.getInt("nbSalaries"));
				budget.setNbCadres(result.getInt("nb"));
				backup = new Backup(result.getInt("backup"));
				budget.setBackup(backup);
				return budget;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Budget save(Budget element) {
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
					+"backup)"
					+"VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, element.getId());
			statement.setInt(2, element.getAgeTravail());
			statement.setInt(3, element.getAgeRetraite());
			statement.setInt(4, element.getChargeSalariale());
			statement.setInt(5, element.getChargeCadre());
			statement.setInt(6, element.getSalaireStandard());
			statement.setInt(6, element.getSalaireCadre());
			statement.setInt(7, element.getNbSalaries());
			statement.setInt(8, element.getNbCadres());
			statement.setInt(9, element.getBackup().getId());
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public void update(Budget element) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Budget> getAll() {
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
				budget.setNbCadres(result.getInt("nb"));
				Backup backup = new Backup(result.getInt("backup"));
				budget.setBackup(backup);
				budgets.add(budget);
			}
			return budgets;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

	public List<Budget> getAllByBackup(Backup backup) {
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
				budget.setBackup(backup);
				budgetsByBackup.add(budget);
			}
			return budgetsByBackup;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}











}