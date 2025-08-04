package com.nozama.app.controller;

import com.nozama.app.dto.CartItemRequest;
import com.nozama.app.dto.CartResponse;
import com.nozama.app.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public CartResponse addItemToCart(@RequestBody @Valid CartItemRequest request) {
        return cartService.addItemToCart(request);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public CartResponse getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    @PreAuthorize("hasRole('USER')")
    public CartResponse removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        return cartService.removeItemFromCart(userId, productId);
    }
    @DeleteMapping("/{userId}/clear")
    public CartResponse clearCart(@PathVariable Long userId) {
        return cartService.clearCart(userId);
    }
}
