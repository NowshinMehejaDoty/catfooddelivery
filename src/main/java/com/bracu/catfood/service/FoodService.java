package com.bracu.catfood.service;

import com.bracu.catfood.entity.Food;
import com.bracu.catfood.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
    public Food getFood(Integer id) {
        return foodRepository.findById(id).orElse(new Food());
    }
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }
    public Food updateFood(Food food) {
        return foodRepository.save(food);
    }
}