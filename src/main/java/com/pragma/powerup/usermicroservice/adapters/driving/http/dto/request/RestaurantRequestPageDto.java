package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestPageDto {

    @NotNull
    @Min(value = 0, message = "El valor del número de la página de restaurantes a consultar debe ser mayor o igual a cero")
    private int page;

    @NotNull
    @Min(value = 0, message = "El valor del número de restaurantes por página debe ser mayor o igual a cero")
    private int size;

}
