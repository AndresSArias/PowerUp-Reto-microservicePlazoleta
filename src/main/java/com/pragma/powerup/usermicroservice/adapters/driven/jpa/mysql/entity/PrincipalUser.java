package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class PrincipalUser implements UserDetails {

    private String name;
    private String numberDocument;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(String name, String numberDocument, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.numberDocument = numberDocument;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static PrincipalUser build(AuthUserResponse usuario, List<String> roles) {
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol)).toList();

        return new PrincipalUser(usuario.name(), usuario.numberDocument(), usuario.email(),
                usuario.password(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return numberDocument;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
