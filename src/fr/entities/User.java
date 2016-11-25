package fr.entities;

public class User extends AbstractEntity<User> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String login;
	private String password;

	public User(){
		this(null, null, null);
	}

	public User(Integer id){
		this(id, null, null);
	}

	public User(Integer id, String login, String password) {
		super(id);
		this.login = login;
		this.password = password;
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
