package com.aim.productservice.services.implementations;

import com.aim.productservice.exceptions.ResourceNotFoundException;
import com.aim.productservice.models.Category;
import com.aim.productservice.models.Product;
import com.aim.productservice.repositories.CategoryRepository;
import com.aim.productservice.repositories.ProductRepository;
import com.aim.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productDBService")
@AllArgsConstructor
public class ProductDBService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", id)
                );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = getCategoryFromDB(product.getCategory());
        product.setCategory(category);
        return productRepository.save(product);
    }

    private Category getCategoryFromDB(Category category) {
        return categoryRepository
                .findByName(category.getName())
                .orElseGet(() -> categoryRepository.save(category));
    }
}
