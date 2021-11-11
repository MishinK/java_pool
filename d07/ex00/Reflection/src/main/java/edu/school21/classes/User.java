package edu.school21.classes;

public class User {

	private String login;
    private String password;
	private Integer visits = 0;

    public User() {
        this.login = "root";
        this.password = "toor";
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer userVisit() {
		this.visits++;
		return(this.visits);
	}

    @Override
    public String toString() {
        return "User[" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
				", visits=" + visits +
                ']';
    }
}
