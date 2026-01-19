package com.example.ecommerce_backend.service;

import com.example.ecommerce_backend.model.Product;
import com.example.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void DeleteProduct(String productid){
        productRepository.deleteById(productid);
    }
    public Product getById(String productid){
        return productRepository.findById(productid).orElse(null);
    }
}
