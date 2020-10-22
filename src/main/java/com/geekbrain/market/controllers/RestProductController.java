package com.geekbrain.market.controllers;

import com.geekbrain.market.dto.ProductDto;
import com.geekbrain.market.entities.Category;
import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.CategoryService;
import com.geekbrain.market.services.ProductService;
import com.geekbrain.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;
    private CategoryService categoryService;


    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam(required = false) Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }

        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> content = productService.findAll(productFilter.getSpec(), page - 1, 5);
        Pageable pageable = PageRequest.of(page - 1, 5);
        List<ProductDto> list = content.getContent().stream().map(ProductDto::new).collect(Collectors.toList());
        Page<ProductDto> productDtos = new PageImpl<ProductDto>(list, pageable, content.getTotalElements());
        return productDtos;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

//    @PostMapping
//    public Product createProduct(@RequestBody Product p) {
//        System.out.println("createProduct "+p.toString());
//        p.setId(null);
//        return productService.saveOrUpdate(p);
//    }
//@GetMapping("/create")

@PostMapping
public ProductDto createProduct(
        @RequestParam String name,
        @RequestParam Long categoryId,
        @RequestParam Integer cost
        ) {
    System.out.println("createProduct "+ name);
    System.out.println("createProduct "+ categoryId);
    System.out.println("createProduct "+ cost);
    Product p = new Product();
    p.setName(name);
    Category category = categoryService.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Not category byID : " + categoryId));
    p.setCategory(category);
    p.setCost(cost);
    p.setId(null);
    productService.saveOrUpdate(p);

    return new ProductDto(p);
}

//    @PostMapping
//    public ProductDto createProduct(@RequestBody ProductDto p) {
//        System.out.println("createProduct "+ p);
//        p.setId(null);
//        return p;
//    }


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