
package com.example.ecommerce_backend.service;

import com.example.ecommerce_backend.model.CartItem;
import com.example.ecommerce_backend.model.Product;
import com.example.ecommerce_backend.repository.CartRepository;
import com.example.ecommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartItem addToCart(String userId, String productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity > product.getStock()) {
            throw new RuntimeException("Not enough stock available");
        }

        CartItem cartItem = cartRepository
                .findByUserIdAndProductId(userId, productId)
                .orElse(null);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        return cartRepository.save(cartItem);
    }

    public List<CartItem> getUserCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}
