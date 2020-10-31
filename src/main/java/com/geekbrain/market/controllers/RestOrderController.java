package com.geekbrain.market.controllers;


import com.geekbrain.market.configs.JwtTokenUtil;
import com.geekbrain.market.dto.OrderDto;
import com.geekbrain.market.dto.OrderItemDto;
import com.geekbrain.market.entities.Order;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.services.OrderService;
import com.geekbrain.market.services.UserService;
import com.geekbrain.market.utils.Cart;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class RestOrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;


    @GetMapping
    private List<OrderDto> getAllOrders(Principal principal) {
        return orderService.findAllDtos(principal.getName());
    }

    @PostMapping
    public void saveOrder(@RequestParam String recipient,
                          @RequestParam String phone,
                          @RequestParam String address,
                          Principal principal
    ) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, address);
        orderService.save(order);
        cart.clear();
    }
}
