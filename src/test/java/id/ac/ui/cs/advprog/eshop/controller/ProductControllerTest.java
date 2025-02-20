package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setup() {
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("createProduct", viewName);
        verify(model).addAttribute("isEditMode", false);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost_ValidProduct() {
        Product product = new Product();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = productController.createProductPost(product, bindingResult);

        assertEquals("redirect:list", viewName);
        verify(productService).create(product);
    }

    @Test
    void testCreateProductPost_InvalidProduct() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(new org.springframework.validation.FieldError("product", "name", "Invalid name")));

        assertThrows(ValidationException.class, () -> productController.createProductPost(new Product(), bindingResult));
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(product);

        String viewName = productController.editProductPage("1", model);

        assertEquals("createProduct", viewName);
        verify(model).addAttribute("isEditMode", true);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPost_ValidProduct() {
        Product product = new Product();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = productController.editProductPost(product, bindingResult, "1");

        assertEquals("redirect:../list", viewName);
        verify(productService).edit("1", product);
    }

    @Test
    void testEditProductPost_InvalidProduct() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(new org.springframework.validation.FieldError("product", "name", "Invalid name")));

        assertThrows(ValidationException.class, () -> productController.editProductPost(new Product(), bindingResult, "1"));
    }

    @Test
    void testDeleteProductPost() {
        String viewName = productController.deleteProductPost("1");

        assertEquals("redirect:../list", viewName);
        verify(productService).delete("1");
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.productListPage(model);

        assertEquals("productList", viewName);
        verify(model).addAttribute("products", products);
    }
}
