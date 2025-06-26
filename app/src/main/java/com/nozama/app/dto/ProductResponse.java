package com.nozama.app.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private Integer stockQuantity;
}
