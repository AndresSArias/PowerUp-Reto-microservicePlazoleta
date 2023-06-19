package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.IdPlatesQuatityPlatesException;
import com.pragma.powerup.usermicroservice.domain.exceptions.NonRestaurantPlateException;
import com.pragma.powerup.usermicroservice.domain.model.Order;
import com.pragma.powerup.usermicroservice.domain.model.OrderPlate;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    private final IPlatePersistencePort platePersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IPlatePersistencePort platePersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.platePersistencePort = platePersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Order saveOrder(Order order, Set<Long> idPlates, List<Integer> quantityPlates) {
        if (idPlates.size() != quantityPlates.size()){
            throw new IdPlatesQuatityPlatesException();
        }
        Restaurant restaurant = restaurantPersistencePort.getRestaurantByNit(order.getRestaurant().getNit());

        if (!validatePlates(order.getRestaurant().getNit(), idPlates)){
            throw new NonRestaurantPlateException();
        }

        order.setRestaurant(restaurant);
        order.setIdChef(null);
        order.setState("PENDIENTE");
        order.setDate(LocalDateTime.now());

        Order orderSaved = orderPersistencePort.saveOrder(order);
        saveRelationOrderPlate(orderSaved, idPlates, quantityPlates);

        return orderSaved;
    }

    private void saveRelationOrderPlate(Order orderSaved, Set<Long> idPlates, List<Integer> quantityPlates) {
        int index = 0;
        for (Long idPlate : idPlates) {
            Plate plate = platePersistencePort.getPlateById(idPlate);
            OrderPlate orderPlate = new OrderPlate(null,orderSaved,plate,quantityPlates.get(index));
            index++;
            orderPersistencePort.saveRelationOrderPlate(orderPlate);
        }
    }

    private boolean validatePlates(String nit, Set<Long> idPlates) {
        for (Long idPlate : idPlates) {
           Plate plate = platePersistencePort.getPlateById(idPlate);
           if (!plate.getRestaurant().getNit().equals(nit)){
               return false;
           }
        }
        return true;
    }
}
