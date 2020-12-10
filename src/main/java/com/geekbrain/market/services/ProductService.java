package com.geekbrain.market.services;

import com.geekbrain.market.dto.ProductDto;
import com.geekbrain.market.entities.Category;
import com.geekbrain.market.entities.Product;
import com.geekbrain.market.exeptions.ResourceNotFoundException;
import com.geekbrain.market.repositories.ProductRepository;
import com.geekbrain.market.soap.ProductSOAP;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Product createNewProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        product.setCategory(category);

        return saveOrUpdate(product);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductSOAP getProductSOAP(Product product) {
        ProductSOAP productSOAP = new ProductSOAP();
        productSOAP.setId(product.getId());
        productSOAP.setName(product.getName());
        productSOAP.setCategoryId(product.getCategory().getId());
        productSOAP.setCategoryTitle(product.getCategory().getTitle());
        productSOAP.setCost(product.getCost());
        return productSOAP;
    }
}
