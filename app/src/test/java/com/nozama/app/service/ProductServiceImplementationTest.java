package com.nozama.app.service;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;
import com.nozama.app.model.Product;
import com.nozama.app.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplementationTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImplementation productService;

    @Test
    void testCreateProduct() {
        // Arrange
        ProductRequest request = new ProductRequest();
        request.setName("Camiseta");
        request.setDescription("Camiseta branca");
        request.setPrice(new BigDecimal("29.90"));
        request.setCategory("Roupas");
        request.setStockQuantity(10);

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Camiseta")
                .description("Camiseta branca")
                .price(new BigDecimal("29.90"))
                .category("Roupas")
                .stockQuantity(10)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        ProductResponse response = productService.createProduct(request);

        // Assert
        assertNotNull(response);
        assertEquals("Camiseta", response.getName());
        assertEquals(new BigDecimal("29.90"), response.getPrice());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testSearchProducts() {
        // Arrange
        Product product = Product.builder()
                .id(1L)
                .name("Camiseta")
                .description("Branca básica")
                .price(new BigDecimal("29.90"))
                .category("Roupas")
                .stockQuantity(5)
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findByFilters(any(), any(), any(), any(), eq(pageable)))
                .thenReturn(productPage);

        // Act
        Page<ProductResponse> result = productService.searchProducts("camiseta", "Roupas", null, null, pageable);

        // Assert
        assertEquals(1, result.getContent().size());
        assertEquals("Camiseta", result.getContent().get(0).getName());
        verify(productRepository).findByFilters(any(), any(), any(), any(), eq(pageable));
    }
}