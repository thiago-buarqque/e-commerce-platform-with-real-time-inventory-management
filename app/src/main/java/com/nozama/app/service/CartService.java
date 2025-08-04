package com.nozama.app.service;

import com.nozama.app.dto.CartItemRequest;
import com.nozama.app.dto.CartResponse;

public interface CartService {

    CartResponse addItemToCart(CartItemRequest request);

    CartResponse getCartByUserId(Long userId);

    CartResponse removeItemFromCart(Long userId, Long productId);

    CartResponse clearCart(Long userId);

}