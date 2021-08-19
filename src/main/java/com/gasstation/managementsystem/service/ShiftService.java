package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;

import java.util.HashMap;

public interface ShiftService {

    HashMap<String, Object> findAll();

    ShiftDTO findById(int id) throws CustomNotFoundException;

    ShiftDTO create(ShiftDTOCreate shiftDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException;

    ShiftDTO update(int id, ShiftDTOUpdate shiftDTOUpdate) throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException;

    ShiftDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException;
}
