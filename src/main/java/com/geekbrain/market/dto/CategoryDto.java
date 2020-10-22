package com.geekbrain.market.dto;


import com.geekbrain.market.entities.Category;
import com.geekbrain.market.utils.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;


    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }
}
