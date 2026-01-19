
package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.CartItem;
import com.example.ecommerce_backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem request) {
        CartItem cartItem = cartService.addToCart(
                request.getUserId(),
                request.getProductId(),
                request.getQuantity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
