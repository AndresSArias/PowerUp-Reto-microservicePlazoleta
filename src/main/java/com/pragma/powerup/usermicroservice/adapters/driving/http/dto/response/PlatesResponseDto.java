package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

public record PlatesResponseDto(
        String name,
        String description,
        int price,
        String urlImagen
) {
}
