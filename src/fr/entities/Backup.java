package fr.entities;

import java.io.Serializable;
import java.util.Date;

public class Backup implements Serializable{
	private int id;
	private Date date_creation;
	private Date date_last;
	
	private User user;

	public Backup() {
	}

	public Backup(Date date_creation, Date date_last, User user) {
		this.date_creation = date_creation;
		this.date_last = date_last;
		this.user = user;
	}

	public Backup(int id, Date date_creation, Date date_last, User user) {
		this.id = id;
		this.date_creation = date_creation;
		this.date_last = date_last;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public Date getDate_last() {
		return date_last;
	}

	public void setDate_last(Date date_last) {
		this.date_last = date_last;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Backup [id=");
		builder.append(id);
		builder.append(", date_creation=");
		builder.append(date_creation);
		builder.append(", date_last=");
		builder.append(date_last);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
	
	
}
