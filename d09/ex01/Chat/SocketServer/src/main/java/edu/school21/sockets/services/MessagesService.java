package edu.school21.sockets.services;

import edu.school21.sockets.models.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessagesService {
    void sendMessage(Message message) throws SQLException;
    List<Message> getMessages() throws SQLException;
}
