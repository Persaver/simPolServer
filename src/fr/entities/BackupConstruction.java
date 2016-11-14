package fr.entities;

import java.io.Serializable;

public class BackupConstruction extends AbstractEntity<Integer> {
	private int x;
	private int y;
	
	private Construction construction;
	private Backup backup;
		
	public BackupConstruction(Integer id,int x, int y, Construction construction, Backup backup) {
		super(id);
		this.x = x;
		this.y = y;
		this.construction = construction;
		this.backup = backup;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Construction getConstruction() {
		return construction;
	}
	
	public void setConstruction(Construction construction) {
		this.construction = construction;
	}
	
	public Backup getBackup() {
		return backup;
	}
	
	public void setBackup(Backup backup) {
		this.backup = backup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BackupConstruction [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", construction=");
		builder.append(construction);
		builder.append(", backup=");
		builder.append(backup);
		builder.append("]");
		return builder.toString();
	}
	
	
}
