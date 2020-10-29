package com.geekbrain.market.controllers;


import com.geekbrain.market.configs.JwtTokenUtil;
import com.geekbrain.market.entities.Order;
import com.geekbrain.market.entities.User;
import com.geekbrain.market.services.OrderService;
import com.geekbrain.market.services.UserService;
import com.geekbrain.market.utils.Cart;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class RestOrderController {
private final UserService userService;
private final OrderService orderService;
private final Cart cart;
private JwtTokenUtil jwtTokenUtil;


    @GetMapping("/save")
    public Long saveOrder(@RequestParam String recipient,
                          @RequestParam String phone,
                          @RequestParam String address,
                          HttpServletRequest request
                            ) {

        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwt);
            } catch (ExpiredJwtException e) {
               e.printStackTrace();
            }
        }

        User user = userService.findByUsername(username);
        Order order = new Order(user, cart, address);
        orderService.save(order);
        return order.getId();
    }
}
