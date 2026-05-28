package com.Haile_Peter_Abdisa.controller;

import com.Haile_Peter_Abdisa.dto.request.CreateProductRequest;
import com.Haile_Peter_Abdisa.dto.response.ProductDTO;
import com.Haile_Peter_Abdisa.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product catalogue endpoints")
public class ProductController {

    private final ProductService productService;

    // GET /api/v1/products?page=0&size=10&sort=price,asc
    @GetMapping
    @Operation(summary = "List all products (paginated)")
    public ResponseEntity<Page<ProductDTO>> list(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    // GET /api/v1/products/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    // GET /api/v1/products/search?keyword=java&maxPrice=50.00
    @GetMapping("/search")
    @Operation(summary = "Search products by keyword and/or max price")
    public ResponseEntity<List<ProductDTO>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) BigDecimal maxPrice) {
        return ResponseEntity.ok(productService.search(keyword, maxPrice));
    }

    // GET /api/v1/products/slug/{slug}
    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get a product by slug")
    public ResponseEntity<ProductDTO> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(productService.findBySlug(slug));
    }

    // POST /api/v1/products
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product")
    public ProductDTO create(@Valid @RequestBody CreateProductRequest req) {
        return productService.create(req);
    }

    // DELETE /api/v1/products/{id}  — soft delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete a product")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
