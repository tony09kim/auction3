package com.example.auction.repository;

import com.example.auction.entity.Cart;
import com.example.auction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOwnerId(int ownerId);
}
