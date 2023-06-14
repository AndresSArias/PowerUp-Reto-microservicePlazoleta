package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserClient;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.*;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserClient userClient;


    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserClient userClient) {
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

        if (!validatePhone(restaurant)){
            throw new PhoneLenghtException();
        }

        if (!validateRestaurantName(restaurant)){
            throw new NameFullNumberException();
        }

        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public String getIdRestaurantByIdOwner(String nitRestaurant,String idOwner) {

        Restaurant restaurant = restaurantPersistencePort.getRestaurantByNit(nitRestaurant);
        if (!idOwner.equals(restaurant.getIdPropietario())){
            throw new OwmerNoAllowedCreationException();
        }
        return restaurant.getNit();
    }

    @Override
    public Page<Restaurant> getAllRestaurants(int page, int size) {
        if (size < 1){
            throw  new LenghtSizeException();
        }
        if (page < 0){
            throw  new LenghtPageException();
        }

        final Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nombre"));
        return restaurantPersistencePort.getAllRestaurants(pageable);
    }

    public boolean validateRestaurantName (Restaurant restaurant){
        return restaurant.getNombre().chars().anyMatch(c -> !Character.isDigit(c));
    }
    public boolean validatePhone(Restaurant restaurant) {
        String[] phoneComponents = restaurant.getPhone().split(" ");
        restaurant.setPhone("");

        int lenghtPhone = 0;
        for (int i = 0; i  < phoneComponents.length; i++){
            lenghtPhone = lenghtPhone + phoneComponents[i].length();
            restaurant.setPhone(restaurant.getPhone()+phoneComponents[i]);
        }

        return lenghtPhone <= 13;
    }


    public Map<String, String> getHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        return  headers;
    }

}
