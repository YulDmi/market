package com.geekbrain.market.dto;

import com.geekbrain.market.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private List<OrderItemDto> orderItems;
    private int price;
    private String address;

    public OrderDto(Order o) {
        this.orderItems = o.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = o.getPrice();
        this.address = o.getAddress();
    }
}
