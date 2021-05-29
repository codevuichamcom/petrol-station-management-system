package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.ShiftDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface ShiftService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public ShiftDTO findById(int id);

    public ShiftDTO save(Shift shift);

    public ShiftDTO delete(int id);
}
