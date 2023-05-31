package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoAllowedUpdateException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoBelongToRestaurant;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwmerNoAllowedCreationException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;

public class PlateUseCase implements IPlateServicePort {

    private final IPlatePersistencePort platePersistencePort;
    private  final ICategoryPersistencePort categoryPersistencePort;
    private  final IRestaurantPersistencePort restaurantPersistencePort;

    public PlateUseCase(IPlatePersistencePort platePersistencePort, ICategoryPersistencePort categoryPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.platePersistencePort = platePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public PlateEntity savePlate(Plate plate, String ownerDNI) {

        plate.setCategory(categoryPersistencePort.getCategoryByName(plate.getCategory().getName()));
        plate.setRestaurant(restaurantPersistencePort.getRestaurantByNit(plate.getRestaurant().getNit()));
        plate.setActive("true");

        if (!ownerDNI.equals(plate.getRestaurant().getIdPropietario())){
            throw new OwmerNoAllowedCreationException();
        }


        return platePersistencePort.savePlate(plate);
    }

    @Override
    public PlateEntity updatePlate(Plate plate, String ownerDNI) {

        Plate plateUpdated = platePersistencePort.getPlateById(plate.getId());

        if (!plate.getRestaurant().getNit().equals(plateUpdated.getRestaurant().getNit())){
            throw  new NoBelongToRestaurant();
        }
        if (!plateUpdated.getRestaurant().getIdPropietario().equals(ownerDNI)){
            throw new NoAllowedUpdateException();
        }

        plateUpdated.setDescription(plate.getDescription());
        plateUpdated.setPrice(plate.getPrice());

        return platePersistencePort.savePlate(plateUpdated);
    }
}
