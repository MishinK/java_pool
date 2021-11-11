package edu.school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    boolean signUp(String username, String password) throws SQLException;
}