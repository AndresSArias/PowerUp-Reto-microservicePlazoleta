package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateStateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlateHCIPage;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlatesResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request.IPlateRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.response.IPlateResponseMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlateHandlerImpl implements IPlateHandler {

    private final IPlateServicePort plateServicePort;
    private final IPlateRequestMapper plateRequestMapper;
    private final IPlateResponseMapper plateResponseMapper;
    private final JwtProvider jwtProvider;


    @Override
    public void savePlate(PlateRequestDto plateRequestDto, String token) {
        plateServicePort.savePlate(plateRequestMapper.toPlate(plateRequestDto),jwtProvider.getIdUserFromToken(token.substring(7)));
    }

    @Override
    public void updatePlate(PlateUpdateRequestDto plateUpdateRequestDto, String token) {
        plateServicePort.updatePlate(plateRequestMapper.toPlate(plateUpdateRequestDto),jwtProvider.getIdUserFromToken(token.substring(7)));
    }

    @Override
    public void updateStatePlate(PlateStateUpdateRequestDto plateStateUpdateRequestDto, String token) {
        plateServicePort.updateStatePlate(plateRequestMapper.toPlate(plateStateUpdateRequestDto),jwtProvider.getIdUserFromToken(token.substring(7)));
    }

    @Override
    public PlateHCIPage getAllSpecificPlates (String nitRestaurant, String nameCategory, int page, int size) {

        Page<PlatesResponseDto> platesResponseDtoPage = plateResponseMapper.toResponsePage(plateServicePort.getAllSpecificPlates(nitRestaurant, nameCategory, page, size));

       return plateResponseMapper.toPlateHCIPage(platesResponseDtoPage,plateServicePort.getNameRestaurantByNit(nitRestaurant),nameCategory);
    }

}
