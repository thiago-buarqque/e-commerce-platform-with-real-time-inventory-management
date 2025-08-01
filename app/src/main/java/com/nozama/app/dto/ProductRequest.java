package com.nozama.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank(message = "The product name is required.")
    private String name;

    @Size(max = 500, message = "The description cannot exceed 500 characters" )
    private String description;

    @NotBlank (message = "The category is required")
    private String category;

    @NotNull(message = "The price is required")
    @Positive(message = "The price must be a positive value")
    private BigDecimal price;

    @NotNull(message = "The stock quantity is required")
    @PositiveOrZero(message = "The stock quantity must be zero or a positive value")
    private Integer stockQuantity;
}
