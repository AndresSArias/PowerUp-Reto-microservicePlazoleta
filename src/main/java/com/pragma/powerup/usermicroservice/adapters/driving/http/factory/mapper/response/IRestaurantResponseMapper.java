package com.pragma.powerup.usermicroservice.adapters.driving.http.factory.mapper.response;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    RestaurantResponseDto toRestaurantResponse (Restaurant restaurant);
    //Page<RestaurantResponseDto> toResponsePage (Page<Restaurant> restaurantPage);

    default Page<RestaurantResponseDto> toResponsePage(Page<Restaurant> entityPage) {
        List<RestaurantResponseDto> dtoList = entityPage.getContent().stream()
                .map(this::toRestaurantResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }
}
