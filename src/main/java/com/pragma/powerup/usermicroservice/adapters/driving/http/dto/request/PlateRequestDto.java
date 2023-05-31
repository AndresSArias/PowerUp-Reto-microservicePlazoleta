package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlateRequestDto {
    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú ]+$", message = "The name of plate must have only letters")
    private String name;
    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú ]+$", message = "The name of category must have only letters")
    private String nameCategory;
    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú0-9 ]+$", message = "The description of plate must have only letters and numbers")
    private String description;
    @Pattern(regexp = "^[0-9]+$" , message = "The price of plate must have only numbers")
    @Size(max = 10, message = "The price is very hight")
    private String price;
    @Pattern(regexp = "^[0-9]+$" , message = "The NIT of restaurant must have only numbers")
    private String nitRestaurant;
    @Pattern( regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "The format of url is invalid")
    private String urlImagen;



}
