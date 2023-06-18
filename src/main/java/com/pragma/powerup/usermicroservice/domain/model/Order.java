package com.pragma.powerup.usermicroservice.domain.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private String idClient;
    private LocalDateTime date;
    private String state;
    private String idChef;
    private Restaurant restaurant;

    public Order(Long id, String idClient, LocalDateTime date, String state, String idChef, Restaurant restaurant) {
        this.id = id;
        this.idClient = idClient;
        this.date = date;
        this.state = state;
        this.idChef = idChef;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIdChef() {
        return idChef;
    }

    public void setIdChef(String idChef) {
        this.idChef = idChef;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
