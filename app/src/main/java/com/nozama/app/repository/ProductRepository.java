package com.nozama.app.repository;

import com.nozama.app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    @Query(value = """
        SELECT * FROM products p
        WHERE (:search IS NULL OR LOWER(p.name) LIKE LOWER(:search) OR LOWER(p.description) LIKE LOWER(:search))
          AND (:category IS NULL OR LOWER(p.category) = LOWER(:category))
          AND (:minPrice IS NULL OR p.price >= :minPrice)
          AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        """,
            countQuery = """
        SELECT COUNT(*) FROM products p
        WHERE (:search IS NULL OR LOWER(p.name) LIKE LOWER(:search) OR LOWER(p.description) LIKE LOWER(:search))
          AND (:category IS NULL OR LOWER(p.category) = LOWER(:category))
          AND (:minPrice IS NULL OR p.price >= :minPrice)
          AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        """,
            nativeQuery = true)
    Page<Product> findByFilters(
            @Param("search") String search,
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );
}
