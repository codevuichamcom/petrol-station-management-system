package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTOFilter;

import java.util.HashMap;

public interface PumShiftService {

    HashMap<String, Object> findAll(PumpShiftDTOFilter filter);

    PumpShiftDTO findById(int id) throws CustomNotFoundException;

    PumpShiftDTO update(int id) throws CustomNotFoundException;

    HashMap<String, Object> updateAllByStationId(int stationId) throws CustomNotFoundException;
}
