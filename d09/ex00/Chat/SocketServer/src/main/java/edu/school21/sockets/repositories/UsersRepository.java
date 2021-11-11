package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;

import java.util.Optional;

public interface UsersRepository<T> extends CrudRepository<User> {
    Optional<T> findByUsername(String username);
}
