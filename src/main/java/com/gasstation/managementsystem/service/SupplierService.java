package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOCreate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOUpdate;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface SupplierService {

    HashMap<String, Object> findAll();

    SupplierDTO findById(int id) throws CustomNotFoundException;

    SupplierDTO create(SupplierDTOCreate supplierDTOCreate) throws CustomDuplicateFieldException;

    SupplierDTO update(int id, SupplierDTOUpdate supplierDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    SupplierDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException;
}
