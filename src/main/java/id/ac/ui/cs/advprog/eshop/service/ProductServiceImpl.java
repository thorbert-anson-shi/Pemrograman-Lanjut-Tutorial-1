package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public Product edit(String id, Product editedProduct) {
        productRepository.edit(id, editedProduct);
        return editedProduct;
    }

    @Override
    public boolean delete(String id) {
        return productRepository.delete(id);
    }

    @Override
    public Product findById(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProducts = new ArrayList<Product>();
        productIterator.forEachRemaining(allProducts::add);
        return allProducts;
    }
}
