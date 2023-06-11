package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.AuthUserResponse;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserClient;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {
    @InjectMocks
    private RestaurantUseCase restaurantUseCase;
    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;
    @Mock
    private IUserClient userClient;
    private Restaurant requestRestaurant;

    @BeforeEach
    public void setUp() {
        requestRestaurant = new Restaurant(1L,"restaurantNameTest1",
                "Calle 1 # 1-1", "111", "+573142294644",
                "https://picsum.photos/200/300",  "1");

    }
    @Test
    void saveRestaurant() {
        //Preparación
        RestaurantEntity resultRestaurant = new RestaurantEntity(1L,"restaurantNameTest1",
                "Calle 1 # 1-1", "111", "+573142294644",
                "https://picsum.photos/200/300",  "1");
        AuthUserResponse resultOwner = new AuthUserResponse("nameTest","111","test@email.con",
                "encryptedPasword","ROLE_OWNER");
        Map<String,String> resultHeaders = new HashMap<String, String>() {{
            put("Authorization", "Bearer x.x.x.x");
        }};

        when(userClient.getUserByDocument(requestRestaurant.getIdPropietario(), resultHeaders)).thenReturn(ResponseEntity.ok(resultOwner));
        when(restaurantPersistencePort.saveRestaurant(requestRestaurant)).thenReturn(resultRestaurant);


        RestaurantEntity obtainedRestaurant = restaurantUseCase.saveRestaurant(requestRestaurant,"Bearer x.x.x.x");

        assertEquals(resultRestaurant,obtainedRestaurant,"result was wrong");

    }

    @ParameterizedTest
    @CsvSource({
            "'nameTest',true",
            "'nameTest123',true",
            "'123nameTest',true",
            "'name123Test',true",
            "'123',false",
    })
    void validateRestaurantName(String name, boolean result) {

        requestRestaurant.setNombre(name);

        boolean obtained = restaurantUseCase.validateRestaurantName(requestRestaurant);

        assertEquals(result, obtained,"result was wrong");
    }

    @ParameterizedTest
    @CsvSource({
            "'+57 3142294643',true",
            "'+57 314 219464 3',true",
            "'+571234567890',true",
            "'+5712345678901',false",
            "'+571234567890123',false",
    })
    void validatePhone(String phone, boolean result) {
        //Preparacion
        requestRestaurant.setPhone(phone);

        //Ejecución
        boolean obtained = restaurantUseCase.validatePhone(requestRestaurant);

        //Verificación
        assertEquals(result, obtained, "result was wrong");

    }

    @Test
    void getHeaders () {
        //Preparación
        Map<String,String> resultHeaders = new HashMap<String, String>() {{
            put("Authorization", "Bearer x.x.x.x");
        }};

        Map<String, String> obtainedHeaders = restaurantUseCase.getHeaders("Bearer x.x.x.x");

        assertEquals(resultHeaders,obtainedHeaders,"result was wrong");

    }

}