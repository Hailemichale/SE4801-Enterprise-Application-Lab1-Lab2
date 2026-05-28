package com.Haile_Peter_Abdisa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@SQLDelete(sql = "UPDATE products SET deleted = TRUE WHERE id = ?")
@SQLRestriction("deleted = FALSE")   // Hibernate 6.x — was @Where in Hibernate 5
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    @NotBlank @Size(max = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull @DecimalMin("0.00")
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull @Min(0)
    private Integer stock;

    @Column(unique = true)
    private String slug;

    @Column(nullable = false)
    @Builder.Default
    private boolean deleted = false;

    // OWNING SIDE of the relationship — holds the FK column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
