package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.TankDTO;
import com.gasstation.managementsystem.repository.TankRepository;
import com.gasstation.managementsystem.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TankServiceImpl implements TankService {
    @Autowired
    TankRepository tankRepository;

    @Override
    public List<TankDTO> findAll() {
        List<Tank> tanks = tankRepository.findAll();
        List<TankDTO> tankDTOS = new ArrayList<>();
        for (Tank tank : tanks) {
            tankDTOS.add(new TankDTO(tank));
        }
        return tankDTOS;
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
