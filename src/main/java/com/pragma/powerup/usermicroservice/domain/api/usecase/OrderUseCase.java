package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.IdPlatesQuatityPlatesException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.List;
import java.util.Set;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public OrderEntity saveOrder(Order order, Set<Long> idPlates, List<Integer> quantityPlates) {
        if (idPlates.size() != quantityPlates.size()){
            throw new IdPlatesQuatityPlatesException();
        }
        Restaurant restaurant = restaurantPersistencePort.getRestaurantByNit(order.getRestaurant().getNit());

        if (validatePlates(idPlates,))

        order.setRestaurant(restaurant);
        order.setIdChef(null);


    }
}
