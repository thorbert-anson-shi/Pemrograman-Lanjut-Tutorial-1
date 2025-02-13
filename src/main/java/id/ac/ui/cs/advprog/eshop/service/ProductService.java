package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product edit(String id, Product editedProduct);
    boolean delete(String id);
    Product findById(String id);
    List<Product> findAll();
}
