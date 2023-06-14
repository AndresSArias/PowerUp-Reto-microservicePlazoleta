package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import org.springframework.data.domain.Page;

public interface IPlateServicePort {

    PlateEntity savePlate (Plate plate, String ownerDNI);

    PlateEntity updatePlate(Plate plate, String ownerDNI);

    PlateEntity updateStatePlate (Plate plate, String ownerDNI);

    Page<Plate> getAllSpecificPlates (String nitRestaurant, String nameCategory, int page, int size);

    String getNameRestaurantByNit (String nitRestaurant);
}