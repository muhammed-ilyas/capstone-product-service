package com.aim.productservice.services.implementations;


import com.aim.productservice.dtos.FakeProductResponseDTO;
import com.aim.productservice.exceptions.ResourceNotFoundException;
import com.aim.productservice.models.Category;
import com.aim.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FakeStoreProductServiceTest {

    private final RestTemplate restTemplate = mock(RestTemplate.class);

    private final FakeStoreProductService fakeStoreProductService = new FakeStoreProductService(restTemplate);

    @Test
    void testGetProductByIdReturnsProduct() {
        FakeProductResponseDTO fakeProductResponseDTO = FakeProductResponseDTO.builder()
                .id(1L)
                .title("Test Product")
                .price(19.99)
                .description("This is a test product.")
                .category("Test Category")
                .image("imageUrl")
                .build();
        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1", FakeProductResponseDTO.class))
                .thenReturn(fakeProductResponseDTO);
        var product = fakeStoreProductService.getProductById(1L);
        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(19.99, product.getPrice());
        assertEquals("This is a test product.", product.getDescription());
        assertEquals("imageUrl", product.getImageUrl());
        assertEquals("Test Category", product.getCategory().getName());
    }

    @Test
    void testGetProductByIdReturnsNull() {
        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1", FakeProductResponseDTO.class))
                .thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> {
            fakeStoreProductService.getProductById(1L);
        });
    }

    @Test
    void testCreateProductReturnsProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(19.99);
        product.setDescription("This is a test product.");
        product.setImageUrl("imageUrl");
        Category category = new Category();
        category.setName("Test Category");
        product.setCategory(category);
        FakeProductResponseDTO fakeProductResponseDTO = FakeProductResponseDTO.builder()
                .id(1L)
                .title("Test Product")
                .price(19.99)
                .description("This is a test product.")
                .category("Test Category")
                .image("imageUrl")
                .build();

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"), any(), eq(FakeProductResponseDTO.class)))
                .thenReturn(fakeProductResponseDTO);
        var response = fakeStoreProductService.createProduct(product);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Product", response.getName());
        assertEquals(19.99, response.getPrice());
        assertEquals("This is a test product.", response.getDescription());
        assertEquals("imageUrl", response.getImageUrl());
        assertEquals("Test Category", response.getCategory().getName());
    }

}