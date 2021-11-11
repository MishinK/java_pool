package edu.school21.repositories;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class EmbeddedDataSourceTest {

    DataSource dataSource;

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    public void getConnectionTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }
}