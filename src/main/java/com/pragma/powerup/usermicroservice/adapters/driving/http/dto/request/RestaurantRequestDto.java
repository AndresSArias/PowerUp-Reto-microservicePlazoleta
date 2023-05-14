package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestDto {

    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú ]+$", message = "The name of restaurant must have only letters")
    private String nombre;

    private String direccion;

    private Long idPropietario;

    private String phone;

    private String urlLogo;

    private String nit;

}
