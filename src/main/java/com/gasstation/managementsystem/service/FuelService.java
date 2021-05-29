package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.model.dto.FuelDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface FuelService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public FuelDTO findById(int id);

    public FuelDTO save(Fuel fuel);

    public FuelDTO delete(int id);
}
