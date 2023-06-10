package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestPageDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.response.IRestaurantResponseMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request.IRestaurantRequestMapper;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;

import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper roleResponseMapper;

    private final JwtProvider jwtProvider;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto, String token) {
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto), token);
    }

    @Override
    public String getIdRestaurant(String nitRestaurant, String token) {
        return  restaurantServicePort.getIdRestaurantByIdOwner(nitRestaurant, jwtProvider.getIdUserFromToken(token.substring(7)));
    }

    @Override
    public Page<RestaurantResponseDto> getAllRestaurant(String page, String size) {
        return roleResponseMapper.toResponsePage(restaurantServicePort.getAllRestaurants(Integer.parseInt(page), Integer.parseInt(size)));
    }


}
