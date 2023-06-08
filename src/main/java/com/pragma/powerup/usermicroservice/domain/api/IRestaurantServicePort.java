package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

public interface IRestaurantServicePort {
    RestaurantEntity saveRestaurant (Restaurant restaurant, String token);

    String getIdRestaurantByIdOwner(String nitRestaurant, String IdOwner);

}
