package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateStateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlateHCIPage;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlatesResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

public interface IPlateHandler {

    void savePlate (PlateRequestDto plateRequestDto, String token);

    void updatePlate(PlateUpdateRequestDto plateUpdateRequestDto, String token);

    void updateStatePlate(PlateStateUpdateRequestDto plateUpdateRequestDto, String token);

    PlateHCIPage getAllSpecificPlates (String nitRestaurant, String nameCategory, int page, int size);

    //Page<PlatesResponseDto> getAllSpecificPlates (String nitRestaurant, String nameCategory, int page, int size);

}
