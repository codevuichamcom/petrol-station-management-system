package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.HashMap;

public interface StationService {
    public HashMap<String, Object> findAll(Pageable pageable, Principal principal);

    public HashMap<String, Object> findAll(Principal principal);

    public StationDTO findById(int id);

    public StationDTO create(StationDTOCreate stationDTOCreate) throws CustomBadRequestException;

    public StationDTO update(int id, StationDTOUpdate stationDTOUpdate) throws CustomBadRequestException;

    public StationDTO delete(int id);
}
