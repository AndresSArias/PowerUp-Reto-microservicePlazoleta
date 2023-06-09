package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class PlateStateUpdateRequestDto {
    @Pattern(regexp = "^[0-9]+$" , message = "The NIT of restaurant must have only numbers")
    private String nitRestaurant;
    @Pattern(regexp = "^[0-9]+$" , message = "The id of plate must have only numbers")
    private String idPlate;
}
