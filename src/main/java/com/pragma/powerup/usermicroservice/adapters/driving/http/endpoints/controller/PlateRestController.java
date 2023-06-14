package com.pragma.powerup.usermicroservice.adapters.driving.http.endpoints.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateStateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PlateUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlateHCIPage;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.PlatesResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantHCIPage;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPlateHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
@RestController
@RequestMapping("/plate")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class PlateRestController {

    private final IPlateHandler plateHandler;

    @Operation(summary = "Add a new plate in restaurant",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plate created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Plate already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for user creation",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/createPlate")
    public ResponseEntity<Map<String, String>> savePlate(@Valid @RequestBody PlateRequestDto plateRequestDto, @RequestHeader("Authorization") String token) {
        plateHandler.savePlate(plateRequestDto, token);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_CREATED_MESSAGE));
    }


    @Operation(summary = "Update an existing plate in restaurant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plate updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "Plate not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for plate update",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PutMapping("/updatePlate")
    public ResponseEntity<Map<String, String>> updatePlate(@Valid @RequestBody PlateUpdateRequestDto plateUpdateRequestDto, @RequestHeader("Authorization") String token) {
        plateHandler.updatePlate(plateUpdateRequestDto, token);
        return ResponseEntity.ok().body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_UPDATED_MESSAGE));
    }

    @Operation(summary = "State update an existing plate in restaurant",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plate state updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "Plate not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for plate update",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PutMapping("/updateStatePlate")
    public ResponseEntity<Map<String, String>> updateStatePlate(@Valid @RequestBody PlateStateUpdateRequestDto plateStateUpdateRequestDto, @RequestHeader("Authorization") String token) {
        plateHandler.updateStatePlate(plateStateUpdateRequestDto, token);
        return ResponseEntity.ok().body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PLATE_UPDATED_MESSAGE));
    }

    @Operation(summary = "Get all plates of the restaurant for category and size and number of page",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All specific plates returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PlateHCIPage.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/getPlates/{nitRestaurant}/{nameCategory}/{size}/{page}")
    public ResponseEntity<PlateHCIPage> getAllRestaurants(@PathVariable String nitRestaurant, @PathVariable String nameCategory, @PathVariable int size, @PathVariable int page) {
        return ResponseEntity.ok(plateHandler.getAllSpecificPlates(nitRestaurant,nameCategory,page,size));
    }


}
