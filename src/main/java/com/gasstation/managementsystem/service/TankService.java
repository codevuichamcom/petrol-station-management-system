package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface TankService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public HashMap<String, Object> findAll();

    public TankDTO findById(int id) throws CustomNotFoundException;

    public TankDTO create(TankDTOCreate tankDTOCreate) throws CustomNotFoundException;

    public TankDTO update(int id, TankDTOUpdate tankDTOUpdate) throws CustomNotFoundException;

    public TankDTO delete(int id) throws CustomNotFoundException;
}
