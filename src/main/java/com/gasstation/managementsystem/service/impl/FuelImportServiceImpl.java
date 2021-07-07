package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.FuelImport;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
import com.gasstation.managementsystem.model.mapper.FuelImportMapper;
import com.gasstation.managementsystem.repository.FuelImportRepository;
import com.gasstation.managementsystem.service.FuelImportService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FuelImportServiceImpl implements FuelImportService {
    private final FuelImportRepository fuelImportRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listFuelImportToMap(List<FuelImport> tanks) {
        List<FuelImportDTO> fuelImportDTOS = tanks.stream().map(FuelImportMapper::toFuelImportDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelImportDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<FuelImport> fuelImportBills = fuelImportRepository.findAll(pageable);
        HashMap<String, Object> map = listFuelImportToMap(fuelImportBills.getContent());
        map.put("totalElement", fuelImportBills.getTotalElements());
        map.put("totalPage", fuelImportBills.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Sort sort) {
        return listFuelImportToMap(fuelImportRepository.findAll(sort));
    }

    @Override
    public FuelImportDTO findById(int id) throws CustomNotFoundException {
        return FuelImportMapper.toFuelImportDTO(optionalValidate.getFuelImportById(id));
    }

    @Override
    public FuelImportDTO create(FuelImportDTOCreate fuelImportDTOCreate) throws CustomNotFoundException {
        FuelImport fuelImport = FuelImportMapper.toFuelImport(fuelImportDTOCreate);
        fuelImport.setTank(optionalValidate.getTankById(fuelImportDTOCreate.getTankId()));
        fuelImport.setSupplier(optionalValidate.getSupplierById(fuelImportDTOCreate.getSupplierId()));
        fuelImport = fuelImportRepository.save(fuelImport);
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }

    @Override
    public FuelImportDTO update(int id, FuelImportDTOUpdate fuelImportDTOUpdate) throws CustomNotFoundException {
        FuelImport oldFuelImport = optionalValidate.getFuelImportById(id);
        FuelImportMapper.copyNonNullToFuelImport(oldFuelImport, fuelImportDTOUpdate);
        Integer tankId = fuelImportDTOUpdate.getTankId();
        Integer supplierId = fuelImportDTOUpdate.getSupplierId();
        if (tankId != null) {
            oldFuelImport.setTank(optionalValidate.getTankById(tankId));
        }
        if (supplierId != null) {
            oldFuelImport.setSupplier(optionalValidate.getSupplierById(supplierId));
        }
        oldFuelImport = fuelImportRepository.save(oldFuelImport);
        return FuelImportMapper.toFuelImportDTO(oldFuelImport);
    }


    @Override
    public FuelImportDTO delete(int id) throws CustomNotFoundException {
        FuelImport fuelImport = optionalValidate.getFuelImportById(id);
        fuelImportRepository.delete(fuelImport);
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }
}
