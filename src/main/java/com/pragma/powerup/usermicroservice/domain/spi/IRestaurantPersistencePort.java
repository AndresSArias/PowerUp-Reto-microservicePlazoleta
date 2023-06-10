package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantEntity saveRestaurant (Restaurant restaurant);

    Restaurant getRestaurantByNit (String nit);

    Page<Restaurant> getAllRestaurants(Pageable pageable);

}
