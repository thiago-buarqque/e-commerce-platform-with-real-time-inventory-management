package com.nozama.app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long orderId;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private String status;
    private String paymentMethod;
    private List<OrderItemResponse> items;
}