package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import com.gasstation.managementsystem.model.mapper.FuelMapper;
import com.gasstation.managementsystem.repository.FuelRepository;
import com.gasstation.managementsystem.service.FuelService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FuelServiceImpl implements FuelService {
    private final FuelRepository fuelRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listFuelToMap(List<Fuel> fuels) {
        List<FuelDTO> fuelDTOS = new ArrayList<>();
        for (Fuel fuel : fuels) {
            fuelDTOS.add(FuelMapper.toFuelDTO(fuel));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Fuel> fuels = fuelRepository.findAll(pageable);
        HashMap<String, Object> map = listFuelToMap(fuels.getContent());
        map.put("totalElement", fuels.getTotalElements());
        map.put("totalPage", fuels.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listFuelToMap(fuelRepository.findAll());
    }

    @Override
    public FuelDTO findById(int id) throws CustomNotFoundException {
        return FuelMapper.toFuelDTO(optionalValidate.getFuelById(id));
    }

    @Override
    public FuelDTO create(FuelDTOCreate fuelDTOCreate) {
        Fuel fuel = FuelMapper.toFuel(fuelDTOCreate);
        fuel = fuelRepository.save(fuel);
        return FuelMapper.toFuelDTO(fuel);
    }

    @Override
    public FuelDTO update(int id, FuelDTOUpdate fuelDTOUpdate) throws CustomNotFoundException {
        Fuel fuel = optionalValidate.getFuelById(id);
        FuelMapper.copyNonNullToFuel(fuel, fuelDTOUpdate);
        fuel = fuelRepository.save(fuel);
        return FuelMapper.toFuelDTO(fuel);
    }

    @Override
    public FuelDTO delete(int id) throws CustomNotFoundException {
        Fuel fuel = optionalValidate.getFuelById(id);
        fuelRepository.delete(fuel);
        return FuelMapper.toFuelDTO(fuel);
    }
}
