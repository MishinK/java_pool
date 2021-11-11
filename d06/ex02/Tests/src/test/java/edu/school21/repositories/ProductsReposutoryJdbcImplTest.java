package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS;
    {
        Product product1 = new Product(1L, "product1", 1);
        Product product2 = new Product(2L, "product2", 20);
        Product product3 = new Product(3L, "product3", 300);
        Product product4 = new Product(4L, "product4", 4000);
        Product product5 = new Product(5L, "product5", 55555);
        EXPECTED_FIND_ALL_PRODUCTS = new LinkedList<>();
        EXPECTED_FIND_ALL_PRODUCTS.add(product1);
        EXPECTED_FIND_ALL_PRODUCTS.add(product2);
        EXPECTED_FIND_ALL_PRODUCTS.add(product3);
        EXPECTED_FIND_ALL_PRODUCTS.add(product4);
        EXPECTED_FIND_ALL_PRODUCTS.add(product5);
    }

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "product2", 20);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "kek", 1000);
    final Product EXPECTED_SAVED_PRODUCT = new Product(null, "lol", 0);

    private Connection connection;
    private ProductsRepository repository;

    @BeforeEach
    public void setConnection() throws SQLException {
        this.connection = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build().getConnection();
        this.repository = new ProductsReposutoryJdbcImpl(this.connection);
    }

    @AfterEach
    public void shutdownConnection() throws SQLException {
        this.connection.close();
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Product> result = repository.findAll();
        Assertions.assertIterableEquals(EXPECTED_FIND_ALL_PRODUCTS, result);
    }

    @Test
    public void testValidFindById() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(2L).orElse(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 6, 20, 10000})
    public void testInvalidFindById(long id) throws SQLException {
        Assertions.assertFalse(repository.findById(id).isPresent());
    }

    @Test
    public void testUpdate() throws SQLException {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getId()).orElse(null));
    }

    @Test
    public void testSave() throws SQLException {
        repository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(EXPECTED_SAVED_PRODUCT.getId()).orElse(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testDelete(long id) throws SQLException {
        Assertions.assertTrue(repository.findById(id).isPresent());
        repository.delete(id);
        Assertions.assertFalse(repository.findById(id).isPresent());
    }
}