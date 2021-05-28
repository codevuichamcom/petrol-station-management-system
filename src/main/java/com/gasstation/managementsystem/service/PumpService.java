package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.model.dto.PumpDTO;

import java.util.List;

public interface PumpService {
    public List<PumpDTO> findAll();

    public PumpDTO findById(int id);

    public PumpDTO save(Pump pump);

    public PumpDTO delete(int id);
}
