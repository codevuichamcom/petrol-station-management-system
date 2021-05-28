package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.model.dto.SupplierDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface SupplierService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public SupplierDTO findById(int id);

    public SupplierDTO save(Supplier supplier);

    public SupplierDTO delete(int id);
}
