package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.model.dto.PumpDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface PumpService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public PumpDTO findById(int id);

    public PumpDTO save(Pump pump);

    public PumpDTO delete(int id);
}
