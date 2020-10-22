package com.geekbrain.market.controllers;


import com.geekbrain.market.dto.CartDto;
import com.geekbrain.market.dto.CategoryDto;
import com.geekbrain.market.entities.Category;
import com.geekbrain.market.services.CategoryService;
import com.geekbrain.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
public class RestCategoryController {
    private CategoryService categoryService;

//    @GetMapping("/add/{product_id}")
//    public void addToCart(@PathVariable(name = "product_id") Long productId) {
//        cart.add(productId);
//    }
//
//    @GetMapping("/dec/{product_id}")
//    public void decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
//        cart.decrement(productId);
//    }
//
//    @GetMapping("/remove/{product_id}")
//    public void removeProduct(@PathVariable(name = "product_id") Long productId) {
//        cart.remove(productId);
//    }

    @GetMapping
    public List<CategoryDto> getListCategory() {

        return categoryService.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}