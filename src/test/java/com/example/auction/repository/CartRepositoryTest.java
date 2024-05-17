//package com.example.auction.repository;
//
//import com.example.auction.entity.Cart;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class CartRepositoryTest {
//    @Autowired
//    CartRepository cartRepository;
//    @Test
//    @Transactional
//    void cartTest(){
//        Cart c = new Cart();
//        c.setCartId(1L);
//        c.setProductId(1);
//        c.setProductId(1);
//        cartRepository.save(c);
//        Cart cart = cartRepository.findByProductId(1);
//        System.out.println(cart.toString());
//    }
//}