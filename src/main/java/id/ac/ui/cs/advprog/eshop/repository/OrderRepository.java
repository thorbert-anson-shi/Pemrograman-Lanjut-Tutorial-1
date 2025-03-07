package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepository {
    private List<Order> orderData = new ArrayList<>();

    public Order save(Order order) {
        for (int index = 0; index < orderData.size(); index++) {
            if (Objects.equals(orderData.get(index).getId(), order.getId())) {
                orderData.set(index, order);
                return order;
            }
        }

        orderData.add(order);
        return order;
    }

    public Order findById(String id) throws OrderNotFoundException {
        return orderData.stream().filter(order -> order.getId().equals(id)).findFirst().orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<Order> findAllByAuthor(String author) throws OrderNotFoundException {
        return orderData.stream().filter(order -> order.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String orderId) {
            super(String.format("Order with id %s not found", orderId));
        }
    }
}
