package com.geekbrain.market;

import com.geekbrain.market.entities.Product;
import com.geekbrain.market.repositories.ProductRepository;
import com.geekbrain.market.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findByProductName() {
        Product product = new Product();
        product.setName("vino");
        product.setCost(850);

        Mockito.doReturn(Optional.of(product)).when(productRepository).findById(2L);

        Product productTest = productService.findById(2L).get();
        Assertions.assertNotNull(productTest);
        Assertions.assertEquals(850, productTest.getCost());
        Assertions.assertEquals("vino", productTest.getName());
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(2L));
    }
}