package com.aim.productservice.services.implementations;

import com.aim.productservice.models.Product;
import com.aim.productservice.repositories.ProductRepository;
import com.aim.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    /**
     * This class implements the SearchService interface
     * to provide search functionality for products.
     */
    private final ProductRepository productRepository;

    public SearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Searches for products based on the provided query, page, size, and sort criteria.
     *
     * @param query the search query string
     * @param page the page number to retrieve
     * @param size the number of items per page
     * @param sortBy the field to sort by
     * @return a paginated list of products matching the search criteria
     */
    @Override
    public Page<Product> search(String query, int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findByNameContainingIgnoreCase(query, pageable);
    }
}
