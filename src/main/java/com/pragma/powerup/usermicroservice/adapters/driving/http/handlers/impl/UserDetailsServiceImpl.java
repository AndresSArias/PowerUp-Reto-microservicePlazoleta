package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoAllowedUserException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserClient;
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
    private IUserClient IUserClient;


    public UserDetails loadUserByUsername(String documentID, String token) throws NoAllowedUserException {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);

        List<String> roles = new ArrayList<>();;

        ResponseEntity<AuthUserResponse> resultado = IUserClient.getUserByDocument(documentID, headers);

        if (resultado == null){
            throw new NoAllowedUserException();
        }
        AuthUserResponse usuario = resultado.getBody();
        roles.add(usuario.role());
        return PrincipalUser.build(usuario, roles);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
