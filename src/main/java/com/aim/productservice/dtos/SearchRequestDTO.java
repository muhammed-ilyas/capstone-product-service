package com.aim.productservice.dtos;

import lombok.Data;

@Data
public class SearchRequestDTO {
    /**
     * DTO for search requests
     */
    private String query;
    private int page;
    private int size;
    private String sortBy;
}
