package id.ac.ui.cs.advprog.eshop.handlers;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository.ProductNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    String errorTemplate = "error";
    String errorMessageField = "errorMessage";

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFound(ProductNotFoundException ex, Model model) {
        model.addAttribute(errorMessageField, ex.getMessage());
        return errorTemplate;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Model model) {
        String errorMessage = "Validation failed";

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        if (!errors.isEmpty()) {
            errorMessage = errors.getFirst().getDefaultMessage();
        }

        model.addAttribute(errorMessageField, errorMessage);
        return errorTemplate;
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute(errorMessageField, "An unexpected error occurred: " + ex.getMessage());
        return errorTemplate;
    }
}
