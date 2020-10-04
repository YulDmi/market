package com.geekbrain.market.services;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.repositories.ProductRepository;
import com.geekbrain.market.repositories.specification.ProductSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Page<Product> findAll(Map<String, String> params, int page, int size) {
        Specification<Product> spec = Specification.where(null);

        if (params.containsKey("name_product")  && !params.get("name_product").isEmpty()) {
            spec = spec.and(ProductSpecifications.titleLike(params.get("name_product")));
        }
        if (params.containsKey("min_price") && !params.get("min_price").isEmpty()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("min_price"))));
        }
        if (params.containsKey("max_price") && !params.get("max_price").isEmpty()) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(Integer.parseInt(params.get("max_price"))));
        }
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product){
        productRepository.save(product);
    }
}
