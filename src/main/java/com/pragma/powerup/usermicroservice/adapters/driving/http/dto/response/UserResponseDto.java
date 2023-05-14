package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private String name;
    private String lastName;
    private String number_document;
    private String phone;
    private Date date_birth;
    private String email;
}
