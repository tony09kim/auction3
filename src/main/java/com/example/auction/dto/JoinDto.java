package com.example.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JoinDto {
    private int productId;
    private String productName;
    private String imageUrl;
    private LocalDateTime createdTime;
    private String formattedCreatedTime;
    private double myPrice;
    private double productPrice;


}
