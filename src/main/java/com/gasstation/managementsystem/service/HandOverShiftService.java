package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdateHandOverShift;

import java.util.HashMap;

public interface HandOverShiftService {

    HashMap<String, Object> findAll();

    HandOverShiftDTO findById(int id) throws CustomNotFoundException;

    HandOverShiftDTO create(HandOverShiftDTOCreate handOverShiftDTOCreate) throws CustomNotFoundException;

    HandOverShiftDTO update(int id) throws CustomNotFoundException;

    void updateAllByStationId(StationDTOUpdateHandOverShift stationDTOUpdateHandOverShift) throws CustomNotFoundException;
}
