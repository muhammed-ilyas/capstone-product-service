package com.aim.productservice.services.implementations;

import com.aim.productservice.dtos.FakeProductDTO;
import com.aim.productservice.models.Product;
import com.aim.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    @Override
    public Product getProductById(long id) {
        FakeProductDTO fakeProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeProductDTO.class);
        return fakeProductDTO.toProduct();
    }
}
