package com.geekbrain.market.repositories;

import com.geekbrain.market.entities.DetailsUser;
import com.geekbrain.market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailsUserRepository extends JpaRepository<DetailsUser, Long> {
    @Query("select p from DetailsUser p where p.user.username = ?1")
    Optional<DetailsUser> findByUsername(String username);

}

