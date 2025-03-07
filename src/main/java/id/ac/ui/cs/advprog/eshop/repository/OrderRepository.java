package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orderData = new ArrayList<>();

    public Order save(Order order) {return null;}

    public Order findById(String id) throws OrderNotFoundException {return null;}

    public List<Order> findAllByAuthor(String author) throws OrderNotFoundException {return null;}

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String orderId) {
            super(String.format("Order with id %s not found", orderId));
        }
    }
}
