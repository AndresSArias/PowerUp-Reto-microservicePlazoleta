package com.pragma.powerup.usermicroservice.domain.model;

import jakarta.persistence.Column;

public class Restaurant {

    private Long id;

    private String nombre;

    private String direccion;

    private String idPropietario;

    private String phone;

    private String urlLogo;

    private String nit;

    public Restaurant(Long id, String nombre, String direccion, String idPropietario, String phone, String urlLogo, String nit) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.idPropietario = idPropietario;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.nit = nit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
