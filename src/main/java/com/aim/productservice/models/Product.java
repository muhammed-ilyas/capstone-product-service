package com.aim.productservice.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private Category category;
}
