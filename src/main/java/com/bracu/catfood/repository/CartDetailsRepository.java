package com.bracu.catfood.repository;

import com.bracu.catfood.entity.Cart;
import com.bracu.catfood.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailsRepository extends JpaRepository<CartDetail, Integer> {
    CartDetail save (CartDetail cartDetail);
    List<CartDetail> findByCartId(Integer id);
}