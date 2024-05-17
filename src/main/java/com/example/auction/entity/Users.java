package com.example.auction.entity;

import com.example.auction.constant.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(name="username", length = 50)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length = 100)
    private String email;
    @Column(name = "nickname", length = 50)
    private String nickname;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    //Oauth2 데이터 저장용
    private String provider;
    private String providerId;
}
