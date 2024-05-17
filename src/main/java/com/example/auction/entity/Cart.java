package com.example.auction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private int userId;

    private int productId;

    private double myPrice;

    private boolean done;
//@Builder
//    public Cart(Long cartId, int userId, int productId, double myPrice, boolean done) {
//        this.cartId = cartId;
//        this.userId = userId;
//        this.productId = productId;
//        this.myPrice = myPrice;
//        this.done = done;
//    }

}
