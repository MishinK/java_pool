package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import java.sql.SQLException;
import java.util.Optional;

public interface UsersService {
    boolean signUp(String username, String password) throws SQLException;
    boolean signIn(String username, String password) throws SQLException;
}