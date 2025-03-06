package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class Order {
    String id;
    List<Product> products;
    Long orderTime;
    String author;

    String status;

    static String[] validStatuses = {"WAITING_PAYMENT", "FAILED", "SUCCESS", "CANCELLED"};

    public Order(String id, List<Product> products, Long orderTime, String author) {
        this.id = id;
        this.orderTime = orderTime;
        this.author = author;
        this.status = "WAITING_PAYMENT";

        if (products.isEmpty()) {
            throw new IllegalArgumentException("");
        } else {
            this.products = products;
        }
    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {
        this(id, products, orderTime, author);

        if (Arrays.asList(validStatuses).contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setStatus(String status) {
        if (Arrays.asList(validStatuses).contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
