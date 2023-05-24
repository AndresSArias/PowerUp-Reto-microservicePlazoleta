package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.NoAllowedUserException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.exceptions.RestaurantAlreadyExistsException;
import com.pragma.powerup.usermicroservice.domain.exceptions.NameFullNumberException;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwmerNoExistsException;
import com.pragma.powerup.usermicroservice.domain.exceptions.PhoneLenghtException;
import com.pragma.powerup.usermicroservice.domain.exceptions.RoleNotAllowedForCreationException;
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
import static com.pragma.powerup.usermicroservice.configuration.Constants.PHONE_LENGHT_MESSAGE;

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
            NameFullNumberException NameFullNumberException) {
        return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NAME_RESTAURANT_EXCEPTION));
    }

}
