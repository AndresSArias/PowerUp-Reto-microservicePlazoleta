package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PlateHCIPage {
    private List<PlatesResponseDto> content;
    private String nameRestaurant;
    private String categoryPlates;

    private int pageActual ;
    private int elemetosForPage ;
    private int totalPages ;
    private long totalElements;
}
