package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.FuelImportBill;
import com.gasstation.managementsystem.model.dto.FuelImportBillDTO;
import com.gasstation.managementsystem.repository.FuelImportBillRepository;
import com.gasstation.managementsystem.service.FuelImportBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuelImportBillServiceImpl implements FuelImportBillService {
    @Autowired
    FuelImportBillRepository fuelImportBillRepository;

    @Override
    public List<FuelImportBillDTO> findAll() {
        List<FuelImportBill> fuelImportBills = fuelImportBillRepository.findAll();
        List<FuelImportBillDTO> fuelImportBillDTOS = new ArrayList<>();
        for (FuelImportBill fuelImportBill : fuelImportBills) {
            fuelImportBillDTOS.add(new FuelImportBillDTO(fuelImportBill));
        }
        return fuelImportBillDTOS;
    }

    @Override
    public FuelImportBillDTO findById(int id) {
        return new FuelImportBillDTO(fuelImportBillRepository.findById(id).get());
    }

    @Override
    public FuelImportBillDTO save(FuelImportBill fuelImportBill) {
        fuelImportBillRepository.save(fuelImportBill);
        return new FuelImportBillDTO(fuelImportBill);
    }

    @Override
    public FuelImportBillDTO delete(int id) {
        FuelImportBill fuelImportBill = fuelImportBillRepository.findById(id).get();
        if (fuelImportBill != null) {
            fuelImportBillRepository.delete(fuelImportBill);
            return new FuelImportBillDTO(fuelImportBill);
        }
        return null;
    }
}
