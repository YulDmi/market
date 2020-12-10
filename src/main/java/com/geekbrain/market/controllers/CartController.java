package com.geekbrain.market.controllers;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.ProductService;
import com.geekbrain.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private Cart cart;

    @GetMapping
    public String showCartPage() {
                return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart(@PathVariable(name = "product_id") Long productId,
                            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Нельзя добавить продукт с ID: " + productId));
        cart.add(p.getId());
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/inc/{product_id}")
    public String incrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.add(productId);
      return "redirect:/cart";
    }

    @GetMapping("/dec/{product_id}")
    public String decrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrement(productId);
      return "redirect:/cart";
    }

    @GetMapping("/remove/{product_id}")
    public String removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }


}
