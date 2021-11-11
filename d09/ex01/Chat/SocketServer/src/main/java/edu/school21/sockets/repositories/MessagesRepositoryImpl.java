package edu.school21.sockets.repositories;

import javax.sql.DataSource;

import edu.school21.sockets.models.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryImpl implements MessagesRepository<Message> {
    private JdbcTemplate jdbcTemplate;

    public MessagesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Message> MessageRowMapper = (resultSet, rowNum) -> {
        return new Message(resultSet.getLong("id"), resultSet.getString("author"), resultSet.getString("text"), resultSet.getTimestamp("date"));
    };

    @Override
    public Optional<Message> findByAuthor(String author) {
        List<Message> MessageList = jdbcTemplate.query("SELECT * FROM Messages WHERE author = ?", MessageRowMapper, author);

        if (MessageList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(MessageList.get(0));
        }
    }

    @Override
    public Message findById(Long id) {
        List<Message> MessageList = jdbcTemplate.query("SELECT * FROM Messages WHERE id = ?", MessageRowMapper, id);

        if (MessageList.isEmpty()) {
            return null;
        } else {
            return MessageList.get(0);
        }
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("SELECT * FROM Messages", MessageRowMapper);
    }

    @Override
    public void save(Message entry) {
        jdbcTemplate.update("INSERT INTO Messages (author, text, date) VALUES (?, ?, ?)", entry.getAuthor(), entry.getText(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public void update(Message entry) {
        jdbcTemplate.update("UPDATE Messages SET (author = ?, text = ?, date = ?) WHERE id = ?", entry.getAuthor(), entry.getText(), entry.getId(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM Messages WHERE id = ?", id);
    }
}
