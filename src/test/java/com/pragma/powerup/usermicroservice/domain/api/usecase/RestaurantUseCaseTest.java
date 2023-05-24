package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.UserClient;
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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {
    @InjectMocks
    private RestaurantUseCase restaurantUseCase;
    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;
    @Mock
    private UserClient userClient;
    private Restaurant requestRestaurant;
    @BeforeEach
    void setUp() {
        requestRestaurant = new Restaurant(1L,"restaurantNameTest1",
                "Calle 1 # 1-1", "111", "+573142294644",
                "https://picsum.photos/200/300",  "1");
    }
    @Test
    void saveRestaurant() {
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
    }

}