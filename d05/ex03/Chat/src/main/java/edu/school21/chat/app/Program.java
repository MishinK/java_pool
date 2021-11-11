package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;

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

			User creator = new User(1L, "user", "user", new ArrayList(), new ArrayList());
			User author = creator;
			Chatroom room = new Chatroom(1L, "room", creator, new ArrayList());
			Message msg = new Message(null, author, room, "Hello!", Timestamp.valueOf(LocalDateTime.now()));
			
			mess.save(msg);
			System.out.println(mess.findById(msg.getId()).get().toString()); 

			User author_new = new User(2L, "user", "user", new ArrayList(), new ArrayList());
			msg.setAuthor(author_new);
			msg.setText("Bye");
			msg.setDate(null);
			mess.update(msg);
			System.out.println(mess.findById(msg.getId()).get().toString()); 

			User author_new2 = new User(8L, "user", "user", new ArrayList(), new ArrayList());
			msg.setAuthor(author_new2);
			msg.setText("Bye2");
			msg.setDate(null);
			mess.update(msg);
			System.out.println(mess.findById(msg.getId()).get().toString()); 
		}
			
        catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
    }
}