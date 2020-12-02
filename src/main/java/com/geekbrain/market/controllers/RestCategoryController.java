package com.geekbrain.market.controllers;


import com.geekbrain.market.dto.CategoryDto;
import com.geekbrain.market.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class RestCategoryController {
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getListCategory() {
        return categoryService.findAll();
    }
}