package com.nozama.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}