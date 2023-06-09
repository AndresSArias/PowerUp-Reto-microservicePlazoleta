package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateStateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateUpdateRequestDto;

public interface IPlateHandler {

    void savePlate (PlateRequestDto plateRequestDto, String token);

    void updatePlate(PlateUpdateRequestDto plateUpdateRequestDto, String token);

    void updateStatePlate(PlateStateUpdateRequestDto plateUpdateRequestDto, String token);
}
