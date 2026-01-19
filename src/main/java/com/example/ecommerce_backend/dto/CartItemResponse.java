package com.example.ecommerce_backend.dto;

import com.example.ecommerce_backend.model.Product;
import lombok.Data;

@Data
public class CartItemResponse {

    private String id;

    private String userId;

    private Integer quantity;

    private Product product;
}

