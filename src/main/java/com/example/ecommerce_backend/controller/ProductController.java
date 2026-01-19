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
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allproducts=productService.getAllProducts();
        return ResponseEntity.ok(allproducts);
    }
    @DeleteMapping("/{productid}")
    public ResponseEntity<String> DeleteProduct(@PathVariable String productid){
        productService.DeleteProduct(productid);
        return ResponseEntity.status(HttpStatus.OK).body("Product Deleted");
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Product product = productService.getById(productId);
        return ResponseEntity.ok(product);
    }

}
