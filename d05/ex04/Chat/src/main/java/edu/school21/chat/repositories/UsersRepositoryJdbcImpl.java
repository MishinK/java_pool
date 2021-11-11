package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String SELECT_ALL_USERS = "SELECT u.id  as u_id,\n" +
            "u.login    as u_login,\n" +
            "u.password as u_password,\n" +
            "c.id      as c_id,\n" +
            "c.name    as c_name,\n" +
            "c.owner   as c_owner\n" +
            "FROM chat.User u\n" +
            "LEFT JOIN chat.User_chatrooms uc on u.id = uc.user_id\n" +
            "LEFT JOIN chat.Chatroom c on u.id = c.owner or c.id = uc.room_id\n" +
            "ORDER by u.id";

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findAll(int page, int size) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
        ResultSet rs = statement.executeQuery();
        List<User> users = new LinkedList<>();
        if (rs.next()) {
            while (!rs.isAfterLast()) {
                long id = rs.getLong("u_id");
                String login = rs.getString("u_login");
                String password = rs.getString("u_password");
                List<Chatroom> ownedRooms = new LinkedList<>();
                List<Chatroom> inRooms = new LinkedList<>();
                processChatrooms(rs, id, ownedRooms, inRooms);
                users.add(new User(id, login, password, ownedRooms, inRooms));
            }
        }
        return users.subList(size * page, size * (page + 1));
    }

	private void processChatrooms(ResultSet rs, long userId, List<Chatroom> ownedRooms, List<Chatroom> inRooms) throws SQLException {
		do {
			long owner = rs.getLong("c_owner");
			Chatroom chatroom = new Chatroom(rs.getLong("c_id"), rs.getString("c_name"), null, null);
			inRooms.add(chatroom);
			if (owner == userId)
				ownedRooms.add(chatroom);
		} while (rs.next() && rs.getLong("u_id") == userId);
	}
}