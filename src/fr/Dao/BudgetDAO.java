package fr.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Budget;
import fr.interfaces.IEntity;



public class BudgetDAO extends DAO<Budget,Integer>{


	@Override
	public Budget get(Integer id) {

		ResultSet result;
		try {
			PreparedStatement prepare = this.connect.prepareStatement("Select * From budget where id = ?");
			prepare.setInt(1, id);
			result = prepare.executeQuery();

			while(result.next()){
				Budget budget=new Budget();
				budget.setId(result.getInt("id"));
				budget.setAge(result.getInt("age"));
				budget.setAgeRetraite(result.getInt("ageRetraite"));
				budget.setChargeSalariale(result.getInt("chargeSalariale"));
				budget.setChargeCadre(result.getInt("chargeCadre"));
				budget.setSalaireStandard(result.getInt("SalaireStandard"));
				budget.setSalaireCadre(result.getInt("SalaireCadre"));
				budget.setNbSalaries(result.getInt("nbSalaries"));
				budget.setNbCadres(result.getInt("nb"));
				budget.setBackup(result.getInt("Backup"));
				budget.setDate(result.getDate("date"));
				return budget;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public void save(Budget element) {
		// TODO Auto-generated method stub
		ResultSet result;


		try {
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Budget (id,ageTravail,ageRetraite,chargeSalariale,chargeCadre,salaireStandard,salaireCadre,nbSalaries,nbCadres,backup,date) VALUES (?,?,?,?,?,?,?,?,?,?,?,)");
			prepare.setInt(1, element.getId());
			prepare.setInt(2, element.getAgeTravail());
			prepare.setInt(3, element.getAgeRetraite());
			prepare.setInt(4, element.getChargeSalariale());
			prepare.setInt(5, element.getChargeCadre());
			prepare.setInt(6, element.getSalaireStandard());
			prepare.setInt(6, element.getSalaireCadre());
			prepare.setInt(7, element.getNbSalaries());
			prepare.setInt(8, element.getNbCadres());
			prepare.setInt(9, element.getBackup());
			prepare.setDate(10, element.getDate());
			result = prepare.executeQuery();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

		ResultSet result;
		PreparedStatement prepare;

		try {
			prepare = this.connect.prepareStatement("DELETE FROM budget where id= ? ");
			prepare.setInt(1, id);
			result = prepare.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void update(Budget element) {
		// TODO Auto-generated method stub


	}

	@Override
	public List<Budget> getAll() {
		// TODO Auto-generated method stub
		//creation de liste de buget
		List<Budget> list = new ArrayList<Budget>();
		ResultSet result;
		PreparedStatement prepare;
		//prepare = this.connect.prepareStatement("select * from budget ;");
		//prepare.setInt(1, backup);
		 
		 
		//select * from budget where idbackup=?,
		
		return null;
	
	}
	
	
	
	public List<IEntity> getAllByBackup(Integer backup) {
		// TODO Auto-generated method stub
		//creation de liste de buget
		List<IEntity> list = new ArrayList<IEntity>();
		ResultSet result;
		PreparedStatement prepare;
		try {
			prepare = this.connect.prepareStatement("select * from budget where backup= ? ");
			prepare.setInt(1, backup);
			result = prepare.executeQuery();
			Budget myBudget=new Budget();
			
			while(result.next()){
				
				myBudget.setId(result.getInt("id"));
				myBudget.setAge(result.getInt("age"));
				myBudget.setAgeRetraite(result.getInt("ageRetraite"));
				myBudget.setChargeSalariale(result.getInt("chargeSalariale"));
				myBudget.setChargeCadre(result.getInt("chargeCadre"));
				myBudget.setSalaireStandard(result.getInt("SalaireStandard"));
				myBudget.setSalaireCadre(result.getInt("SalaireCadre"));
				myBudget.setNbSalaries(result.getInt("nbSalaries"));
				myBudget.setNbCadres(result.getInt("nb"));
				myBudget.setBackup(result.getInt("Backup"));
				myBudget.setDate(result.getDate("date"));
				list.add(myBudget);
		}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	
	}











}