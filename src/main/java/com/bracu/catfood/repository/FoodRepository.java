package com.bracu.catfood.repository;

import com.bracu.catfood.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food save (Food food);
    List<Food> findAll();
    Optional<Food> findById(Integer id);

}