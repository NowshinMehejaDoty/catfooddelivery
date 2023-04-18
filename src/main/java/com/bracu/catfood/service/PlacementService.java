package com.bracu.catfood.service;

import com.bracu.catfood.entity.Placement;
import com.bracu.catfood.entity.PlacementDetail;
import com.bracu.catfood.repository.PlacementDetailRepository;
import com.bracu.catfood.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementService {
    @Autowired
    PlacementRepository placementRepository;

    @Autowired
    PlacementDetailRepository placementDetailRepository;

    public Placement addPlacement(Placement placement) {
        return placementRepository.save(placement);
    }

    public PlacementDetail addPlacementDetail(PlacementDetail placementDetail) {
        return placementDetailRepository.save(placementDetail);
    }

    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    public List<Placement> getPlacementByUser(Integer id) {
        return placementRepository.findByUserId(id);
    }

    public Placement getPlacement(Integer id) {
        return placementRepository.findById(id).orElse(null);
    }

    public List<PlacementDetail> getPlacementDetailsByPlacement(Integer id) {
        return placementDetailRepository.findByPlacementId(id);
    }
}