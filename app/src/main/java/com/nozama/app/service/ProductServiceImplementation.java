package com.nozama.app.service;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;
import com.nozama.app.model.Product;
import com.nozama.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ProductResponse> searchProducts(String search, String category, BigDecimal minPrice, BigDecimal maxPrice){
        List<Product> all = productRepository.findAll();

        return all.stream()
                .filter(p -> (search == null || p.getName().contains(search) || p.getDescription().contains(search)))
                .filter(p -> (category == null || p.getCategory().equalsIgnoreCase(category)))
                .filter(p -> (minPrice == null || p.getPrice().compareTo(minPrice) >= 0))
                .filter(p -> (maxPrice == null || p.getPrice().compareTo(maxPrice) <= 0))
                .map(this::toResponse)
                .collect(Collectors.toList());
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
