package com.example.auction.repository;

import com.example.auction.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository <Users, Long>{
    Optional<Users> findByProviderAndProviderId(String provider, String providerId);

    Optional<Users> findByUsername(String username);
}

