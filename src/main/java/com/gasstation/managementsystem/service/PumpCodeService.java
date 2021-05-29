package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.PumpCode;
import com.gasstation.managementsystem.model.dto.PumpCodeDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface PumpCodeService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public PumpCodeDTO findById(int id);

    public PumpCodeDTO save(PumpCode pumpCode);

    public PumpCodeDTO delete(int id);
}
