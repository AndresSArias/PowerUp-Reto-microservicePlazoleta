package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.domain.model.Order;

import java.util.List;
import java.util.Set;

public interface IOrderServicePort {

    OrderEntity saveOrder(Order order, Set<Long> idPlates, List<Integer> quantityPlates);
}
