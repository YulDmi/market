
package com.geekbrain.market;

import com.geekbrain.market.entities.Category;
import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.ProductService;
import com.geekbrain.market.utils.Cart;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CartTest {

    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    @Test
    public void cartTest() {

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(i + 1L);
            product.setCost(10000 / (i + 3));
            product.setCategory(new Category());
            product.setName("product + " + i);
            Mockito.doReturn(Optional.of(product)).when(productService).findById(product.getId());
            cart.add(product.getId());
        }

        assertEquals(10, cart.getItems().size());
        cart.remove(2L);
        assertEquals(9, cart.getItems().size());
        cart.decrement(1L);
        assertEquals(8, cart.getItems().size());
        cart.clear();
        cart.recalculate();
        assertEquals(0, cart.getPrice());

    }

    @Test
    public void cartNotNullTest(){
        assertNotNull(cart);
        assertNotNull(cart.getItems());
    }
}
