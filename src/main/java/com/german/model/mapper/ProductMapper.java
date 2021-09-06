package com.german.model.mapper;

import com.german.model.dto.ProductDto;
import com.german.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto (Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setArticle(product.getArticle());
        dto.setDeleted(product.getDeleted());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
