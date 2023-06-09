package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.request;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateStateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateRequestMapper {
    @Mapping(target = "category.name",source = "nameCategory")
    @Mapping(target = "restaurant.nit",source = "nitRestaurant")
    Plate toPlate (PlateRequestDto plateRequestDto);

    @Mapping(target = "id",source = "idPlate")
    @Mapping(target = "restaurant.nit",source = "nitRestaurant")
    Plate toPlate (PlateUpdateRequestDto plateRequestDto);

    @Mapping(target = "id",source = "idPlate")
    @Mapping(target = "restaurant.nit",source = "nitRestaurant")
    Plate toPlate (PlateStateUpdateRequestDto plateStateUpdateRequestDto);


}
