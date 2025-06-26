package com.nozama.app.controller;

import com.nozama.app.dto.ProductRequest;
import com.nozama.app.dto.ProductResponse;
import com.nozama.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<ProductResponse> createProduct (@RequestBody ProductRequest request){
    return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
  }
  public ResponseEntity<List<ProductResponse>> searchProducts(
          @RequestParam(required = false) String search,
          @RequestParam(required = false) String category,
          @RequestParam(required = false) BigDecimal minPrice,
          @RequestParam(required = false) BigDecimal maxPrice
  ) {
    return ResponseEntity.ok(productService.searchProducts(search, category, minPrice, maxPrice));
  }
}
