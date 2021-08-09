package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.FuelImport;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
import com.gasstation.managementsystem.model.mapper.FuelImportMapper;
import com.gasstation.managementsystem.repository.FuelImportRepository;
import com.gasstation.managementsystem.service.FuelImportService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FuelImportServiceImpl implements FuelImportService {
    private final FuelImportRepository fuelImportRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

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
    public HashMap<String, Object> findAll() {
        return listFuelImportToMap(fuelImportRepository.findAllFuelImport(Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    public FuelImportDTO findById(int id) throws CustomNotFoundException {
        return FuelImportMapper.toFuelImportDTO(optionalValidate.getFuelImportById(id));
    }

    @Override
    public FuelImportDTO create(FuelImportDTOCreate fuelImportDTOCreate) throws CustomNotFoundException, CustomBadRequestException {

        Tank tank = optionalValidate.getTankById(fuelImportDTOCreate.getTankId());
        if (fuelImportDTOCreate.getImportDate() > DateTimeHelper.getCurrentDate()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("import.date.invalid")
                    .message("Import date cannot be greater than current date")
                    .table("fuel_import_tbl").build());
        }
        if (!Objects.equals(fuelImportDTOCreate.getFuelId(), tank.getFuel().getId())) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("mismatch")
                    .message("Fuel type mismatch with tank")
                    .table("fuel_import_tbl").build());
        }
        if (fuelImportDTOCreate.getVolume() > tank.getVolume() - tank.getRemain()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("full")
                    .message("Tank not enough to hold")
                    .table("tank_tbl").build());
        }
        FuelImport fuelImport = FuelImportMapper.toFuelImport(fuelImportDTOCreate);

        tank.setRemain(tank.getRemain() + fuelImportDTOCreate.getVolume());
        fuelImport.setTank(tank);
        fuelImport.setSupplier(optionalValidate.getSupplierById(fuelImportDTOCreate.getSupplierId()));
        User creator = userHelper.getUserLogin();
        fuelImport.setCreator(creator);
        fuelImport.setFuel(optionalValidate.getFuelById(fuelImportDTOCreate.getFuelId()));
        fuelImport = fuelImportRepository.save(fuelImport);
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }

    @Override
    public FuelImportDTO update(int id, FuelImportDTOUpdate fuelImportDTOUpdate) throws CustomNotFoundException, CustomBadRequestException {
        FuelImport oldFuelImport = optionalValidate.getFuelImportById(id);
        FuelImportMapper.copyNonNullToFuelImport(oldFuelImport, fuelImportDTOUpdate);
        Integer tankId = fuelImportDTOUpdate.getTankId();
        Integer supplierId = fuelImportDTOUpdate.getSupplierId();
        Integer fuelId = fuelImportDTOUpdate.getFuelId();
        if (fuelImportDTOUpdate.getImportDate() > DateTimeHelper.getCurrentDate()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("import.date.invalid")
                    .message("Import date cannot be greater than current date")
                    .table("fuel_import_tbl").build());
        }


        if (tankId != null) {
            oldFuelImport.setTank(optionalValidate.getTankById(tankId));
        }
        if (supplierId != null) {
            oldFuelImport.setSupplier(optionalValidate.getSupplierById(supplierId));
        }
        if (fuelId != null) {
            if (!fuelId.equals(oldFuelImport.getTank().getFuel().getId())) {
                throw new CustomBadRequestException(CustomError.builder()
                        .code("mismatch")
                        .message("Fuel type mismatch with tank")
                        .table("fuel_import_tbl").build());
            }
            oldFuelImport.setFuel(optionalValidate.getFuelById(fuelId));
        }
        if (fuelImportDTOUpdate.getVolume() > oldFuelImport.getTank().getVolume() - oldFuelImport.getTank().getRemain()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("full")
                    .message("Tank not enough to hold")
                    .table("tank_tbl").build());
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
