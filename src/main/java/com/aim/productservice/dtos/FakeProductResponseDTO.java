package com.aim.productservice.dtos;

import com.aim.productservice.models.Category;
import com.aim.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FakeProductResponseDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(title)
                .description(description)
                .imageUrl(image)
                .price(price)
                .category(Category.builder().name(category).build())
                .build();
    }
}

