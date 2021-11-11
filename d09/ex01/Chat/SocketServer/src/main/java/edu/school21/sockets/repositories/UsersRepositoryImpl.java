package edu.school21.sockets.repositories;

import javax.sql.DataSource;

import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository<User> {
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"));
    };

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM Users WHERE username = ?", userRowMapper, username);

        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public User findById(Long id) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM Users WHERE id = ?", userRowMapper, id);

        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM Users", userRowMapper);
    }

    @Override
    public void save(User entry) {
        jdbcTemplate.update("INSERT INTO Users (username, password) VALUES (?, ?)", entry.getUsername(), entry.getPassword());
    }

    @Override
    public void update(User entry) {
        jdbcTemplate.update("UPDATE Users SET username = ? WHERE id = ?", entry.getUsername(), entry.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM Users WHERE id = ?", id);
    }
}
