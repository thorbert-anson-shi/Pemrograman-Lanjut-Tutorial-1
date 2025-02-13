package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public boolean delete(String id) {
        Product toBeDeleted = getProductById(id);
        return productData.remove(toBeDeleted);
    }

    public Product edit(String id, Product editedProduct) throws ProductNotFoundException {
        Product toBeEdited = getProductById(id);
        return productData.set(productData.indexOf(toBeEdited), editedProduct);
    }

    public Product getProductById(String id) throws ProductNotFoundException {
        return productData.stream().filter(product -> product.getProductId().equals(id)).findFirst().orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String productId) {
            super(String.format("Product with id %s not found", productId));
        }
    }
}
