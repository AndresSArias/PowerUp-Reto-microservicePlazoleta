package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.UserClient;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwmerNoExistsException;
import com.pragma.powerup.usermicroservice.domain.exceptions.RoleNotAllowedForCreationException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final UserClient userClient;


    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, UserClient userClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userClient = userClient;
    }

    @Override
    public RestaurantEntity saveRestaurant(Restaurant restaurant, String token) {
        AuthUserResponse userOwner = null;
        try{
            userOwner = userClient.getUserByDocument(restaurant.getIdPropietario(), getHeaders(token)).getBody();
        }catch (Exception e){
            throw new OwmerNoExistsException();
        }

        if(!userOwner.role().equals("ROLE_OWNER")){
            throw new RoleNotAllowedForCreationException();
        }

        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    public Map<String, String> getHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        return  headers;
    }
}
