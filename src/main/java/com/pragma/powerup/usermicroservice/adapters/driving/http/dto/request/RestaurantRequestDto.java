package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestDto {

    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú0-9 ]+$", message = "The name of restaurant must have only letters and numbers")
    private String nombre;
    @Pattern(regexp = "^[A-Za-z0-9\\s.,#-]+$", message = "The direction must have only numbers, letters, # , -")
    private String direccion;
    @Pattern(regexp = "^[0-9]+$" , message = "The DNI of owner must have only numbers")
    private String idPropietario;
    @Pattern(regexp = "^[+]?[0-9]+(\s[+]?[0-9]+)?$" , message = "The phone must have only numbers and country code with '+'")
    private String phone;

    @Pattern( regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "The format of url is invalid")
    private String urlLogo;
    @Pattern(regexp = "^[0-9]+$" , message = "The NIT of restaurant must have only numbers")
    private String nit;

}
