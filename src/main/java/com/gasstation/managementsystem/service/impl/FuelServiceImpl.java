package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import com.gasstation.managementsystem.model.mapper.FuelMapper;
import com.gasstation.managementsystem.repository.FuelRepository;
import com.gasstation.managementsystem.service.FuelService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuelServiceImpl implements FuelService {
    private final FuelRepository fuelRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listFuelToMap(List<Fuel> fuels) {
        List<FuelDTO> fuelDTOS = fuels.stream().map(FuelMapper::toFuelDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listFuelToMap(fuelRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    public FuelDTO findById(int id) throws CustomNotFoundException {
        return FuelMapper.toFuelDTO(optionalValidate.getFuelById(id));
    }

    @Override
    public FuelDTO create(FuelDTOCreate fuelDTOCreate) throws CustomDuplicateFieldException {
        checkDuplicate(fuelDTOCreate.getName());
        Fuel fuel = FuelMapper.toFuel(fuelDTOCreate);
        trimString(fuel);
        fuel = fuelRepository.save(fuel);
        return FuelMapper.toFuelDTO(fuel);
    }

    private void trimString(Fuel fuel) {
        fuel.setName(StringUtils.trim(fuel.getName()));
    }

    private void checkDuplicate(String name) throws CustomDuplicateFieldException {
        name = StringUtils.trim(name);
        if (name != null) {
            Optional<Fuel> fuelOptional = fuelRepository.findByNameContainingIgnoreCase(name);
            if (fuelOptional.isPresent()) {
                throw new CustomDuplicateFieldException(CustomError.builder()
                        .code("duplicate").field("name").message("Duplicate field name").build());
            }
        }
    }

    @Override
    public FuelDTO update(int id, FuelDTOUpdate fuelDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Fuel oldFuel = optionalValidate.getFuelById(id);
        String name = StringUtils.trim(fuelDTOUpdate.getName());
        if (name != null && !name.equalsIgnoreCase(oldFuel.getName())) {
            checkDuplicate(name);
        }
        FuelMapper.copyNonNullToFuel(oldFuel, fuelDTOUpdate);
        trimString(oldFuel);
        oldFuel = fuelRepository.save(oldFuel);
        return FuelMapper.toFuelDTO(oldFuel);
    }

    @Override
    public FuelDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        Fuel fuel = optionalValidate.getFuelById(id);
        try {
            fuelRepository.delete(fuel);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("fuel_id")
                    .message("Fuel in use").build());
        }
        return FuelMapper.toFuelDTO(fuel);
    }
}
