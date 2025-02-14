package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static id.ac.ui.cs.advprog.eshop.repository.ProductRepository.ProductNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(200);

        productRepository.create(product1);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
    }

    @Test
    void testEditProduct() {
        // Create and add a product
        Product product = new Product();

        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Modify the product details
        product.setProductName("Sampo Cap Baru");
        product.setProductQuantity(150);
        productRepository.edit(product.getProductId(), product);

        // Retrieve and verify the updated product
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product updatedProduct = productIterator.next();
        assertEquals("Sampo Cap Baru", updatedProduct.getProductName());
        assertEquals(150, updatedProduct.getProductQuantity());
    }

    @Test
    void testEditNonExistentProduct() {
        Exception thrown = assertThrows(ProductNotFoundException.class, () -> {
            productRepository.edit("dummy-id", new Product());
        });

        assertNotNull(thrown);
    }

    @Test
    void testDeleteProduct() {
        // Create and add a product
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Delete the product
        productRepository.delete(product.getProductId());

        // Verify that the product list is empty
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteNonexistentProduct() {
        Exception thrown = assertThrows(ProductNotFoundException.class, () -> {
            productRepository.delete("dummy-id");
        });

        assertNotNull(thrown);
    }
}
