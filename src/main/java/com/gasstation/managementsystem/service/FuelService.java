package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.model.dto.FuelDTO;

import java.util.List;

public interface FuelService {
    public List<FuelDTO> findAll();

    public FuelDTO findById(int id);

    public FuelDTO save(Fuel fuel);

    public FuelDTO delete(int id);
}
