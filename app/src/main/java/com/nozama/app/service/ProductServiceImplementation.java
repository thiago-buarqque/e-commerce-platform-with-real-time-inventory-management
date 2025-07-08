package com.nozama.app.service;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;
import com.nozama.app.model.Product;
import com.nozama.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .stockQuantity(request.getStockQuantity())
                .build();

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }
    @Override
    public Page<ProductResponse> searchProducts(String search, String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        String searchLike = (search == null || search.isBlank()) ? null : "%" + search.toLowerCase() + "%";
        Page<Product> productsPage = productRepository.findByFilters(searchLike, category, minPrice, maxPrice, pageable);
        return productsPage.map(this::toResponse);
    }

    private ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setCategory(product.getCategory());
        response.setStockQuantity(product.getStockQuantity());
        return response;
    }
}
