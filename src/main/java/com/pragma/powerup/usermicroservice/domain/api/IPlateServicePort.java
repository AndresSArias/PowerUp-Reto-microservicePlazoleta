package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.domain.model.Plate;

public interface IPlateServicePort {

    PlateEntity savePlate (Plate plate, String ownerDNI);

    PlateEntity updatePlate(Plate plate, String ownerDNI);

    PlateEntity updateStatePlate (Plate plate, String ownerDNI);
}