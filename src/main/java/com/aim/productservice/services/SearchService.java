package com.aim.productservice.services;

import com.aim.productservice.models.Product;
import org.springframework.data.domain.Page;

public interface SearchService {
    // Interface for search service to handle product search functionality
    Page<Product> search(String query, int page, int size, String sortBy);
}
