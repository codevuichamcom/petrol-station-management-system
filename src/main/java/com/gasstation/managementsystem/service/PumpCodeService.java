package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTO;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOCreate;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface PumpCodeService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll();

    PumpCodeDTO findById(int id) throws CustomNotFoundException;

    PumpCodeDTO create(PumpCodeDTOCreate pumpCodeDTOCreate) throws CustomNotFoundException;

    PumpCodeDTO update(int id, PumpCodeDTOUpdate pumpCodeDTOUpdate) throws CustomNotFoundException;

    PumpCodeDTO delete(int id) throws CustomNotFoundException;
}
