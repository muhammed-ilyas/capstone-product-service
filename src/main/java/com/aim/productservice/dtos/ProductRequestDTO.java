package com.aim.productservice.dtos;

import com.aim.productservice.models.Category;
import com.aim.productservice.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDTO {
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category;

    public Product toProduct() {
        return Product.builder()
                .name(name)
                .description(description)
                .imageUrl(imageUrl)
                .price(price)
                .category(Category.builder().name(category).build())
                .build();
    }
}
