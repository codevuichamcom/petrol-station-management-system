package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.model.dto.PumpDTO;
import com.gasstation.managementsystem.repository.PumpRepository;
import com.gasstation.managementsystem.service.PumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PumpServiceImpl implements PumpService {
    @Autowired
    PumpRepository pumpRepository;

    @Override
    public List<PumpDTO> findAll() {
        List<Pump> pumps = pumpRepository.findAll();
        List<PumpDTO> pumpDTOS = new ArrayList<>();
        for (Pump pump : pumps) {
            pumpDTOS.add(new PumpDTO(pump));
        }
        return pumpDTOS;
    }

    @Override
    public PumpDTO findById(int id) {
        return new PumpDTO(pumpRepository.findById(id).get());
    }

    @Override
    public PumpDTO save(Pump pump) {
        pumpRepository.save(pump);
        return new PumpDTO(pump);
    }

    @Override
    public PumpDTO delete(int id) {
        Pump pump = pumpRepository.findById(id).get();
        if (pump != null) {
            pumpRepository.delete(pump);
            return new PumpDTO(pump);
        }
        return null;
    }
}
