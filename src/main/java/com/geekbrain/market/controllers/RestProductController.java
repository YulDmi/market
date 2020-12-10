package com.geekbrain.market.controllers;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.ProductService;
import com.geekbrain.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;


    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam(required = false) Map<String, String> params) {
        if(page < 1) {
            page = 1;
        }

        ProductFilter productFilter = new ProductFilter(params);
      return   productService.findAll(productFilter.getSpec(), page - 1, 5);

    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
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