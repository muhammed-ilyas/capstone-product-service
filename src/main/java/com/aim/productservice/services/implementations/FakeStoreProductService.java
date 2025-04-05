package com.aim.productservice.services.implementations;

import com.aim.productservice.dtos.FakeProductRequestDTO;
import com.aim.productservice.dtos.FakeProductResponseDTO;
import com.aim.productservice.exceptions.ResourceNotFoundException;
import com.aim.productservice.models.Product;
import com.aim.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    @Override
    public Product getProductById(long id) {
        FakeProductResponseDTO fakeProductResponseDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeProductResponseDTO.class);
        if (ObjectUtils.isEmpty(fakeProductResponseDTO)) {
            throw new ResourceNotFoundException("Product", "id", id);
        }
        return fakeProductResponseDTO.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeProductResponseDTO[] fakeProductResponseDTOs = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeProductResponseDTO[].class);
        if (ObjectUtils.isEmpty(fakeProductResponseDTOs)) {
            throw new ResourceNotFoundException("Products", "id", null);
        }
        return Stream.of(fakeProductResponseDTOs)
                .map(FakeProductResponseDTO::toProduct)
                .toList();
    }

    @Override
    public Product createProduct(Product product) {
        FakeProductRequestDTO fakeProductRequestDTO = FakeProductRequestDTO.fromProduct(product);
        FakeProductResponseDTO fakeProductResponseDTO = restTemplate.postForObject(
                "https://fakestoreapi.com/products", fakeProductRequestDTO, FakeProductResponseDTO.class);
        return fakeProductResponseDTO.toProduct();
    }
}
