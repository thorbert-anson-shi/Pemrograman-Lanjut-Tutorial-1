package id.ac.ui.cs.advprog.eshop.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter @Setter
public class Product {
    @NotNull
    private String productId = UUID.randomUUID().toString();

    @NotBlank(message="Product name cannot be empty")
    private String productName;

    @Min(value=0, message="Product quantity cannot go lower than 0")
    private int productQuantity;
}