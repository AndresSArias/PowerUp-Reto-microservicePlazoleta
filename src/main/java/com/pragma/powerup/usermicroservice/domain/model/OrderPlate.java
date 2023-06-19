package com.pragma.powerup.usermicroservice.domain.model;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderPlate {
    private Long id;

    private Order order;

    private Plate plate;

    private int quantity;

    public OrderPlate(Long id, Order order, Plate plate, int quantity) {
        this.id = id;
        this.order = order;
        this.plate = plate;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
