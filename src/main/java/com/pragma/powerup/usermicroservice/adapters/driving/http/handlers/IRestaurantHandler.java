package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.RestaurantRequestPageDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantHCIPage;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IRestaurantHandler {

   void saveRestaurant (RestaurantRequestDto restaurantRequestDto, String token);

   String getIdRestaurant (String nitRestaurant, String token);

   RestaurantHCIPage getAllRestaurant(int page, int size);

}
