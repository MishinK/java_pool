package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private HikariConfig config;
    private HikariDataSource ds;

    public DataSource() {
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/java_db");
        config.setUsername("ashea");
        config.setPassword("123");
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void close() {
        if (ds != null) {
            ds.close();
        }
    }
}