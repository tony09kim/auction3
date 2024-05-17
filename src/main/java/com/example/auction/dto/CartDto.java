package com.example.auction.dto;

import com.example.auction.entity.Cart;
import com.example.auction.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long cartId;

    private int userId;

    private int productId;

    private double myPrice;

    private boolean done;
    public Cart fromCartDto(CartDto cartDto){
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCartId());
        cart.setProductId(cartDto.getProductId());
        cart.setUserId(cartDto.getUserId());
        cart.setMyPrice(cartDto.getMyPrice());
        cart.setDone(cartDto.isDone());
        return cart;
    }
    public static CartDto fromCart(Cart cart){
        return new CartDto(
                cart.getCartId(),
                cart.getUserId(),
                cart.getProductId(),
                cart.getMyPrice(),
                cart.isDone()
        );
    }

}
