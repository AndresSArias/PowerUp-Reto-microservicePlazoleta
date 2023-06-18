package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRequestDto {
    @Pattern(regexp = "^[0-9]+$" , message = "The NIT of restaurant must have only numbers")
    private String nitRestaurant;
    @Pattern(regexp = "^\\d+(,\\d+)*$", message = "Enter the id's in the form 'id,id1,id2', where id are numbers.")
    private String idPlates;
    @Pattern(regexp = "^\\d+(,\\d+)*$", message = "Enter the quantities of the plates previously entered and also with their form of input.")
    private String quantityPlates;
}
