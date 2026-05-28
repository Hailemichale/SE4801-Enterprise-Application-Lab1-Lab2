package com.Haile_Peter_Abdisa.mapper;

import com.Haile_Peter_Abdisa.dto.response.ProductDTO;
import com.Haile_Peter_Abdisa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product p) {
        return new ProductDTO(
            p.getId(),
            p.getName(),
            p.getDescription(),
            p.getPrice(),
            p.getStock(),
            p.getSlug(),
            p.getCategory() != null ? p.getCategory().getName() : null,
            p.getCategory() != null ? p.getCategory().getId()   : null,
            p.getCreatedAt()
        );
    }
}
