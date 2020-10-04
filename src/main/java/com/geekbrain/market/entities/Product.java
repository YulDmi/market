package com.geekbrain.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name= "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    public Product(Long id, String name, Integer cost) {
       this.id = id;
        this.name = name;
        this.cost = cost;
    }
}
