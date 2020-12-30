package com.geekbrain.market.entities;

import com.geekbrain.market.utils.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private Integer price;

    @Column(name = "address")
    private String address;

    public Order(User user, Cart cart, String address) {
        this.user = user;
        this.price = cart.getPrice();
        this.items = new ArrayList<>();
        this.address = address;
        cart.getItems().stream().forEach(oi -> {
            oi.setOrder(this);
            items.add(oi);
        });
      //  cart.clear();
    }

    public Order fromUser(User user){
        this.user = user;
        return this;
    }

    public Order price(Cart cart){
        this.price = cart.getPrice();
        return this;
    }

    public Order items(Cart cart){
        cart.getItems().stream().forEach(oi -> {
            oi.setOrder(this);
            items.add(oi);
        });
        return this;
    }

    public Order address(String address){
        this.address = address;
        return this;
    }
}
