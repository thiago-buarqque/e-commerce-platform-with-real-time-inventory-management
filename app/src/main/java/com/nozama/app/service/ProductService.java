package com.nozama.app.service;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct (ProductRequest request);

    Page<ProductResponse> searchProducts(
            String search,
            String category,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable
    );
}
