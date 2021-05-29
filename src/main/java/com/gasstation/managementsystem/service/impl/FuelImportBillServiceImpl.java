package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.FuelImportBill;
import com.gasstation.managementsystem.model.dto.FuelImportBillDTO;
import com.gasstation.managementsystem.repository.FuelImportBillRepository;
import com.gasstation.managementsystem.service.FuelImportBillService;
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
public class FuelImportBillServiceImpl implements FuelImportBillService {
    @Autowired
    FuelImportBillRepository fuelImportBillRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<FuelImportBill> fuelImportBills = fuelImportBillRepository.findAll(pageable);
        List<FuelImportBillDTO> fuelImportBillDTOS = new ArrayList<>();
        for (FuelImportBill supplier : fuelImportBills) {
            fuelImportBillDTOS.add(new FuelImportBillDTO(supplier));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelImportBillDTOS);
        map.put("totalElement", fuelImportBills.getTotalElements());
        map.put("totalPage", fuelImportBills.getTotalPages());
        return map;
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
