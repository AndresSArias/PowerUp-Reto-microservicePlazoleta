package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

public record AuthUserResponse(
        String name,
        String numberDocument,
        String email,
        String password,
        String role
) {
}
