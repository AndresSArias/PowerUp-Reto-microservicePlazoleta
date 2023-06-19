package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.ClientHasActiveOrderException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoCategoryFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoRestaurantFoundException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.*;
import com.pragma.powerup.usermicroservice.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, WRONG_CREDENTIALS_MESSAGE));
    }

    @ExceptionHandler(NoAllowedUserException.class)
    public ResponseEntity<Map<String,String>> handleNoAllowedUserException (NoAllowedUserException noAllowedUserException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_ALLOWED_USER_MESSAGE));
    }

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantAlreadyExistsException(
            RestaurantAlreadyExistsException restaurantAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, RESTAURANT_ALREADY_EXISTS_MESSAGE));
    }

    @ExceptionHandler(OwmerNoExistsException.class)
    public ResponseEntity<Map<String, String>> handleOwmerNoExistsException(
            OwmerNoExistsException owmerNoExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, OWNER_NO_EXISTS_MESSAGE));
    }

    @ExceptionHandler(RoleNotAllowedForCreationException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotAllowedForCreationException(
            RoleNotAllowedForCreationException roleNotAllowedForCreationException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_ALLOWED_MESSAGE));
    }

    @ExceptionHandler(PhoneLenghtException.class)
    public ResponseEntity<Map<String, String>> handlePhoneLenghtException(
            PhoneLenghtException phoneLenghtException) {
        return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PHONE_LENGHT_MESSAGE));
    }

    @ExceptionHandler(NameFullNumberException.class)
    public ResponseEntity<Map<String, String>> handleNameFullNumberException(
            NameFullNumberException nameFullNumberException) {
        return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NAME_RESTAURANT_EXCEPTION));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_DATA_FOUND_MESSAGE));
    }

    @ExceptionHandler(NoCategoryFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoCategoryFoundException(NoCategoryFoundException noCategoryFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_CATEGORY_FOUND_MESSAGE));
    }

    @ExceptionHandler(NoRestaurantFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoRestaurantFoundException(NoRestaurantFoundException noRestaurantFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_RESTAURANT_FOUND_MESSAGE));
    }

    @ExceptionHandler(OwmerNoAllowedCreationException.class)
    public ResponseEntity<Map<String, String>> handleOwmerNoAllowedCreationException(OwmerNoAllowedCreationException owmerNoAllowedCreationException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, OWNER_NO_ALLOWED_CREATION_MESSAGE));
    }
    @ExceptionHandler(NoPlateFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoPlateFoundException(NoPlateFoundException noPlateFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_PLATE_FOUND_MESSAGE));
    }
    @ExceptionHandler(NoBelongToRestaurant.class)
    public ResponseEntity<Map<String, String>> handleNoBelongToRestaurant(
            NoBelongToRestaurant noBelongToRestaurant) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PLATE_NO_BELONG_RESTAURANT_MESSAGE));
    }
    @ExceptionHandler(NoAllowedUpdateException.class)
    public ResponseEntity<Map<String,String>> handleNoAllowedUpdateException (NoAllowedUpdateException NoAllowedUpdateException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_ALLOWED_OWNER_UPDATE_PLATE_MESSAGE));
    }
    @ExceptionHandler(LenghtSizeException.class)
    public ResponseEntity<Map<String,String>> handleLenghtSizeException (LenghtSizeException lenghtSizeException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ILLEGAL_ARGUMENT_SIZE_PAGE_MESSAGE));
    }
    @ExceptionHandler(LenghtPageException.class)
    public ResponseEntity<Map<String,String>> handleLenghtPageException (LenghtPageException lenghtPageException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ILLEGAL_ARGUMENT_INDEX_PAGE_MESSAGE));
    }

    @ExceptionHandler(IdPlatesQuatityPlatesException.class)
    public ResponseEntity<Map<String,String>> handleIdPlatesQuatityPlatesException (IdPlatesQuatityPlatesException idPlatesQuatityPlatesException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ILLEGAL_SIZE_OF_PLATES_IN_ORDEN_MESSAGE));
    }


    @ExceptionHandler(NonRestaurantPlateException.class)
    public ResponseEntity<Map<String,String>> handleNonRestaurantPlateException (NonRestaurantPlateException nonRestaurantPlateException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NON_RESTAURANT_PLATE_MESSAGE));
    }


    @ExceptionHandler(ClientHasActiveOrderException.class)
    public ResponseEntity<Map<String,String>> handleClientHasActiveOrderException (ClientHasActiveOrderException clientHasActiveOrderException){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CLIENTE_HAS_ACTIVE_ORDER_MESSAGE));
    }
}
