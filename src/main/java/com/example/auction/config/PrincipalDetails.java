package com.example.auction.config;

import com.example.auction.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Map;


public class PrincipalDetails implements UserDetails, OAuth2User {
    private Users user;
    private Map<String, Object> attributes;

    public PrincipalDetails(Users user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    public PrincipalDetails(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }



    @Override

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add( () -> {
            return user.getUserRole().getValue();});
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();

    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        String sub = attributes.get("sub").toString();
        return sub;
    }
}
