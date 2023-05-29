package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request.IPlateRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlateHandlerImpl implements IPlateHandler {

    private final IPlateServicePort plateServicePort;
    private final IPlateRequestMapper plateRequestMapper;
    private final JwtProvider jwtProvider;


    @Override
    public void savePlate(PlateRequestDto plateRequestDto, String token) {
        plateServicePort.savePlate(plateRequestMapper.toPlate(plateRequestDto),jwtProvider.getNombreUsuarioFromToken(token.substring(7)));
    }

    @Override
    public void updatePlate(PlateUpdateRequestDto plateUpdateRequestDto, String token) {
        plateServicePort.updatePlate(plateRequestMapper.toPlate(plateUpdateRequestDto),jwtProvider.getNombreUsuarioFromToken(token.substring(7)));
    }
}
