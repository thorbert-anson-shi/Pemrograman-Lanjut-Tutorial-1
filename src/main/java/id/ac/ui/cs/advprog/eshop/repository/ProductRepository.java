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
        Product toBeDeleted = productData.stream().filter(product -> product.getProductId().equals(id)).findFirst()
                .orElse(null);
        return productData.remove(toBeDeleted);
    }

    public Product edit(String id, Product editedProduct) {
        Product toBeEdited = productData.stream().filter(product -> product.getProductId().equals(id)).findFirst()
                .orElse(null);
        return productData.set(productData.indexOf(toBeEdited), editedProduct);
    }

    public Product get(String id) {
        return productData.stream().filter(product -> product.getProductId().equals(id)).findFirst().orElse(null);
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
