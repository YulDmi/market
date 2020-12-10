package com.geekbrain.market.repositories;

import com.geekbrain.market.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
  //  @Query("select c from Category c where c.category.title = ?1")
    Optional <Category> findByTitle(String title);
}
