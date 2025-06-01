package com.aim.productservice.controllers;

import com.aim.productservice.models.Category;
import com.aim.productservice.models.Product;
import com.aim.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    @Qualifier("productDBService")
    private ProductService productService;

    @Test
    void testGetProductByIdReturnsProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product.");
        product.setImageUrl("imageUrl");
        product.setPrice(19.99);
        product.setDeleted(false);
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");
        product.setCategory(category);

        // Mock the service to return the product
        when(productService.getProductById(1L)).thenReturn(product);

        // Act
        var response = productController.getProductById(1L);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Product", response.getBody().getName());
        assertEquals("This is a test product.", response.getBody().getDescription());
        assertEquals("imageUrl", response.getBody().getImageUrl());
        assertEquals(19.99, response.getBody().getPrice());
        assertEquals("Test Category", response.getBody().getCategory());
    }

    @Test
    void testGetProductByIdReturnsNull() {
        // Arrange
        when(productService.getProductById(1L)).thenReturn(null);

        // Act
        var response = productController.getProductById(1L);

        // Assert
        assertNull(response.getBody());

    }
  
}