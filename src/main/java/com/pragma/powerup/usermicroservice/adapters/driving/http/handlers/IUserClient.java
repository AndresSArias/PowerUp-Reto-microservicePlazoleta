package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;



@HttpExchange(
        url = "http://localhost:8090/",
        accept = "application/json",
        contentType = "application/json"
)
public interface IUserClient {

    @GetExchange("user/getUser/{numberDocument}")
    ResponseEntity<AuthUserResponse> getUserByDocument(@PathVariable String numberDocument, @RequestHeader Map<String, String> headers);


}
