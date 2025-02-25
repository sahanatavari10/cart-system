package com.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Double> {

    List<Product> findByNameContaining(String name);

    Optional<Product> findByProductId(Long id);

    void deleteByProductId(Long id);
}
