package com.Haile_Peter_Abdisa.repository;

import com.Haile_Peter_Abdisa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ── Derived query methods (Spring Data generates SQL) ──────────────
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByPriceLessThanEqual(BigDecimal maxPrice);
    Optional<Product> findBySlug(String slug);

    // Case-insensitive search across name and description
    @Query("SELECT p FROM Product p WHERE " +
           "lower(p.name) LIKE lower(concat('%', :keyword, '%')) OR " +
           "lower(p.description) LIKE lower(concat('%', :keyword, '%'))")
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    // Paginated list — FIX: JOIN FETCH avoids N+1 on category
    @Query(value = "SELECT p FROM Product p LEFT JOIN FETCH p.category",
           countQuery = "SELECT COUNT(p) FROM Product p")
    Page<Product> findAllWithCategory(Pageable pageable);

    // Most expensive product
    Optional<Product> findTopByOrderByPriceDesc();

    // Count products in a category (JPQL aggregate)
    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :catId")
    long countByCategoryId(@Param("catId") Long catId);
}
