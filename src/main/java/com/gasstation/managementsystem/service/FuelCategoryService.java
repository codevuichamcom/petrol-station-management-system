package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.FuelCategory;
import com.gasstation.managementsystem.model.dto.FuelCategoryDTO;

import java.util.List;

public interface FuelCategoryService {
    public List<FuelCategoryDTO> findAll();

    public FuelCategoryDTO findById(int id);

    public FuelCategoryDTO save(FuelCategory fuelCategory);

    public FuelCategoryDTO delete(int id);
}
