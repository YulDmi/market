package com.geekbrain.market.dto;

import com.geekbrain.market.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

//private Page<Product> page;

    private Long Id;
    private String name;
    private String category;
    private int cost;

    public ProductDto(Product p) {
        this.Id = p.getId();
        this.name = p.getName();
        this.category = p.getCategory().getTitle();
        this.cost = p.getCost();

    }
}
