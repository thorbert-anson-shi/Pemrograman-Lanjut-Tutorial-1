package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");

        // Act
        Product result = productService.create(product);

        // Assert
        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void editProduct_ShouldReturnEditedProduct() {
        // Arrange
        String id = "1";
        Product editedProduct = new Product();
        editedProduct.setProductId(id);
        editedProduct.setProductName("Updated Product");

        // Act
        Product result = productService.edit(id, editedProduct);

        // Assert
        assertNotNull(result);
        assertEquals(editedProduct.getProductId(), result.getProductId());
        assertEquals(editedProduct.getProductName(), result.getProductName());
        verify(productRepository, times(1)).edit(id, editedProduct);
    }

    @Test
    void deleteProduct_ShouldReturnTrue_WhenProductExists() {
        // Arrange
        String id = "1";
        when(productRepository.delete(id)).thenReturn(true);

        // Act
        boolean result = productService.delete(id);

        // Assert
        assertTrue(result);
        verify(productRepository, times(1)).delete(id);
    }

    @Test
    void deleteProduct_ShouldReturnFalse_WhenProductDoesNotExist() {
        // Arrange
        String id = "nonexistent";
        when(productRepository.delete(id)).thenReturn(false);

        // Act
        boolean result = productService.delete(id);

        // Assert
        assertFalse(result);
        verify(productRepository, times(1)).delete(id);
    }

    @Test
    void findById_ShouldReturnProduct_WhenProductExists() {
        // Arrange
        String id = "1";
        Product product = new Product();
        product.setProductId(id);
        product.setProductName("Test Product");

        when(productRepository.getProductById(id)).thenReturn(product);

        // Act
        Product result = productService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        verify(productRepository, times(1)).getProductById(id);
    }

    @Test
    void findById_ShouldReturnNull_WhenProductDoesNotExist() {
        // Arrange
        String id = "nonexistent";
        when(productRepository.getProductById(id)).thenReturn(null);

        // Act
        Product result = productService.findById(id);

        // Assert
        assertNull(result);
        verify(productRepository, times(1)).getProductById(id);
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");

        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products.iterator());

        // Act
        List<Product> result = productService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(product1.getProductId(), result.get(0).getProductId());
        assertEquals(product2.getProductId(), result.get(1).getProductId());
        verify(productRepository, times(1)).findAll();
    }
}