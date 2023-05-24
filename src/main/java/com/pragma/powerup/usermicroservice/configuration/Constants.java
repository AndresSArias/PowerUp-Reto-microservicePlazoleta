package com.pragma.powerup.usermicroservice.configuration;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long ADMIN_ROLE_ID = 1L;
    public static final Long OWNER_ROLE_ID = 2L;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credential";
    public static final String RESTAURANT_CREATED_MESSAGE = "Restaurant created succesfully";
    public static final String NO_ALLOWED_USER_MESSAGE = "The user of token is no authorized";
    public static final String RESTAURANT_ALREADY_EXISTS_MESSAGE = "A restaurant already exists with the NIT number provided";
    public static final String OWNER_NO_EXISTS_MESSAGE = "Owner no exists with idPropietario";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create restaurant with this role of idPropietario";
    public static final String PHONE_LENGHT_MESSAGE = "The lenght of Phone is over 13";
    public static final String NAME_RESTAURANT_EXCEPTION = "The name of restaurant cant be only numbers";
    public static final String ROLES = "roles";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
