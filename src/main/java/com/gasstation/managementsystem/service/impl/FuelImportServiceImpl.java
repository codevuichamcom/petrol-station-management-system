package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
import com.gasstation.managementsystem.model.mapper.FuelImportMapper;
import com.gasstation.managementsystem.repository.ExpenseRepository;
import com.gasstation.managementsystem.repository.FuelImportRepository;
import com.gasstation.managementsystem.repository.TankRepository;
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

import java.util.ArrayList;
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
    private final TankRepository tankRepository;
    private final ExpenseRepository expenseRepository;

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
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        List<FuelImport> fuelImportList = new ArrayList<>();
        switch (userType.getId()) {
            case UserType.ADMIN:
                fuelImportList = fuelImportRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
                break;
            case UserType.OWNER:
                List<Station> stationList = userLoggedIn.getStationList();
                List<Integer> stationIds = new ArrayList<>();
                for (Station station : stationList) {
                    stationIds.add(station.getId());
                }
                fuelImportList = fuelImportRepository.findAllByStationIds(stationIds, Sort.by(Sort.Direction.DESC, "id"));
        }
        return listFuelImportToMap(fuelImportList);
    }

    @Override
    public FuelImportDTO findById(int id) throws CustomNotFoundException {
        return FuelImportMapper.toFuelImportDTO(optionalValidate.getFuelImportById(id));
    }

    @Override
    public FuelImportDTO create(FuelImportDTOCreate fuelImportDTOCreate) throws CustomNotFoundException, CustomBadRequestException {

        Tank tank = optionalValidate.getTankById(fuelImportDTOCreate.getTankId());
        if (fuelImportDTOCreate.getImportDate() > DateTimeHelper.getCurrentUnixTime()) {
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
        tank = tankRepository.save(tank);
        fuelImport.setTank(tank);
        fuelImport.setSupplier(optionalValidate.getSupplierById(fuelImportDTOCreate.getSupplierId()));
        User creator = userHelper.getUserLogin();
        fuelImport.setCreator(creator);
        fuelImport.setFuel(optionalValidate.getFuelById(fuelImportDTOCreate.getFuelId()));
        fuelImport = fuelImportRepository.save(fuelImport);
        Double amountPaid = fuelImportDTOCreate.getAmountPaid();
        String reasonPayExpense = fuelImportDTOCreate.getReason();
        if (amountPaid != null) {
            reasonPayExpense += fuelImport.getId() + ")";
            addExpense(amountPaid, reasonPayExpense, fuelImport);
        }
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }

    @Override
    public FuelImportDTO update(int id, FuelImportDTOUpdate fuelImportDTOUpdate) throws CustomNotFoundException, CustomBadRequestException {
        FuelImport oldFuelImport = optionalValidate.getFuelImportById(id);
        FuelImportMapper.copyNonNullToFuelImport(oldFuelImport, fuelImportDTOUpdate);
        if (fuelImportDTOUpdate.getImportDate() > DateTimeHelper.getCurrentUnixTime()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("import.date.invalid")
                    .message("Import date cannot be greater than current date")
                    .table("fuel_import_tbl").build());
        }

        Double accountsPayable = fuelImportDTOUpdate.getAccountsPayable();
        String reasonPayExpense = fuelImportDTOUpdate.getReason();
        oldFuelImport = fuelImportRepository.save(oldFuelImport);
        if (fuelImportDTOUpdate.getAccountsPayable() != null) {
            addExpense(accountsPayable, reasonPayExpense, oldFuelImport);
        }
        return FuelImportMapper.toFuelImportDTO(oldFuelImport);
    }

    private void addExpense(Double amount, String reasonPayExpense, FuelImport fuelImport) throws CustomBadRequestException {
        if (reasonPayExpense == null) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("reason.required")
                    .message("reason is required when pay expenses")
                    .table("expense_tbl").build());
        }
        Station station = fuelImport.getTank().getStation();
        Expense expense = Expense.builder()
                .amount(amount)
                .reason(reasonPayExpense)
                .station(station)
                .fuelImport(fuelImport)
                .createdDate(DateTimeHelper.getCurrentDate())
                .creator(userHelper.getUserLogin()).build();
        expenseRepository.save(expense);
    }


    @Override
    public FuelImportDTO delete(int id) throws CustomNotFoundException {
        FuelImport fuelImport = optionalValidate.getFuelImportById(id);
        fuelImportRepository.delete(fuelImport);
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }
}
