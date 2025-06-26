package com.nozama.app.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private double price;
    private Integer stockQuantity;
}
