package com.geekbrain.market.services;

import com.geekbrain.market.dto.OrderDto;
import com.geekbrain.market.entities.Order;
import com.geekbrain.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();

    }public List<OrderDto> findAllDtos(String username){
        return orderRepository.findAllOrdersByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }
    public List<Order> findAllByUserId(Long userId){
        return orderRepository.findAllByUserId(userId);
    }
    public Order save(Order order){
      return  orderRepository.save(order);
    }
}
