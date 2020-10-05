package com.geekbrain.market.controllers;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.ProductService;
import com.geekbrain.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService productService;


    @GetMapping("/products")
    public String firstRequest(Model model,
                               @RequestParam(defaultValue = "1", name = "p") Integer page,
                               @RequestParam Map<String, String> params
    ) {
        if (page <= 0) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(null);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 3);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "products";
    }

    @GetMapping("/change_product/{id}")
    public String changeProduct(Model model, @PathVariable Long id) {
       Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не найден продукт с ID: " + id));
        model.addAttribute("product", product);
        return "product_change";
    }

    @PostMapping("/add_change")
    public String changeProduct(@ModelAttribute Product product) {

        productService.saveOrUpdate(product);
        return "redirect:/products";
    }

}
