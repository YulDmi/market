package com.geekbrain.market.utils;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.repositories.specification.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String name_product = params.get("name_product");
        if (params.containsKey("name_product") && name_product != null && !name_product.isEmpty()) {
            spec = spec.and(ProductSpecifications.titleLike(name_product));
            filterDefinitionBuilder.append("&name_product=").append(name_product);
        }

        if (params.containsKey("min_price") && params.get("min_price") != null && !params.get("min_price").isEmpty()) {
            Integer min_price = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(min_price));
            filterDefinitionBuilder.append("&min_price=").append(min_price);
        }

        if (params.containsKey("max_price") && params.get("max_price") != null && !params.get("max_price").isEmpty()) {
            Integer max_price = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(max_price));
            filterDefinitionBuilder.append("&max_price=").append(max_price);
        }

        if (params.containsKey("categoryId") && params.get("categoryId") != null && !params.get("categoryId").isEmpty()) {
            Long categoryId =Long.parseLong(params.get("categoryId"));
            spec = spec.and(ProductSpecifications.categoryIdEquals(categoryId));
            filterDefinitionBuilder.append("&categoryId=").append(categoryId);
        }
        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Product> getSpec() {
        return spec;
    }
}

