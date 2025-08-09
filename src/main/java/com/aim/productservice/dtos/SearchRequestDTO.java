package com.aim.productservice.dtos;

import lombok.Data;

@Data
public class SearchRequestDTO {
    private String query;
    private int page;
    private int size;
    private String sortBy;
}
