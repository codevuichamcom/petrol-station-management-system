package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.HashMap;

public interface StationService {
    HashMap<String, Object> findAll(Pageable pageable, Principal principal);

    HashMap<String, Object> findAll(Principal principal);

    StationDTO findById(int id) throws CustomNotFoundException;

    StationDTO create(StationDTOCreate stationDTOCreate) throws CustomBadRequestException;

    StationDTO update(int id, StationDTOUpdate stationDTOUpdate) throws CustomBadRequestException, CustomNotFoundException;

    StationDTO delete(int id) throws CustomNotFoundException;
}
