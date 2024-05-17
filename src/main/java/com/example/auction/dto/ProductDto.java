package com.example.auction.dto;

import com.example.auction.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;

    private int ownerId;

    private String productName;

    private String imageUrl;

    private LocalDateTime createdTime;

    private double productPrice;

    private int finalBuyerId;

    private String formattedCreatedTime;
    public Product fromProductDto(ProductDto productDto){
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setOwnerId(product.getOwnerId());
        product.setProductName(productDto.getProductName());
        product.setImageUrl(productDto.getImageUrl());
        product.setCreatedTime(productDto.getCreatedTime());
        product.setFormattedCreatedTime(productDto.getFormattedCreatedTime());
        product.setProductPrice(productDto.getProductPrice());
        product.setFinalBuyerId(productDto.getFinalBuyerId());

        return product;
    }
    public static ProductDto fromProduct(Product product){
        return new ProductDto(
                product.getProductId(),
                product.getOwnerId(),
                product.getProductName(),
                product.getImageUrl(),
                product.getCreatedTime(),
                product.getProductPrice(),
                product.getFinalBuyerId(),
                product.getFormattedCreatedTime()
        );
    }
    public ProductDto(Product entity){
    this.productId=entity.getProductId();
    this.ownerId=entity.getOwnerId();
    this.productName= entity.getProductName();
    this.imageUrl= entity.getImageUrl();
    this.createdTime=entity.getCreatedTime();
    this.productPrice=entity.getProductPrice();
    this.finalBuyerId=entity.getFinalBuyerId();
    this.formattedCreatedTime = entity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
