package com.example.ecommerce_backend.controller;


import com.example.ecommerce_backend.model.Product;
import com.example.ecommerce_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @DeleteMapping("/{productid}")
    public String DeleteProduct(@PathVariable String productid){
        productService.DeleteProduct(productid);
        return "done";
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Product product = productService.getById(productId);
        return ResponseEntity.ok(product);
    }

}
