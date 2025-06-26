package com.nozama.app.service;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct (ProductRequest request);
    List<ProductResponse> searchProducts(String search, String category, double minPrice, double maxPrice);
}
