package com.aim.productservice.controllers;

import com.aim.productservice.dtos.ProductDTO;
import com.aim.productservice.models.Product;
import com.aim.productservice.services.implementations.FakeStoreProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

    private final FakeStoreProductService fakeStoreProductService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable long id) {
        Product product = fakeStoreProductService.getProductById(id);
        ProductDTO productDTO = ProductDTO.fromProduct(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
