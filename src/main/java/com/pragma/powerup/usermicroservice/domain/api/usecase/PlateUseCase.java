package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoAllowedUpdateException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoBelongToRestaurant;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.LenghtPageException;
import com.pragma.powerup.usermicroservice.domain.exceptions.LenghtSizeException;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwmerNoAllowedCreationException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public PlateEntity updateStatePlate(Plate plate, String ownerDNI) {
        Plate plateUpdated = platePersistencePort.getPlateById(plate.getId());

        if (!plate.getRestaurant().getNit().equals(plateUpdated.getRestaurant().getNit())){
            throw  new NoBelongToRestaurant();
        }
        if (!plateUpdated.getRestaurant().getIdPropietario().equals(ownerDNI)){
            throw new NoAllowedUpdateException();
        }

        if (plateUpdated.getActive().equals("true")){
            plateUpdated.setActive("false");
        }else{
            plateUpdated.setActive("true");
        }

        return platePersistencePort.savePlate(plateUpdated);
    }

    @Override
    public Page<Plate> getAllSpecificPlates(String nitRestaurant, String nameCategory, int page, int size) {
        if (size < 1){
            throw  new LenghtSizeException();
        }
        if (page < 0){
            throw  new LenghtPageException();
        }
        final Pageable pageable = PageRequest.of(page, size);
        return platePersistencePort.getAllSpecificPlates(nitRestaurant,nameCategory,pageable);
    }

    @Override
    public String getNameRestaurantByNit(String nitRestaurant) {
        return restaurantPersistencePort.getRestaurantByNit(nitRestaurant).getNombre();
    }
}
