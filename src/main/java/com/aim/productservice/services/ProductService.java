package com.aim.productservice.services;

import com.aim.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);
}
