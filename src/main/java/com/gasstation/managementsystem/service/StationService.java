package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;

import java.util.HashMap;

public interface StationService {
    HashMap<String, Object> findAll();

    StationDTO findById(int id) throws CustomNotFoundException;

    StationDTO create(StationDTOCreate stationDTOCreate) throws CustomBadRequestException, CustomDuplicateFieldException;

    StationDTO update(int id, StationDTOUpdate stationDTOUpdate) throws CustomBadRequestException, CustomNotFoundException, CustomDuplicateFieldException;

    StationDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException;
}
