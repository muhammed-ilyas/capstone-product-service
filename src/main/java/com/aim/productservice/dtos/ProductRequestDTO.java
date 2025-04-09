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
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setPrice(price);
        Category category1 = new Category();
        category1.setName(this.category);
        product.setCategory(category1);
        return product;
    }
}
