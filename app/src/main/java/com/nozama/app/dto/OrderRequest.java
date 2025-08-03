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
    @NotNull(message = "The userId is required")
    private Long userId;
    @NotNull(message = "The items of the Order are required")
    private List<OrderItemRequest> items;
    @NotNull(message = "The paymentMethod is required")
    private String paymentMethod;
}
