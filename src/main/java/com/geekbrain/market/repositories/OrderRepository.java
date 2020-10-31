package com.geekbrain.market.repositories;

import com.geekbrain.market.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    @Query("select o from Order o where o.user.username = ?1")
   List<Order> findAllOrdersByUsername(String username);
}
