package com.geekbrain.market.services;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;


    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByMinCostHQL() {
        return productRepository.findByMinCostHQL();
    }

    public List<Product> findByMaxCostHQL() {
        return productRepository.findByMaxCostHQL();
    }
}
