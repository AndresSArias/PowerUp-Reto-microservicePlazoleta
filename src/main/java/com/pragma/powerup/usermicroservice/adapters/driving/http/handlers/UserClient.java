package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@HttpExchange(
        url = "http://localhost:8090/",
        accept = "application/json",
        contentType = "application/json"
)
public interface UserClient {

    @GetExchange("auth/user/{numberDocument}")
    AuthUserResponse getUserByDocument(@PathVariable String numberDocument);


}
