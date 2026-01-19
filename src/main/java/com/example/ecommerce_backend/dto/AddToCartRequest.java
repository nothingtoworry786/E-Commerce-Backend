package com.example.ecommerce_backend.dto;

import lombok.Data;

@Data
public class AddToCartRequest {

    private String userId;

    private String productId;

    private Integer quantity;
}

