package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú ]+$", message = "The name must have only letters")
    private String name;

    @Pattern(regexp = "^[A-Za-zÁ-Úá-ú ]+$", message = "The last name must have only letters")
    private String lastName;

    @Pattern(regexp = "^[0-9]+$" , message = "The DNI must have only numbers")
    private String numberDocument;

    @Pattern(regexp = "^[+]?[0-9]+(\s[+]?[0-9]+)?$" , message = "The phone must have only numbers and country code with '+'")
    private String phone;

    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[0-2])-\\d{4}$")
    private String dateBirth;

    @Email
    private String email;

    @NotBlank
    private String password;
}
