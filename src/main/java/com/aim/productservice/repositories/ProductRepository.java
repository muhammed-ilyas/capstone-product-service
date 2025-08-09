package com.aim.productservice.repositories;

import com.aim.productservice.models.Category;
import com.aim.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    List<Product> findByCategory(Category category);

    List<Product> findByCategory_Name(String categoryName);

    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> getProductByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = CustomQuery.GET_PRODUCT_BY_CATEGORY_NAME, nativeQuery = true)
    List<Product> getProductByCategoryNameNative(@Param("categoryName") String categoryName);

    /**
     * Finds products by name containing the specified string, ignoring case.
     * @param name the name string to search for
     * @param pageable the pagination information
     * @return a paginated list of products matching the name criteria
     */
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
