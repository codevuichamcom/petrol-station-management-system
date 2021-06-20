package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.model.dto.FuelDTO;
import com.gasstation.managementsystem.repository.FuelRepository;
import com.gasstation.managementsystem.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class FuelServiceImpl implements FuelService {
    @Autowired
    FuelRepository fuelRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Fuel> fuels = fuelRepository.findAll(pageable);
        List<FuelDTO> fuelDTOS = new ArrayList<>();
        for (Fuel supplier : fuels) {
            fuelDTOS.add(new FuelDTO(supplier));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelDTOS);
        map.put("totalElement", fuels.getTotalElements());
        map.put("totalPage", fuels.getTotalPages());
        return map;
    }

    @Override
    public FuelDTO findById(int id) {
        return new FuelDTO(fuelRepository.findById(id).get());
    }

    @Override
    public FuelDTO save(Fuel fuel) {
        fuelRepository.save(fuel);
        return new FuelDTO(fuel);
    }

    @Override
    public FuelDTO delete(int id) {
        Fuel fuel = fuelRepository.findById(id).get();
        if (fuel != null) {
            fuelRepository.delete(fuel);
            return new FuelDTO(fuel);
        }
        return null;
    }
}
