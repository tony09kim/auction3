package com.example.auction.repository;

import com.example.auction.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByProductId(int productId);
    Cart findByProductIdAndUserId(int productId,int userId);
    List<Cart> findByUserId(int userId);

    void deleteByProductId(int productId);
//    Optional<Cart> findByProductId(int productId);
}
