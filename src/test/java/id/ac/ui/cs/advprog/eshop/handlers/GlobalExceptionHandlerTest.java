package id.ac.ui.cs.advprog.eshop.handlers;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @Mock
    private Model model;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleProductNotFound_ShouldReturnErrorView() {
        // Arrange
        String notFoundProductId = "8";
        ProductNotFoundException exception = new ProductNotFoundException(notFoundProductId);

        // Act
        String viewName = exceptionHandler.handleProductNotFound(exception, model);

        // Assert
        verify(model).addAttribute("errorMessage", "Product with id " + notFoundProductId + " not found");
        assertEquals("error", viewName);
    }

    @Test
    void handleMethodArgumentNotValid_ShouldReturnErrorView() {
        // Arrange
        String errorMessage = "Validation failed";
        ObjectError error = new ObjectError("object", errorMessage);

        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(error));

        // Act
        String viewName = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException, model);

        // Assert
        verify(model).addAttribute("errorMessage", errorMessage);
        assertEquals("error", viewName);
    }

    @Test
    void handleGenericException_ShouldReturnErrorView() {
        // Arrange
        String errorMessage = "Something went wrong";
        Exception exception = new RuntimeException(errorMessage);

        // Act
        String viewName = exceptionHandler.handleGenericException(exception, model);

        // Assert
        verify(model).addAttribute("errorMessage", "An unexpected error occurred: " + errorMessage);
        assertEquals("error", viewName);
    }

    @Test
    void handleGenericException_WithNullMessage_ShouldReturnErrorView() {
        // Arrange
        Exception exception = new RuntimeException();

        // Act
        String viewName = exceptionHandler.handleGenericException(exception, model);

        // Assert
        verify(model).addAttribute("errorMessage", "An unexpected error occurred: null");
        assertEquals("error", viewName);
    }


    @Test
    void handleMethodArgumentNotValid_WithEmptyErrors_ShouldHandleEmptyList() {
        // Arrange
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of());

        // Act
        String viewName = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException, model);

        // Assert
        verify(model).addAttribute(eq("errorMessage"), any(String.class));
        assertEquals("error", viewName);
    }
}