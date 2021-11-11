package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.repositories.MessagesRepositoryImpl;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;
import java.sql.SQLException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
@ComponentScan("edu.school21.sockets")
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver.name}")
    private String driverName;

    @Bean
    public HikariDataSource dataSource() throws SQLException, IOException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverName);

		try {
            Connection connection = dataSource.getConnection();
			String schema = null;
			try {
				schema = new String(Files.readAllBytes(Paths.get("./src/main/resources/schema.sql")));
			} catch ( IOException e) {
				e.printStackTrace();
			}
			Statement statement = null;
        	try {
            	statement = connection.createStatement();
            	statement.executeUpdate(schema);
       
        	} catch (SQLException throwables) {
            	throwables.printStackTrace();
        	}
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
		
        return dataSource;
    }

    @Bean
    public UsersRepositoryImpl usersRepository() throws SQLException, IOException {
        return new UsersRepositoryImpl(dataSource());
    }

    @Bean
    public MessagesRepositoryImpl messagesRepository() throws SQLException, IOException {
        return new MessagesRepositoryImpl(dataSource());
    }
}