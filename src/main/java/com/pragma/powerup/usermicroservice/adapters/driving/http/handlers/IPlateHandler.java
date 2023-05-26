package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;

public interface IPlateHandler {

    void savePlate (PlateRequestDto plateRequestDto, String token);

}
