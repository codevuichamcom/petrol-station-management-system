package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.TankDTO;
import com.gasstation.managementsystem.repository.TankRepository;
import com.gasstation.managementsystem.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TankServiceImpl implements TankService {
    @Autowired
    TankRepository tankRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Tank> tanks = tankRepository.findAll(pageable);
        List<TankDTO> supplierDTOS = new ArrayList<>();
        for (Tank supplier : tanks) {
            supplierDTOS.add(new TankDTO(supplier));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", supplierDTOS);
        map.put("totalElement", tanks.getTotalElements());
        map.put("totalPage", tanks.getTotalPages());
        return map;
    }

    @Override
    public TankDTO findById(int id) {
        return new TankDTO(tankRepository.findById(id).get());
    }

    @Override
    public TankDTO save(Tank tank) {
        tankRepository.save(tank);
        return new TankDTO(tank);
    }

    @Override
    public TankDTO delete(int id) {
        Tank tank = tankRepository.findById(id).get();
        if (tank != null) {
            tankRepository.delete(tank);
            return new TankDTO(tank);
        }
        return null;
    }
}
