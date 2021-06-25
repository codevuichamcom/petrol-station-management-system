package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface FuelService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public HashMap<String, Object> findAll();

    public FuelDTO findById(int id) throws CustomNotFoundException;

    public FuelDTO create(FuelDTOCreate fuelDTOCreate) throws CustomDuplicateFieldException;

    public FuelDTO update(int id, FuelDTOUpdate fuelDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    public FuelDTO delete(int id) throws CustomNotFoundException;
}
