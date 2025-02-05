package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductService {
    public Product create(Product product);
    public Iterator<Product> findAll();
}
