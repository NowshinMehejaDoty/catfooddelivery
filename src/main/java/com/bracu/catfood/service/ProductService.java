package com.bracu.catfood.service;

import com.bracu.catfood.entity.Product;
import com.bracu.catfood.entity.User;
import com.bracu.catfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product addProduct(Product product) {
        return productRepository.save(product);
    };
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByFood(Integer id) {
        return productRepository.findByFoodId(id);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

}