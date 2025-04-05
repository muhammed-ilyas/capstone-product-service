package com.aim.productservice.controllers;

import com.aim.productservice.dtos.ProductRequestDTO;
import com.aim.productservice.dtos.ProductResponseDTO;
import com.aim.productservice.models.Product;
import com.aim.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);
        ProductResponseDTO productResponseDTO = ProductResponseDTO.fromProduct(product);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @GetMapping()
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products
                .stream()
                .map(ProductResponseDTO::fromProduct)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductRequestDTO productRequestDTO) {
        Product product = productRequestDTO.toProduct();
        Product createdProduct = productService.createProduct(product);
        ProductResponseDTO productResponseDTO = ProductResponseDTO.fromProduct(createdProduct);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }

}
