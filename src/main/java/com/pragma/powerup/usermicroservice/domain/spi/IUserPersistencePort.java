package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    //Interface que conecta con la clase apter de la persisntencia
    void saveUserOwner(User user);

}
