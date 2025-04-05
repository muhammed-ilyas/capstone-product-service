package com.aim.productservice.dtos;

import com.aim.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FakeProductRequestDTO {
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    public static FakeProductRequestDTO fromProduct(Product product) {
        return FakeProductRequestDTO.builder()
                .title(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory().getName())
                .image(product.getImageUrl())
                .build();
    }
}
