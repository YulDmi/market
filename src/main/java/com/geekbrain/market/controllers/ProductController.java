package com.geekbrain.market.controllers;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService productService;


    @GetMapping("/products")
    public String firstRequest(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page <= 0) {
            page = 1;
        }
        model.addAttribute("products", productService.findAll(page - 1, 5));
        return "products";
    }

    @GetMapping("/products/{prefix}")
    public String findMaxCost(Model model, @PathVariable String prefix) {
        List<Product> products = new ArrayList<>();
        if (prefix.equals("min")) {
            products = productService.findByMinCostHQL();
        }
        if (prefix.equals("max")) {
            products = productService.findByMaxCostHQL();
        }
        if (prefix.equals("min_max")) {
            products = productService.findByMinCostHQL();
            products.addAll(productService.findByMaxCostHQL());
        }
        if (products.isEmpty()) {
            return "redirect:/products";
        }
        model.addAttribute("products", products);
        return "Products";
    }


}
