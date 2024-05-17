package com.example.auction.service;

import com.example.auction.constant.UserRole;
import com.example.auction.dto.UserCreateForm;
import com.example.auction.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createUser(UserCreateForm userCreateForm) {
        Users account = new Users();
        account.setUsername(userCreateForm.getUsername());
        account.setPassword(passwordEncoder.encode(
                userCreateForm.getPassword1()
        ));
        account.setEmail(userCreateForm.getEmail());
        account.setNickname(userCreateForm.getNickname());
        if ("ADMIN".equals(userCreateForm.getUsername().toUpperCase())) {
            account.setUserRole(UserRole.ADMIN);
        } else {
            account.setUserRole(UserRole.USER);
        }
        em.persist(account);
    }
}
