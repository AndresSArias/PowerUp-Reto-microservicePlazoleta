package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.domain.model.Plate;

public interface IPlatePersistencePort {

    PlateEntity savePlate (Plate plate);

    Plate getPlateById(Long id);

}
