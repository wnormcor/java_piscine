package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private static final String SELECT_ALL_PRODUCTS = "select\n" +
            "identifier, name, price\n" +
            "from PRODUCT_TABLE\n";
    private static final String SELECT_PRODUCT_BY_ID = SELECT_ALL_PRODUCTS +
            "where identifier = ?";
    private static final String UPDATE_PRODUCT_BY_ID = "update PRODUCT_TABLE\n" +
            "set name = ?, price = ?\n" +
            "where identifier = ?";
    private static final String INSERT_PRODUCT = "insert into PRODUCT_TABLE" +
            "(identifier, name, price) values (?, ?, ?)";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM PRODUCT_TABLE\n" +
            "WHERE identifier = ?";
    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Product> findAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
        List<Product> products = new LinkedList<>();
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            products.add(new Product(
                    rs.getInt("identifier"),
                    rs.getString("name"),
                    rs.getInt("price")
            ));
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
        statement.setLong(1, id);
        List<Product> products = new LinkedList<>();
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            products.add(new Product(
                    rs.getInt("identifier"),
                    rs.getString("name"),
                    rs.getInt("price")
            ));
        }
        return products.stream().findFirst();
    }

    @Override
    public void update(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getPrice());
        statement.setLong(3, product.getId());
        statement.executeUpdate();
    }

    @Override
    public void save(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT);
        statement.setLong(1, product.getId());
        statement.setString(2, product.getName());
        statement.setInt(3, product.getPrice());
        statement.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
        statement.setLong(1, id);
        statement.executeUpdate();
    }
}
