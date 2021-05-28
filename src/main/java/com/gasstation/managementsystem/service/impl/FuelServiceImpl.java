package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.model.dto.FuelDTO;
import com.gasstation.managementsystem.repository.FuelRepository;
import com.gasstation.managementsystem.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuelServiceImpl implements FuelService {
    @Autowired
    FuelRepository fuelRepository;

    @Override
    public List<FuelDTO> findAll() {
        List<Fuel> fuels = fuelRepository.findAll();
        List<FuelDTO> fuelDTOS = new ArrayList<>();
        for (Fuel fuel : fuels) {
            fuelDTOS.add(new FuelDTO(fuel));
        }
        return fuelDTOS;
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
