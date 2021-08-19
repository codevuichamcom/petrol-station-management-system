package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface FuelImportService {
    HashMap<String, Object> findAll();

    FuelImportDTO findById(int id) throws CustomNotFoundException;

    FuelImportDTO create(FuelImportDTOCreate fuelImportDTOCreate) throws CustomNotFoundException, CustomBadRequestException;

    FuelImportDTO update(int id, FuelImportDTOUpdate fuelImportDTOUpdate) throws CustomNotFoundException, CustomBadRequestException;

    FuelImportDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException;
}
