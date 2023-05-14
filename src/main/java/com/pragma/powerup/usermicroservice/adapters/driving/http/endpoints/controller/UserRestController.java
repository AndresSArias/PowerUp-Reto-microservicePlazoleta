package com.pragma.powerup.usermicroservice.adapters.driving.http.endpoints.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a new userOwner",
            responses = {
                    @ApiResponse(responseCode = "201", description = "UserOwner created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "UserOwner already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for user creation",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/createUserOwner")
    public ResponseEntity<Map<String, String>> saveUserOwner(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUserOwner(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.USEROWNER_CREATED_MESSAGE));
    }

    /*
    @Operation(summary = "Get a employee user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with employee role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/employee/{id}")
    public ResponseEntity<PersonResponseDto> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getEmployee(id));
    }

     */
    /*
    @Operation(summary = "Get a client user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with client role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/client/{id}")
    public ResponseEntity<PersonResponseDto> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getClient(id));
    }
    */
}
