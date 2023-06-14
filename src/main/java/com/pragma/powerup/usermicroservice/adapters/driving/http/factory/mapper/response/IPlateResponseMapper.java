package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.response;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlateHCIPage;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlatesResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateResponseMapper {

    PlatesResponseDto toPlateResponse(Plate plate);

    default Page<PlatesResponseDto> toResponsePage(Page<Plate> entityPage) {
        List<PlatesResponseDto> dtoList = entityPage.getContent().stream()
                .map(this::toPlateResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    @Mapping(target = "pageActual",source = "platesResponseDtos.pageable.pageNumber")
    @Mapping(target = "elemetosForPage",source = "platesResponseDtos.pageable.pageSize")
    PlateHCIPage toPlateHCIPage (Page<PlatesResponseDto> platesResponseDtos, String nameRestaurant, String categoryPlates);
    /*
    @Mapping(target = "pageActual",source = "platesResponseDtos.pageable.pageNumber")
    @Mapping(target = "elemetosForPage",source = "platesResponseDtos.pageable.pageSize")
    PlateHCIPage toPlateHCIPage (Page<PlatesResponseDto> platesResponseDtos, String nameRestaurant, String categoryPlates);
*/


}