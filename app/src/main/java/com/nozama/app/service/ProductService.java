package com.nozama.app.service;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct (ProductRequest request);
    List<ProductResponse> searchProducts(String search, String category, BigDecimal minPrice, BigDecimal maxPrice);
}
