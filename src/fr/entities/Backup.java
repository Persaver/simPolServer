package fr.entities;

import java.io.Serializable;
import java.util.Date;

public class Backup extends AbstractEntity<Backup> implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date_creation;
	private Date date_last;
	private Integer nbj;

	private User user;

	public Backup() {
		super();
	}

	public Backup(Integer id) {
		this.id=id;
	}

	public Backup(Integer id, Date date_creation, Date date_last, Integer nbj) {
		super(id);
		this.date_creation = date_creation;
		this.date_last = date_last;
		this.nbj = nbj;
	}

	public Backup(Date date_creation, Date date_last, User user) {
		this.date_creation = date_creation;
		this.date_last = date_last;
		this.user = user;
	}

	public Backup(Integer id, Date date_creation, Date date_last, User user) {
		this.id = id;
		this.date_creation = date_creation;
		this.date_last = date_last;
		this.user = user;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate_creation() {
		return this.date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public Date getDate_last() {
		return this.date_last;
	}

	public void setDate_last(Date date_last) {
		this.date_last = date_last;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getNbj() {
		return this.nbj;
	}

	public void setNbj(Integer nbj) {
		this.nbj = nbj;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Backup [id=");
		builder.append(this.id);
		builder.append(", date_creation=");
		builder.append(this.date_creation);
		builder.append(", date_last=");
		builder.append(this.date_last);
		builder.append(", nbj=");
		builder.append(this.nbj);
		builder.append(", user=");
		builder.append(this.user);
		builder.append("]");
		return builder.toString();
	}



}
