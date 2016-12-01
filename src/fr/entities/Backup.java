package fr.entities;

public class Backup extends AbstractEntity<Backup> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String date_creation;
	private String date_last;
	private Integer nbj;
	private User user;
	private Integer budget;

	public Backup() {
	}

	public Backup(Integer id) {
		this(id, null, null, null, null,null);
	}

	public Backup(Integer id, String date_creation, String date_last, Integer nbj, User user, Integer budget) {
		super(id);
		this.date_creation = date_creation;
		this.date_last = date_last;
		this.nbj = nbj;
		this.user = user;
		this.budget=budget;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public String getDate_creation() {
		return this.date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public String getDate_last() {
		return this.date_last;
	}

	public void setDate_last(String date_last) {
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

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
