package com.pragma.powerup.usermicroservice.domain.api.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PlateEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.usermicroservice.domain.model.Category;
import com.pragma.powerup.usermicroservice.domain.model.Plate;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlatePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PlateUseCaseTest {

    @InjectMocks
    private PlateUseCase plateUseCase;
    @Mock
    private IPlatePersistencePort platePersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    private Plate requestPlate;
    private  Category responseCategory;
    private  Restaurant responseRestaurant;

    private Plate requestUpdatePlate;



    @BeforeEach
    void setUp() {

        //Preparación test HU3
        requestPlate = new Plate(1L, "testPlate",
                new Category(1L, "testNameCategory", "testDescriptionCategory"),
                "testDescription", 10000,
                new Restaurant(1L,"restaurantNameTest1",
                        "Calle 1 # 1-1", "111", "+573142294644",
                        "https://picsum.photos/200/300",  "1"),
                "https://picsum.photos/200/",null);

        responseCategory = new  Category(1L, "testNameCategory", "testDescriptionCategory");
        responseRestaurant =  new Restaurant(1L,"restaurantNameTest1",
                "Calle 1 # 1-1", "111", "+573142294644",
                "https://picsum.photos/200/300",  "1");

        //Preparacion HU4
        requestUpdatePlate =  new Plate(1L, null,
                new Category(null, null, null),
                "New testDescription", 50000,
                new Restaurant(null,null,
                        null, null, null,
                        null,  "1"),
                null,null);
        //Preparación HU7
        requestUpdatePlate =  new Plate(1L, null,
                new Category(null, null, null),
                null, 0,
                new Restaurant(null,null,
                        null, null, null,
                        null,  "1"),
                null,null);
    }

    @Test
    void savePlate() {
        //Preparación
        CategoryEntity resultCategory = new  CategoryEntity(1L, "testNameCategory", "testDescriptionCategory");
        RestaurantEntity resultRestaurant =  new RestaurantEntity(1L,"restaurantNameTest1",
                "Calle 1 # 1-1", "111", "+573142294644",
                "https://picsum.photos/200/300",  "1");
        PlateEntity resultPlate = new PlateEntity(1L, "testPlate",resultCategory,"testDescription", 10000,
                resultRestaurant,
                "https://picsum.photos/200/","true");
        when (categoryPersistencePort.getCategoryByName(anyString())).thenReturn(responseCategory);
        when (restaurantPersistencePort.getRestaurantByNit(anyString())).thenReturn(responseRestaurant);
        when (platePersistencePort.savePlate(requestPlate)).thenReturn(resultPlate);

        PlateEntity obtaintPlate = plateUseCase.savePlate(requestPlate, "111");

        assertEquals(resultPlate, obtaintPlate,"result was wrong");
    }

    @Test
    void updatePlate(){
        //Preparación
        CategoryEntity resultCategory = new  CategoryEntity(1L, "testNameCategory", "testDescriptionCategory");
        RestaurantEntity resultRestaurant =  new RestaurantEntity(1L,"restaurantNameTest1",
                "Calle 1 # 1-1", "111", "+573142294644",
                "https://picsum.photos/200/300",  "1");
        Plate plateUpdated = new Plate(1L, "testPlate",
                new Category(1L, "testNameCategory", "testDescriptionCategory"),
                "New testDescription", 50000,
                new Restaurant(1L,"restaurantNameTest1",
                        "Calle 1 # 1-1", "111", "+573142294644",
                        "https://picsum.photos/200/300",  "1"),
                "https://picsum.photos/200/","tue");
        PlateEntity resultUpdatePlateEntity = new PlateEntity(1L, "testPlate",resultCategory,"New testDescription", 50000,
                resultRestaurant,
                "https://picsum.photos/200/","true");
        when (platePersistencePort.getPlateById(requestUpdatePlate.getId())).thenReturn(plateUpdated);
        when (platePersistencePort.savePlate(plateUpdated)).thenReturn(resultUpdatePlateEntity);

        PlateEntity obtaintUpdatePlate = plateUseCase.updatePlate(requestUpdatePlate, "111");

        assertEquals(resultUpdatePlateEntity, obtaintUpdatePlate,"result was wrong");

    }

    @Test
    void updateStatePlate(){
        CategoryEntity resultCategory = new  CategoryEntity(1L, "testNameCategory", "testDescriptionCategory");
        RestaurantEntity resultRestaurant =  new RestaurantEntity(1L,"restaurantNameTest1",
                "Calle 1 # 1-1", "111", "+573142294644",
                "https://picsum.photos/200/300",  "1");
        Plate plateStateUpdated = new Plate(1L, "testPlate",
                new Category(1L, "testNameCategory", "testDescriptionCategory"),
                "testDescription", 10000,
                new Restaurant(1L,"restaurantNameTest1",
                        "Calle 1 # 1-1", "111", "+573142294644",
                        "https://picsum.photos/200/300",  "1"),
                "https://picsum.photos/200/","true");
        PlateEntity resultUpdatePlateEntity = new PlateEntity(1L, "testPlate",resultCategory,"testDescription", 10000,
                resultRestaurant,
                "https://picsum.photos/200/","false");
        when (platePersistencePort.getPlateById(requestUpdatePlate.getId())).thenReturn(plateStateUpdated);
        when (platePersistencePort.savePlate(plateStateUpdated)).thenReturn(resultUpdatePlateEntity);


        PlateEntity obtaintUpdatePlate = plateUseCase.updateStatePlate(requestUpdatePlate, "111");

        assertEquals(resultUpdatePlateEntity, obtaintUpdatePlate,"result was wrong");

    }
}