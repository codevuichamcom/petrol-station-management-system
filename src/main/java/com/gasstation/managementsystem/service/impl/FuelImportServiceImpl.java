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
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
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
    public HashMap<String, Object> findAll() {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        List<FuelImport> fuelImportList = new ArrayList<>();
        switch (userType.getId()) {
            case UserType.ADMIN:
                fuelImportList = fuelImportRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
                break;
            case UserType.OWNER:
                fuelImportList = fuelImportRepository.findAllByOwnerId(userLoggedIn.getId(), Sort.by(Sort.Direction.DESC, "id"));
        }
        return listFuelImportToMap(fuelImportList);
    }

    @Override
    public FuelImportDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        FuelImport fuelImport = optionalValidate.getFuelImportById(id);
        if (userType.getId() == UserType.OWNER
                && !fuelImport.getTank().getStation().getOwner().getId().equals(userLoggedIn.getId())) { //id không phải của nó
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .message("Fuel import not of the owner")
                    .table("fuel_import_tbl").build());
        }
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }

    @Override
    @Transactional
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
        trimString(fuelImport);
        fuelImport = fuelImportRepository.save(fuelImport);
        Double amountPaid = fuelImportDTOCreate.getAmountPaid();
        String reasonPayExpense = fuelImportDTOCreate.getReason();
        if (amountPaid != null && amountPaid != 0) {
            if (reasonPayExpense != null) {
                reasonPayExpense += fuelImport.getId() + ")";
            }
            addExpense(amountPaid, reasonPayExpense, fuelImport);
        }
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }

    private void trimString(FuelImport fuelImport) {
        fuelImport.setName(StringUtils.trim(fuelImport.getName()));
        fuelImport.setNote(StringUtils.trim(fuelImport.getNote()));
    }

    @Override
    @Transactional
    public FuelImportDTO update(int id, FuelImportDTOUpdate fuelImportDTOUpdate) throws CustomNotFoundException, CustomBadRequestException {
        FuelImport oldFuelImport = optionalValidate.getFuelImportById(id);
        FuelImportMapper.copyNonNullToFuelImport(oldFuelImport, fuelImportDTOUpdate);
        Long importDate = fuelImportDTOUpdate.getImportDate();
        if (importDate != null
                && importDate > DateTimeHelper.getCurrentUnixTime()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("import.date.invalid")
                    .message("Import date cannot be greater than current date")
                    .table("fuel_import_tbl").build());
        }

        Double accountsPayable = fuelImportDTOUpdate.getAccountsPayable();
        String reasonPayExpense = fuelImportDTOUpdate.getReason();
        trimString(oldFuelImport);
        oldFuelImport = fuelImportRepository.save(oldFuelImport);
        if (accountsPayable != null && accountsPayable != 0) {
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
                .reason(StringUtils.trim(reasonPayExpense))
                .station(station)
                .fuelImport(fuelImport)
                .createdDate(DateTimeHelper.getCurrentDate())
                .creator(userHelper.getUserLogin()).build();
        expenseRepository.save(expense);
    }


    @Override
    public FuelImportDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        FuelImport fuelImport = optionalValidate.getFuelImportById(id);
        try {
            fuelImportRepository.delete(fuelImport);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("fuel_import_id")
                    .message("Fuel import in use").build());
        }
        return FuelImportMapper.toFuelImportDTO(fuelImport);
    }
}
