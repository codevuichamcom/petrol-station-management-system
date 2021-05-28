package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.FuelCategory;
import com.gasstation.managementsystem.model.dto.FuelCategoryDTO;
import com.gasstation.managementsystem.repository.FuelCategoryRepository;
import com.gasstation.managementsystem.service.FuelCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuelCategoryServiceImpl implements FuelCategoryService {
    @Autowired
    FuelCategoryRepository fuelCategoryRepository;

    @Override
    public List<FuelCategoryDTO> findAll() {
        List<FuelCategory> fuelCategories = fuelCategoryRepository.findAll();
        List<FuelCategoryDTO> fuelCategoryDTOS = new ArrayList<>();
        for (FuelCategory fuelCategory : fuelCategories) {
            fuelCategoryDTOS.add(new FuelCategoryDTO(fuelCategory));
        }
        return fuelCategoryDTOS;
    }

    @Override
    public FuelCategoryDTO findById(int id) {
        return new FuelCategoryDTO(fuelCategoryRepository.findById(id).get());
    }

    @Override
    public FuelCategoryDTO save(FuelCategory fuelCategory) {
        fuelCategoryRepository.save(fuelCategory);
        return new FuelCategoryDTO(fuelCategory);
    }

    @Override
    public FuelCategoryDTO delete(int id) {
        FuelCategory fuelCategory = fuelCategoryRepository.findById(id).get();
        if (fuelCategory != null) {
            fuelCategoryRepository.delete(fuelCategory);
            return new FuelCategoryDTO(fuelCategory);
        }
        return null;
    }

}
