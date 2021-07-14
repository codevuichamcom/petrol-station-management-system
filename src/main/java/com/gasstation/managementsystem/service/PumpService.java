package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface PumpService {

    HashMap<String, Object> findAll();

    HashMap<String, Object> findAllByOwnerId(int ownerId, Sort sort);

    PumpDTO findById(int id) throws CustomNotFoundException;

    PumpDTO create(PumpDTOCreate pumpDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException;

    PumpDTO update(int id, PumpDTOUpdate pumpDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    PumpDTO delete(int id) throws CustomNotFoundException;

}
