package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.usermicroservice.domain.model.Category;
import com.pragma.powerup.usermicroservice.domain.spi.ICategoryPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
@Transactional
public class CategoryMysqlAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public List<Category> getAllCategorys() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }
}
