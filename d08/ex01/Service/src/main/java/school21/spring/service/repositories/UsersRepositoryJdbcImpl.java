package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource          dataSource;
    private Connection          connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
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

    @Override
    public User findById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM emails WHERE userid = " + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return user;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Long identifier = 0L;
        User userToAdd = null;
        while ((userToAdd = findById(++identifier)) != null) {
            list.add(userToAdd);
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("INSERT INTO emails(email) values('%s');", entity.getEmail()));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE emails SET " + "email = " + '\'' + entity.getEmail() + '\'' + " WHERE userid = " + entity.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM emails WHERE userid = " + id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM emails WHERE email = \'" + email + "\'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return Optional.of(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}