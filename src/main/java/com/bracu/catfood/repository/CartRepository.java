package com.bracu.catfood.repository;

import com.bracu.catfood.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart save (Cart cart);
    List<Cart> findByUserId(Integer id);
    Optional<Cart> findById(Integer id);
    List<Cart> findByUserIdAndIsPlaced(Integer id, Integer isPlaced);
}