package com.aim.productservice.services;

import com.aim.productservice.models.Product;

public interface ProductService {
    Product getProductById(long id);
}
