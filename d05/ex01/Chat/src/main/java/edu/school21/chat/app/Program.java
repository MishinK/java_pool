package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        try {
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

			MessagesRepository mess = new MessagesRepositoryJdbcImpl(dataSource);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter message id\n-> ");
            Long id = scanner.nextLong();

            Optional<Message> message = mess.findById(id);
            try {
                System.out.println(message.get().toString());
            } catch (Exception e) {
                System.err.println("null optional returned");
            }
			scanner.close();
        }
        catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
    }
}