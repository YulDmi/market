package com.geekbrain.market.controllers;

import com.geekbrain.market.entities.Order;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.services.OrderService;
import com.geekbrain.market.services.UserService;
import com.geekbrain.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private Cart cart;

    @GetMapping ("/admin")
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders_adm";
    }

    @GetMapping("/checkout")
    public String checkoutOrder() {
        return "checkout";
    }

    @GetMapping("/save")
    public String saveOrder(@RequestParam String name,
                            @RequestParam String telephone,
                            @RequestParam String address,
                            Principal principal,
                            Model model) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address);
        orderService.save(order);
        model.addAttribute("orders", orderService.findAllByUserId(user.getId()));
        return "orders";
    }
}
