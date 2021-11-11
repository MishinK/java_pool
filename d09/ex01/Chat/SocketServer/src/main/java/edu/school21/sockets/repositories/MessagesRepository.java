package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;

import java.util.Optional;

public interface MessagesRepository<T> extends CrudRepository<Message> {
    Optional<T> findByAuthor(String author);
}
