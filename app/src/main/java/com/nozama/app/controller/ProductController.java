package com.nozama.app.controller;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;
import com.nozama.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor

public class ProductController {
  private final ProductService productService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ProductResponse createProduct(@Valid @RequestBody ProductRequest request){
    return productService.createProduct(request);
  }

  @GetMapping
  public Page<ProductResponse> searchProducts(
          @RequestParam(required = false) String search,
          @RequestParam(required = false) String category,
          @RequestParam(required = false) BigDecimal minPrice,
          @RequestParam(required = false) BigDecimal maxPrice,
          @PageableDefault(page = 0, size = 3, sort = "name") Pageable pageable
  ) {
    return productService.searchProducts(search, category, minPrice, maxPrice, pageable);
  }
}
