package com.bracu.catfood.repository;

import com.bracu.catfood.entity.Placement;
import com.bracu.catfood.entity.PlacementDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacementDetailRepository extends JpaRepository<PlacementDetail, Integer> {
    PlacementDetail save (PlacementDetail detail);
    List<PlacementDetail> findByPlacementId(Integer id);
}