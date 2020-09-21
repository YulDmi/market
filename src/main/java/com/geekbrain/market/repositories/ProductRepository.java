package com.geekbrain.market.repositories;

import com.geekbrain.market.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.cost = (select min(cost) from Product)")
    List<Product> findByMinCostHQL();

    @Query("select p from Product p where p.cost = (select max(cost) from Product)")
    List<Product> findByMaxCostHQL();
}