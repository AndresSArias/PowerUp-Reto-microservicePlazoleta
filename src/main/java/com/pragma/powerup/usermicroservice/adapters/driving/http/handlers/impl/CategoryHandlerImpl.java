package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.response.ICategoryResponseMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.ICategoryHandler;
import com.pragma.powerup.usermicroservice.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryHandlerImpl implements ICategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;
    @Override
    public List<CategoryResponseDto> getAllCategorys() {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategorys());
    }
}
