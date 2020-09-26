package com.geekbrain.market.controllers;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

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

        Page<Product> products = productService.findAll(params, page - 1, 3);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/change_product/{id}")
    public String changeProduct(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        model.addAttribute("product", product.get());
        return "product_change";
    }

    @GetMapping("/add_change")
    public String changeProduct(
            @RequestParam Long id,
                                @RequestParam String new_name,
                                @RequestParam Integer new_cost){

        productService.save(new Product(id, new_name, new_cost));
        return "redirect:/products";
    }

}
