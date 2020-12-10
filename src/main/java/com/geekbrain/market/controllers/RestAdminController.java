package com.geekbrain.market.controllers;

import com.geekbrain.market.dto.CategoryDto;
import com.geekbrain.market.dto.ProductDto;
import com.geekbrain.market.entities.Category;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.services.CategoryService;
import com.geekbrain.market.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class RestAdminController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {

        Category category = categoryService.findByName(productDto.getCategory()).orElseThrow(() -> new ResourceNotFoundException("Not category byID : " + productDto.getCategory()));
        productService.createNewProduct(productDto, category);
        return productDto;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getListCategory() {
        return categoryService.findAll();
    }

    @PostMapping("/newCategory")
    public CategoryDto createNewCategory(@RequestBody  CategoryDto categoryDto) {
        System.out.println(categoryDto.getTitle());

       return categoryService.save(categoryDto);

    }


}
