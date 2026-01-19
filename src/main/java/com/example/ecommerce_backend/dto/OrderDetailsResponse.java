package com.example.ecommerce_backend.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderDetailsResponse {

    private String id;

    private String userId;

    private Double totalAmount;

    private String status;

    private Instant createdAt;

    private List<OrderItemResponse> items;
}

