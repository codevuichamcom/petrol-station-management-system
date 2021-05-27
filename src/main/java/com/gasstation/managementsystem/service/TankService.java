package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.TankDTO;

import java.util.List;

public interface TankService {
    public List<TankDTO> findAll();

    public TankDTO findById(int id);

    public TankDTO save(Tank tank);

    public TankDTO delete(int id);
}
