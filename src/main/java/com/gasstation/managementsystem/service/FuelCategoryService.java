package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.FuelCategory;
import com.gasstation.managementsystem.model.dto.FuelCategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface FuelCategoryService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public FuelCategoryDTO findById(int id);

    public FuelCategoryDTO save(FuelCategory fuelCategory);

    public FuelCategoryDTO delete(int id);
}
