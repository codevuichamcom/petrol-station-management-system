package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface PumpService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll(Sort sort);

    PumpDTO findById(int id) throws CustomNotFoundException;

    PumpDTO create(PumpDTOCreate pumpDTOCreate) throws CustomNotFoundException;

    PumpDTO update(int id, PumpDTOUpdate pumpDTOUpdate) throws CustomNotFoundException;

    PumpDTO delete(int id) throws CustomNotFoundException;
}
