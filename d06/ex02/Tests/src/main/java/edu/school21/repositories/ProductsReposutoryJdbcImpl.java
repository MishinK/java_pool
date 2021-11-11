package edu.school21.repositories;

import edu.school21.models.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsReposutoryJdbcImpl implements ProductsRepository {
    
	private final Connection connection;

    public ProductsReposutoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() throws SQLException {
		List<Product> products = new LinkedList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Product.Product;");
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next())
            products.add(new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price")));
        ps.close();
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Product product = null;
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Product.Product WHERE id = ?;");
        ps.setObject(1, id);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        if (rs.next())
            product = new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price"));
        ps.close();
        return Optional.ofNullable(product);
    }

    @Override
    public void save(Product product) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Product.Product (name, price) VALUES (?, ?);");
        ps.setObject(1, product.getName());
        ps.setObject(2, product.getPrice());
        ps.execute();
        ResultSet rs = connection.createStatement().executeQuery("CALL IDENTITY();");
        if (rs.next())
            product.setId(rs.getLong(1));
        ps.close();
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE Product.Product SET name = ?, price = ? WHERE id = ?;");
        ps.setObject(1, product.getName());
        ps.setObject(2, product.getPrice());
        ps.setObject(3, product.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM Product.Product WHERE id = ?;");
        ps.setObject(1, id);
        ps.execute();
        ps.close();
    }
}