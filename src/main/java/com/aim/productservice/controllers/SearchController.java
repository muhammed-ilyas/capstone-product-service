package com.aim.productservice.controllers;

import com.aim.productservice.dtos.ProductResponseDTO;
import com.aim.productservice.dtos.SearchRequestDTO;
import com.aim.productservice.models.Product;
import com.aim.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Page<ProductResponseDTO> search(@RequestBody SearchRequestDTO searchRequestDTO) {
        Page<Product> productPage = searchService.search(searchRequestDTO.getQuery(),
                                    searchRequestDTO.getPage(),
                                    searchRequestDTO.getSize(),
                                    searchRequestDTO.getSortBy());
        return productPage.map(ProductResponseDTO::fromProduct);
    }

    @GetMapping
    public Page<ProductResponseDTO> search(@RequestParam String query,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "name") String sortBy) {
        Page<Product> productPage = searchService.search(query, page, size, sortBy);
        return productPage.map(ProductResponseDTO::fromProduct);
    }
}
