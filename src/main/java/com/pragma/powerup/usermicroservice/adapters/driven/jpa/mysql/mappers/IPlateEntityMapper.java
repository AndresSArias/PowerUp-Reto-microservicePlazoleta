package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IPlateEntityMapper {
    @Mapping(target = "categoryEntity.id", source = "category.id")
    @Mapping(target = "restaurantEntity.id", source = "restaurant.id")
    PlateEntity toEntity (Plate plate);
    @Mapping(target = "category.id", source = "categoryEntity.id")
    @Mapping(target = "restaurant.id", source = "restaurantEntity.id")
    Plate toPlate (Optional<PlateEntity> plateEntity);
}
