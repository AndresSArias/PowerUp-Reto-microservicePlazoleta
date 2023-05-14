package com.pragma.powerup.usermicroservice.domain.model;

import java.time.LocalDate;


public class User {

    private Long id;
    private String name;
    private String lastName;
    private String numberDocument;
    private String phone;
    private LocalDate dateBirth;
    private String email;
    private String password;
    private Role role;

    public User(Long id, String name, String lastName, String numberDocument, String phone, LocalDate dateBirth, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.numberDocument = numberDocument;
        this.phone = phone;
        this.dateBirth = dateBirth;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumberDocument() {
        return numberDocument;
    }

    public void setNumber_document(String number_document) {
        this.numberDocument = number_document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
