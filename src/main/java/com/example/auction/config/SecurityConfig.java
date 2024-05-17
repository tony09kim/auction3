package com.example.auction.config;

import com.example.auction.controller.ProductController;
import com.example.auction.service.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;
    @Bean
    AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthFailureHandler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/signup").permitAll()
//                        .requestMatchers("/**").permitAll())
                        .anyRequest().authenticated())

                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
//                        .failureForwardUrl("/login")
                        .usernameParameter("username")
                        .defaultSuccessUrl("/main", true))


                .logout(out->out
                        .logoutSuccessUrl("/login")
                        .logoutUrl("/logout"))


                .oauth2Login(oAuth->oAuth
                        .loginPage("/login")
                        .defaultSuccessUrl("/main")
                        .userInfoEndpoint(userInfo->
                                userInfo.userService(principalOauth2UserService)))
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
