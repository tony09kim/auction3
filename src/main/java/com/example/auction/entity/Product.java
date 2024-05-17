package com.example.auction.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private int ownerId;

    private String productName;

    private String imageUrl;

    private LocalDateTime createdTime;

    private String formattedCreatedTime;

    private double productPrice;

    private int finalBuyerId;


    @Builder
    public Product(Long productId, int ownerId, String productName, String imageUrl, LocalDateTime createdTime,String formattedCreatedTime, double productPrice, int finalBuyerId) {
        this.productId = productId;
        this.ownerId = ownerId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.createdTime = createdTime;
        this.formattedCreatedTime= formattedCreatedTime;
        this.productPrice = productPrice;
        this.finalBuyerId = finalBuyerId;
    }


}
