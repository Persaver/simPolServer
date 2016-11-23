package fr.entities;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String login;
	private String password;

	public User(){

	}



	public User(int id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return this.login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(this.id);
		builder.append(", login=");
		builder.append(this.login);
		builder.append(", password=");
		builder.append(this.password);
		builder.append("]");
		return builder.toString();
	}

}