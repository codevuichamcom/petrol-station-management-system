package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface FuelService {

    HashMap<String, Object> findAll();

    FuelDTO findById(int id) throws CustomNotFoundException;

    FuelDTO create(FuelDTOCreate fuelDTOCreate) throws CustomDuplicateFieldException;

    FuelDTO update(int id, FuelDTOUpdate fuelDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    FuelDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException;
}
