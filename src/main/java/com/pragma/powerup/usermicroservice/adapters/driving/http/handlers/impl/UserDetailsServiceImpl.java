package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoAllowedUserException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserClient userClient;


    public UserDetails loadUserByUsername(String documentID, String token) throws UsernameNotFoundException {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        ResponseEntity<AuthUserResponse> resultado = null;
        AuthUserResponse usuario;

        List<String> roles;
        try{
            resultado = userClient.getUserByDocument(documentID, headers);
        }catch (Exception e){
            throw new NoAllowedUserException();
        }
        usuario = resultado.getBody();
        roles = new ArrayList<>();
        roles.add(usuario.role());

        return PrincipalUser.build(usuario, roles);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
