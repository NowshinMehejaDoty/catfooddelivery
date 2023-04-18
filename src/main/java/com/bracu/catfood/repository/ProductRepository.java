package com.bracu.catfood.repository;

import com.bracu.catfood.entity.Food;
import com.bracu.catfood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    List<Product> findByFoodId(Integer id);
    Optional<Product> findById(Integer id);

    Product save (Product product);
}