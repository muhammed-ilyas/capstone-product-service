package com.aim.productservice.services;

import com.aim.productservice.models.Product;
import org.springframework.data.domain.Page;

public interface SearchService {
    Page<Product> search(String query, int page, int size, String sortBy);
}
