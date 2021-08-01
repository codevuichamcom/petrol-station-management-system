package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOFilter;

import java.util.HashMap;

public interface HandOverShiftService {

    HashMap<String, Object> findAll(HandOverShiftDTOFilter filter);

    HandOverShiftDTO findById(int id) throws CustomNotFoundException;

    HandOverShiftDTO update(int id) throws CustomNotFoundException;

    HashMap<String, Object> updateAllByStationId(int stationId) throws CustomNotFoundException;
}
