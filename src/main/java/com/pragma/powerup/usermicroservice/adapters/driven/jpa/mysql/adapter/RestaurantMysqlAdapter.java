package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoRestaurantFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;

import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.RestaurantAlreadyExistsException;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class RestaurantMysqlAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;

    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public RestaurantEntity saveRestaurant(Restaurant restaurant) {

        if (restaurantRepository.findByNit(restaurant.getNit()).isPresent()){
            throw new RestaurantAlreadyExistsException();
        }


        return restaurantRepository.save(restaurantEntityMapper.toRestaurantEntity(restaurant));
    }

    @Override
    public Restaurant getRestaurantByNit(String nit) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findByNit(nit);

        if (!restaurantEntity.isPresent()){
            throw new NoRestaurantFoundException();
        }
        return restaurantEntityMapper.toRestaurant(restaurantEntity.get());
    }

    @Override
    public Page<Restaurant> getAllRestaurants (Pageable pageable) {
        Page<RestaurantEntity> restaurantEntityPage = restaurantRepository.findAll(pageable);
        if (restaurantEntityPage.isEmpty()){
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantPage(restaurantEntityPage);
    }

}
