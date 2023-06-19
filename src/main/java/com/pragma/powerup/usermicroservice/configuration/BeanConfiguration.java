package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.CategoryMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.OrderMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.PlateMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.RestaurantMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.*;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserClient;
import com.pragma.powerup.usermicroservice.domain.api.ICategoryServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IPlateServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.usermicroservice.domain.api.usecase.CategoryUseCase;
import com.pragma.powerup.usermicroservice.domain.api.usecase.OrderUseCase;
import com.pragma.powerup.usermicroservice.domain.api.usecase.PlateUseCase;
import com.pragma.powerup.usermicroservice.domain.api.usecase.RestaurantUseCase;
import com.pragma.powerup.usermicroservice.domain.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final ICategoryRepository categoryRepository;
    private final IPlateRepository plateRepository;
    private final IOrderRepository orderRepository;
    private final IOrderPlateRepository orderPlateRepository;

    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IPlateEntityMapper plateEntityMapper;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderPlateEntityMapper orderPlateEntityMapper;

    private  final IUserClient userClient;




    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return  new RestaurantMysqlAdapter(restaurantRepository,restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort (){
        return new RestaurantUseCase(restaurantPersistencePort(), userClient);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort () {
        return new CategoryMysqlAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort () {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IPlatePersistencePort platePersistencePort(){
        return  new PlateMysqlAdapter(plateRepository, plateEntityMapper, restaurantRepository, categoryRepository);
    }

    @Bean
    public IPlateServicePort plateServicePort () {
        return  new PlateUseCase(platePersistencePort(), categoryPersistencePort(), restaurantPersistencePort());
    }
    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderMysqlAdapter(orderRepository,orderPlateRepository, orderEntityMapper, orderPlateEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort(), platePersistencePort(), restaurantPersistencePort());
    }

}
