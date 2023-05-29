package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class PlateMysqlAdapter implements IPlatePersistencePort {

    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;

    private final IRestaurantRepository restaurantRepository;
    private final ICategoryRepository categoryRepository;


    @Override
    public PlateEntity savePlate(Plate plate) {

        return plateRepository.save(plateEntityMapper.toEntity(plate));
    }

    @Override
    public Plate getPlateById(Long id) {
        return plateEntityMapper.toPlate (plateRepository.findById(id));
    }
}
