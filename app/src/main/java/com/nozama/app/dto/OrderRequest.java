package com.nozama.app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<OrderItemRequest> items;
    private String paymentMethod;
    private BigDecimal totalAmount;
    private LocalDateTime CreatedAt;
}
