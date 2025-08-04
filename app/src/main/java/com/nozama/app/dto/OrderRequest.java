package com.nozama.app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private Long userId;

    private List<OrderItemRequest> items;
    @NotNull
    private String paymentMethod;

    @NotNull
    @Positive
    private BigDecimal totalAmount;
    @PastOrPresent
    private LocalDateTime CreatedAt;
}
