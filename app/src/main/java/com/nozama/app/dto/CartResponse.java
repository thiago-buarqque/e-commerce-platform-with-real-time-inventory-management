package com.nozama.app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponse {
    private Long cartId;
    private Long userId;
    private List<CartItemResponse> items;
    private BigDecimal totalAmount;
}