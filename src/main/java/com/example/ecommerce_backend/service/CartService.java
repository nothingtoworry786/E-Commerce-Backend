
package com.example.ecommerce_backend.service;

import com.example.ecommerce_backend.dto.AddToCartRequest;
import com.example.ecommerce_backend.dto.CartItemResponse;
import com.example.ecommerce_backend.model.CartItem;
import com.example.ecommerce_backend.model.Product;
import com.example.ecommerce_backend.repository.CartRepository;
import com.example.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartItemResponse addToCart(AddToCartRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (request.getQuantity() > product.getStock()) {
            throw new RuntimeException("Not enough stock available");
        }

        CartItem cartItem = cartRepository
                .findByUserIdAndProductId(request.getUserId(), request.getProductId())
                .orElse(null);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUserId(request.getUserId());
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        }

        CartItem saved = cartRepository.save(cartItem);
        return toResponse(saved, product);
    }

    public List<CartItemResponse> getUserCart(String userId) {
        List<CartItem> items = cartRepository.findByUserId(userId);
        return items.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }

    private CartItemResponse toResponse(CartItem cartItem) {
        Product product = productRepository.findById(cartItem.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return toResponse(cartItem, product);
    }

    private CartItemResponse toResponse(CartItem cartItem, Product product) {
        CartItemResponse response = new CartItemResponse();
        response.setId(cartItem.getId());
        response.setUserId(cartItem.getUserId());
        response.setQuantity(cartItem.getQuantity());
        response.setProduct(product);
        return response;
    }
}

