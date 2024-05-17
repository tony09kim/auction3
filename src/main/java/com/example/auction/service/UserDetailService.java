package com.example.auction.service;

import com.example.auction.controller.ProductController;
import com.example.auction.entity.Users;
import com.example.auction.repository.UsersRepository;
import com.example.auction.config.PrincipalDetails;
import com.example.auction.entity.Users;
import com.example.auction.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.valueOf;

@Service
public class UserDetailService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public UserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> account = usersRepository.findByUsername(valueOf(username));
        if(account.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Users userAccount = account.get();
        ProductController.testOwnerId= Math.toIntExact(userAccount.getUserId());
        return new PrincipalDetails(userAccount);
    }
}
