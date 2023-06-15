package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    List<Category> getAllCategorys();
    Category getCategoryByName(String name);
}
