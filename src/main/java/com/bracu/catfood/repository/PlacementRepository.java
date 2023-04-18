package com.bracu.catfood.repository;

import com.bracu.catfood.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlacementRepository extends JpaRepository<Placement, Integer> {
    Placement save (Placement placement);
    List<Placement> findAll();
    List<Placement> findByUserId(Integer id);
    Optional<Placement> findById(Integer id);
}