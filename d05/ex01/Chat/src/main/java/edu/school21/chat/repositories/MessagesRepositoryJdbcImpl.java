package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.Optional;
import java.time.LocalDateTime;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource dataSource;

    private void closeConnections(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        dataSource.close();
        rs.close();
        ps.close();
        conn.close();
    }

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Message> findById(Long massage_id) throws SQLException {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM chat.Message WHERE  id = " + massage_id);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                return null;
            }

			Long user_id = rs.getLong(2);
			Long chatroom_id = rs.getLong(3);
			String text = rs.getString(4);
			LocalDateTime dateTime = rs.getTimestamp(5).toLocalDateTime();
           
            prepStmt = connection.prepareStatement("SELECT * FROM chat.User WHERE id = " + user_id);
            rs = prepStmt.executeQuery();
			if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                return null;
            }

			User author = new User(rs.getLong(1), rs.getString(2), rs.getString(3));

            prepStmt = connection.prepareStatement("SELECT * FROM chat.Chatroom WHERE id = " + chatroom_id);
            rs = prepStmt.executeQuery();
			if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                return null;
            }
			String room_name = rs.getString(2);
			Long owner_id = rs.getLong(3);

			prepStmt = connection.prepareStatement("SELECT * FROM chat.User WHERE id = " + owner_id);
            rs = prepStmt.executeQuery();
			if (rs.next() == false) {
                closeConnections(rs, prepStmt, connection);
                return null;
            }

			User owner = new User(rs.getLong(1), rs.getString(2), rs.getString(3));

            Chatroom room = new Chatroom(chatroom_id, room_name, owner);
            
			closeConnections(rs, prepStmt, connection);

			Message message = new Message(massage_id, author, room, text, dateTime);

            return Optional.of(message);
        } catch (SQLException e) {
            e.getMessage();
        }
        return Optional.empty();
    }
}