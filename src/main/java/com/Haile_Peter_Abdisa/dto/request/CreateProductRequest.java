package com.Haile_Peter_Abdisa.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreateProductRequest(
    @NotBlank @Size(max = 200)  String name,
    String description,
    @NotNull @DecimalMin("0.01") BigDecimal price,
    @NotNull @Min(0)             Integer stock,
    @NotNull                     Long    categoryId
) {}
