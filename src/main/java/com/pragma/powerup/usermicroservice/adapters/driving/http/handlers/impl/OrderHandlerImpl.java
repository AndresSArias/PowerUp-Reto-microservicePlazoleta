package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request.IOrderRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderHandlerImpl implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;

    private final JwtProvider jwtProvider;
    @Override
    public void saveOrder(OrderRequestDto orderRequestDto, String token) {
        String idClient = jwtProvider.getIdUserFromToken(token.substring(7));
        Order order = orderRequestMapper.toOrder(orderRequestDto.getNitRestaurant(),idClient);
        Set<Long> idPlates = orderRequestMapper.toIdPlates(orderRequestDto.getIdPlates());
        List<Integer> quantityPlates = orderRequestMapper.toQuantityPlates(orderRequestDto.getQuantityPlates());
        orderServicePort.saveOrder(order, idPlates, quantityPlates);
    }
}
