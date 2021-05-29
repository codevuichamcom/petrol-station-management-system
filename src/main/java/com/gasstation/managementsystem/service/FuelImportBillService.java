package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.FuelImportBill;
import com.gasstation.managementsystem.model.dto.FuelImportBillDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface FuelImportBillService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public FuelImportBillDTO findById(int id);

    public FuelImportBillDTO save(FuelImportBill fuelImportBill);

    public FuelImportBillDTO delete(int id);
}
