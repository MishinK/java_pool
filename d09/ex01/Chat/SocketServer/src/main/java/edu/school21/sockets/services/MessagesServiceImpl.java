package edu.school21.sockets.services;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {
    MessagesRepository messagesRepository;

    public MessagesServiceImpl() {
    }

    @Autowired
    @Qualifier("messagesRepository")
    public void setMessagesRepository(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public void sendMessage(Message message) throws SQLException {
        messagesRepository.save(message);
    }

    @Override
    public List<Message> getMessages() throws SQLException {
        return messagesRepository.findAll();
    }

}
