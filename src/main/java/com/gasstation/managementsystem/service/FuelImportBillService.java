package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.FuelImportBill;
import com.gasstation.managementsystem.model.dto.FuelImportBillDTO;

import java.util.List;

public interface FuelImportBillService {
    public List<FuelImportBillDTO> findAll();

    public FuelImportBillDTO findById(int id);

    public FuelImportBillDTO save(FuelImportBill fuelImportBill);

    public FuelImportBillDTO delete(int id);
}
