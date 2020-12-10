package com.geekbrain.market.controllers;

import com.geekbrain.market.dto.ProductDto;
import com.geekbrain.market.entities.Category;
import com.geekbrain.market.entities.Product;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.services.CategoryService;
import com.geekbrain.market.services.ProductService;
import com.geekbrain.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class RestProductController {
    private final ProductService productService;


    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam(required = false) Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }

        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> content = productService.findAll(productFilter.getSpec(), page - 1, 5);
        List<ProductDto> list = content.getContent().stream().map(ProductDto::new).collect(Collectors.toList());
        Page<ProductDto> out = new PageImpl<ProductDto>(list, content.getPageable(), content.getTotalElements());
        return out;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
        return new ProductDto(p);
    }



    @PutMapping
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }


}