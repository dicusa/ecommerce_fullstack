package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Cart;
import com.example.ecommerce_backend.models.CartDTO;
import com.example.ecommerce_backend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartDTO> addToCart(@PathVariable Long cartId,
                                             @RequestParam Long productId,
                                             @RequestParam int quantity) {
        CartDTO updatedCart = cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/myCart")
    public ResponseEntity<CartDTO> getCartofCurrentUser() {
        CartDTO cart = cartService.getCurrentuserCartDTO();
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/items/{productId}")
    public ResponseEntity<CartDTO> updateCartItemQuantity(
            @PathVariable Long productId,
            @RequestParam int quantity
    ) {
        System.out.println("Inside put request");
        CartDTO updatedCart = cartService.updateCartItemQuantity( productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable Long productId) {
        CartDTO updatedCart = cartService.removeItemFromCart(productId);
        return ResponseEntity.ok(updatedCart);
    }


}

