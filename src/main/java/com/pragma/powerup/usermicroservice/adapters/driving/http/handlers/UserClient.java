package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public interface UserClient {

    @GetExchange("user/getUser/{numberDocument}")
    AuthUserResponse getUserByDocument(@PathVariable String numberDocument,  @RequestHeader Map<String, String> headers);


}
