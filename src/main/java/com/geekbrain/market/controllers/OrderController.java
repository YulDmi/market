package com.geekbrain.market.controllers;

import com.geekbrain.market.entities.Order;
import com.geekbrain.market.services.OrderService;
import com.geekbrain.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/checkout")
    public String checkoutOrder() {
        return "checkout";
    }

    @GetMapping("/save")
    public String saveOrder(@RequestParam String name,
                            @RequestParam String telephone,
                            @RequestParam String address,
                            Model model) {
        Order order = new Order();
        order.setCustomer(name + telephone + address);
        order.setPrice(cart.getPrice());
        cart.getItems().forEach(orderItem -> orderItem.setOrder(order));
        order.setItems(cart.getItems());
        orderService.save(order);
        cart.getItems().clear();
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }
}
