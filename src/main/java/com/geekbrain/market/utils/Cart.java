package com.geekbrain.market.utils;

import com.geekbrain.market.controllers.ResourceNotFoundException;
import com.geekbrain.market.entities.OrderItem;
import com.geekbrain.market.entities.Product;
import com.geekbrain.market.services.ProductService;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int price;


    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void add(Long productId) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals((productId))) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        System.out.println("долвдплорд");
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + productId + " (add to cart)"));
        items.add(new OrderItem(p));
        recalculate();
    }

//    public void increment(Long id) {
//        for (OrderItem o : items) {
//            if (o.getProduct().getId().equals((id))) {
//                o.incrementQuantity();
//                recalculate();
//                return;
//            }
//        }
//    }

    public void decrement(Long productId) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals((productId))) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    remove(productId);
                    return;
                }
                recalculate();
                return;
            }
        }
       // recalculate();
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        for (OrderItem o : items) {
            o.setPricePerProduct(o.getProduct().getCost());
            o.setPrice(o.getProduct().getCost() * o.getQuantity());
            price += o.getPrice();

        }
    }

    public void clear() {
        items.clear();
        price = 0;
    }
}
