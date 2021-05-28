package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.PumpCode;
import com.gasstation.managementsystem.model.dto.PumpCodeDTO;

import java.util.List;

public interface PumpCodeService {
    public List<PumpCodeDTO> findAll();

    public PumpCodeDTO findById(int id);

    public PumpCodeDTO save(PumpCode pumpCode);

    public PumpCodeDTO delete(int id);
}
