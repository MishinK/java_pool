package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
		try {
            Connection connection = dataSource.getConnection();
			String data = null;
			String schema = null;
			try {
				schema = new String(Files.readAllBytes(Paths.get("./src/main/resources/schema.sql")));
				data = new String(Files.readAllBytes(Paths.get("./src/main/resources/data.sql")));
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        return new User(resultSet.getLong("userid"), resultSet.getString("email"));
    };

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM emails WHERE email = ?", userRowMapper, email);
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public User findById(Long id) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM emails WHERE userid = ?", userRowMapper, id);

        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM emails", userRowMapper);
    }

    @Override
    public void save(User entry) {
        jdbcTemplate.update("INSERT INTO emails (email) VALUES (?)", entry.getEmail());
    }

    @Override
    public void update(User entry) {
        jdbcTemplate.update("UPDATE emails SET email = ? WHERE userid = ?", entry.getEmail(), entry.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM emails WHERE userid = ?", id);
    }
}