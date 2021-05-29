package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.TankDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface TankService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public TankDTO findById(int id);

    public TankDTO save(Tank tank);

    public TankDTO delete(int id);
}
