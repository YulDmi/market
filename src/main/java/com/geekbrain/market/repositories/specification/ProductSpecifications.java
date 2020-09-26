package com.geekbrain.market.repositories.specification;

import com.geekbrain.market.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> priceGreaterOrEqualsThan(Integer minPrice){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minPrice);
    }

    public static Specification<Product> priceLessOrEqualsThan(Integer maxPrice){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxPrice);
    }

    public static Specification<Product> titleLike(String name){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", name));
    }
}
