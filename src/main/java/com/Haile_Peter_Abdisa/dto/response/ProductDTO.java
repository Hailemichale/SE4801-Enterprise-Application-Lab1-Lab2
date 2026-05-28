package com.Haile_Peter_Abdisa.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDTO(
    Long    id,
    String  name,
    String  description,
    BigDecimal price,
    int     stock,
    String  slug,
    String  categoryName,   // flatten — client doesn't need the full Category object
    Long    categoryId,
    LocalDateTime createdAt
) {}
