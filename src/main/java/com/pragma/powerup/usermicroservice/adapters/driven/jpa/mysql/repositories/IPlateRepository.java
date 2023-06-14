package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {
    Page<PlateEntity> findAllByRestaurantEntity(RestaurantEntity restaurantEntity, Pageable pageable);
    Page<PlateEntity> findAllByRestaurantEntityAndCategoryEntity(RestaurantEntity restaurantEntity, CategoryEntity categoryEntity,Pageable pageable);

}
