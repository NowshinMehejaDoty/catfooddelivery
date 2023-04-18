package com.bracu.catfood.repository;

import com.bracu.catfood.entity.Product;
import com.bracu.catfood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String username);
    Optional<User> findById(Integer id);
    User save(User user);
}