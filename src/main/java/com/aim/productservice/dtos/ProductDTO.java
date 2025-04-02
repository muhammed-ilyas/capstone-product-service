package com.aim.productservice.dtos;

import com.aim.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category;

    public static ProductDTO fromProduct(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .build();
    }
}
