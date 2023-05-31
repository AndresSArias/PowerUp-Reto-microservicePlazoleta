package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlateUpdateRequestDto {

    @Pattern(regexp = "^[0-9]+$" , message = "The NIT of restaurant must have only numbers")
    private String nitRestaurant;
    @Pattern(regexp = "^[0-9]+$" , message = "The id of plate must have only numbers")
    private String idPlate;
    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú0-9 ]+$", message = "The description of plate must have only letters and numbers")
    private String description;
    @Pattern(regexp = "^[0-9]+$" , message = "The price of plate must have only numbers")
    @Size(max = Integer.MAX_VALUE, message = "The price is very hight")
    private String price;
}
