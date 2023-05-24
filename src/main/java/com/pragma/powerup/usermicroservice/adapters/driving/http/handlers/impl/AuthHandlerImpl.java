package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class AuthHandlerImpl implements IAuthHandler {

    private final JwtProvider jwtProvider;


    @Override
    public JwtResponseDto refresh(JwtResponseDto jwtResponseDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtResponseDto);
        return new JwtResponseDto(token);
    }
}
