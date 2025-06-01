package com.aim.productservice.dtos;

import com.aim.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category;

    public static ProductResponseDTO fromProduct(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .build();
    }
}
