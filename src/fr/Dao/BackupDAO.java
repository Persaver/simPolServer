package fr.Dao;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.entities.Backup;
import fr.entities.Categorie;
import fr.entities.Construction;

public class BackupDAO extends DAO<Backup,Integer>{

	@Override
	public Backup get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Backup element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Backup element) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Backup> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Backup> getByUser(int id){
		return null;
		// TODO Auto-generated method stub

	}
}


