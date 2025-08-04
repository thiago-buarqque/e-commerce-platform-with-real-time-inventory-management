package com.nozama.app.service.implementation;

import com.nozama.app.dto.CartItemRequest;
import com.nozama.app.dto.CartItemResponse;
import com.nozama.app.dto.CartResponse;
import com.nozama.app.model.Cart;
import com.nozama.app.model.CartItem;
import com.nozama.app.model.Product;
import com.nozama.app.model.User;
import com.nozama.app.repository.CartItemRepository;
import com.nozama.app.repository.CartRepository;
import com.nozama.app.repository.ProductRepository;
import com.nozama.app.repository.UserRepository;
import com.nozama.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartResponse addItemToCart(CartItemRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem existingItem = cart.getItems().stream()
                .filter(i -> i.getProduct().getId() == product.getId())
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cartItemRepository.save(newItem);
        }

        return mapToCartResponse(cart);
    }

    @Override
    public CartResponse getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return mapToCartResponse(cart);
    }

    @Override
    public CartResponse clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear(); // Limpa a lista localmente também

        return mapToCartResponse(cart);
    }

    @Override
    public CartResponse removeItemFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cartItemRepository.delete(itemToRemove);

        return mapToCartResponse(cart);
    }

    private CartResponse mapToCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setCartId(cart.getId());
        response.setUserId(cart.getUser().getId());

        List<CartItemResponse> itemResponses = cart.getItems().stream().map(item -> {
            CartItemResponse itemResponse = new CartItemResponse();
            itemResponse.setProductId(item.getProduct().getId());
            itemResponse.setProductName(item.getProduct().getName());
            itemResponse.setQuantity(item.getQuantity());
            itemResponse.setPricePerUnit(item.getProduct().getPrice());
            itemResponse.setSubtotal(item.getProduct().getPrice().multiply(
                    BigDecimal.valueOf(item.getQuantity())));
            return itemResponse;
        }).toList();

        response.setItems(itemResponses);

        BigDecimal totalAmount = itemResponses.stream()
                .map(CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        response.setTotalAmount(totalAmount);

        return response;
    }


}
