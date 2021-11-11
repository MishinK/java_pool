package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long  id;
    private String login;
    private String password;
    private List<Chatroom> chatrooms;
    private List<Chatroom> createdrooms;

    public User(Long id, String login, String password){
		this.id = id;
		this.login = login;
        this.password = password;
		this.chatrooms = null;
		this.createdrooms = null;
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Chatroom> getChatrooms() {
        return this.chatrooms;
    }

    public List<Chatroom> getCreatedrooms() {
        return this.createdrooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
		return id.equals(user.id) && login.equals(user.login) && password.equals(user.password);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", chatrooms=" + chatrooms +
                ", createdrooms=" + createdrooms +
                '}';
    }
}
