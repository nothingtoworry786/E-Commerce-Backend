package com.example.ecommerce_backend.repository;
import com.example.ecommerce_backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ProductRepository extends MongoRepository<Product,String> {
}
