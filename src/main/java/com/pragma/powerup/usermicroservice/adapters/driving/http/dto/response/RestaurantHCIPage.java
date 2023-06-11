package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class RestaurantHCIPage {

    private List<RestaurantResponseDto> content;
    private int pageActual ;
    private int elemetosForPage ;
    private int totalPages ;
    private long totalElements;

}
