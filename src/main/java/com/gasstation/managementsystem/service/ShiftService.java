package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.ShiftDTO;

import java.util.List;

public interface ShiftService {
    public List<ShiftDTO> findAll();

    public ShiftDTO findById(int id);

    public ShiftDTO save(Shift shift);

    public ShiftDTO delete(int id);
}
