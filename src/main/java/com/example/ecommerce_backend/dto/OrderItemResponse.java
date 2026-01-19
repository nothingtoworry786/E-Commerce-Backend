package com.example.ecommerce_backend.dto;

import com.example.ecommerce_backend.model.Product;
import lombok.Data;

@Data
public class OrderItemResponse {

    private String id;

    private Integer quantity;

    private Double price;

    private Product product;
}

