package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsRepositoryJdbcImplTest {
    private final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1, "test1", 1),
            new Product(2, "test2", 2),
            new Product(3, "test3", 3),
            new Product(4, "test4", 4),
            new Product(5, "test5", 5),
            new Product(6, "test6", 6),
            new Product(7, "test7", 7),
            new Product(8, "test8", 8),
            new Product(9, "test9", 9),
            new Product(10, "test10", 10)
    );
    private final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "test1", 1);
    private final Product EXPECTED_UPDATED_PRODUCT = new Product(2, "test update", 2222);

    private ProductsRepository productsRepository;

    @BeforeEach
    public void init() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findAllTest() throws SQLException {
        List<Product> actualProducts = productsRepository.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS.size(), actualProducts.size());
        assertTrue(EXPECTED_FIND_ALL_PRODUCTS.stream()
                .allMatch(expectedProduct -> actualProducts.stream()
                        .anyMatch(expectedProduct::equals)));
    }

    @Test
    public void findByIdTest() throws SQLException {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT,
                productsRepository.findById(EXPECTED_FIND_BY_ID_PRODUCT.getId()).get());
    }

    @Test
    public void updateTest() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT,
                productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    public void saveTest() throws SQLException {
        Product productToSave = new Product(11, "test save", 11);
        productsRepository.save(productToSave);
        assertEquals(productToSave,
                productsRepository.findById(productToSave.getId()).get());
    }

    @Test
    public void deleteTest() throws SQLException {
        Long productIdToDelete = 1L;
        assertTrue(productsRepository.findById(productIdToDelete).isPresent());
        productsRepository.delete(productIdToDelete);
        assertFalse(productsRepository.findById(productIdToDelete).isPresent());
    }
}
