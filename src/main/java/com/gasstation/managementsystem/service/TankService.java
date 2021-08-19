package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface TankService {

    HashMap<String, Object> findAll();

    TankDTO findById(int id) throws CustomNotFoundException;

    TankDTO create(TankDTOCreate tankDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException;

    TankDTO update(int id, TankDTOUpdate tankDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    TankDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException;
}
