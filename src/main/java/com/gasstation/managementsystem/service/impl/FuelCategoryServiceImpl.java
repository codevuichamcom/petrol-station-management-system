package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.FuelCategory;
import com.gasstation.managementsystem.model.dto.FuelCategoryDTO;
import com.gasstation.managementsystem.repository.FuelCategoryRepository;
import com.gasstation.managementsystem.service.FuelCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class FuelCategoryServiceImpl implements FuelCategoryService {
    @Autowired
    FuelCategoryRepository fuelCategoryRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<FuelCategory> fuelCategories = fuelCategoryRepository.findAll(pageable);
        List<FuelCategoryDTO> fuelCategoryDTOS = new ArrayList<>();
        for (FuelCategory supplier : fuelCategories) {
            fuelCategoryDTOS.add(new FuelCategoryDTO(supplier));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelCategoryDTOS);
        map.put("totalElement", fuelCategories.getTotalElements());
        map.put("totalPage", fuelCategories.getTotalPages());
        return map;
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
