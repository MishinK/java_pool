package edu.school21.chat.app;
import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import edu.school21.chat.repositories.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

public class Program {
    public static void main(String[] args) throws SQLException {
		DataSource dataSource = new DataSource();
		Connection connection = dataSource.getConnection();
		String data = null;
		String schema = null;
		try {
			data = new String(Files.readAllBytes(Paths.get("./src/main/resources/data.sql")));
			schema = new String(Files.readAllBytes(Paths.get("./src/main/resources/schema.sql")));
		} catch ( IOException e) {
			e.printStackTrace();
		}
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(schema);
			statement.executeUpdate(data);
   
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		statement.close();
		connection.close();

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        List<User> users = usersRepository.findAll(0, 2);
        users.forEach(System.out::println);
    }
}