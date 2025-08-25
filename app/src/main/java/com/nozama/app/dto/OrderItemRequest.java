package com.nozama.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {
    @NotNull(message = "The product id is required")
    private Long productId;
    @NotNull(message = "The quantity is required")
    private Integer quantity;
}

