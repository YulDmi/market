package com.geekbrain.market.services;

import com.geekbrain.market.entities.Category;
import com.geekbrain.market.entities.Order;
import com.geekbrain.market.repositories.CategoryRepository;
import com.geekbrain.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }
    public Category save(Category category){
      return categoryRepository.save(category);
    }
}
