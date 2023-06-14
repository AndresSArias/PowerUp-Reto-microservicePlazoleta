package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoCategoryFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoRestaurantFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IPlateEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlateRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoPlateFoundException;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

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

        Optional<PlateEntity> plateEntity= plateRepository.findById(id);

        if (!plateEntity.isPresent()){
            throw new NoPlateFoundException();
        }

        return plateEntityMapper.toPlate (plateEntity.get());
    }

    @Override
    public Page<Plate> getAllSpecificPlates (String nitRestaurant, String nameCategory, Pageable pageable){
        Page<PlateEntity> plateEntityPage;
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findByNit(nitRestaurant);
        if (!restaurantEntity.isPresent()){
            throw new NoRestaurantFoundException();
        }
        if (nameCategory.equals("All")){
            plateEntityPage = plateRepository.findAllByRestaurantEntity(restaurantEntity.get(),pageable);
            System.out.print(plateEntityPage);
        }else{
            Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(nameCategory);
            if(!categoryEntity.isPresent()){
                throw new NoCategoryFoundException();
            }else{
                plateEntityPage = plateRepository.findAllByRestaurantEntityAndCategoryEntity(restaurantEntity.get(),categoryEntity.get(),pageable);
                System.out.print(plateEntityPage);
            }
        }

        if (plateEntityPage.isEmpty()){
            throw new NoDataFoundException();
        }

        return plateEntityMapper.toPlatePage (plateEntityPage);
    }

}
