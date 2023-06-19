package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderPlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.ClientHasActiveOrderException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IOrderEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IOrderPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IOrderPlateRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.model.OrderPlate;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderPlateRepository orderPlateRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderPlateEntityMapper orderPlateEntityMapper;



    @Override
    public Order saveOrder(Order order) {
       List<OrderEntity> passOrders = orderRepository.findAllByIdClient(order.getIdClient());
        if (!validateOrders(passOrders)){
            throw new ClientHasActiveOrderException();
        }
        return orderEntityMapper.toOrder(orderRepository.save(orderEntityMapper.toOrderEntity(order)));
    }

    @Override
    public OrderPlateEntity saveRelationOrderPlate(OrderPlate orderPlate) {
        return orderPlateRepository.save(orderPlateEntityMapper.toOrderPlateEntity(orderPlate));
    }

    private boolean validateOrders(List<OrderEntity> passOrders) {
        for (OrderEntity orderEntity : passOrders) {
            if (orderEntity.getState().equals("PENDIENTE") || orderEntity.getState().equals("EN_PREPARACION") || orderEntity.getState().equals("LISTO")){
                return false;
            }
        }
        return  true;
    }
}
