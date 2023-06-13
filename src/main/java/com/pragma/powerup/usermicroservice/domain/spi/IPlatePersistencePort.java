package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlatePersistencePort {

    PlateEntity savePlate (Plate plate);

    Plate getPlateById(Long id);

    Page<Plate> getAllSpecificPlates (String nitRestaurant, String nameCategory, Pageable pageable);

}
