package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.model.dto.SupplierDTO;
import com.gasstation.managementsystem.repository.SupplierRepository;
import com.gasstation.managementsystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Supplier> suppliers = supplierRepository.findAll(pageable);
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", supplierDTOS);
        map.put("totalElement", suppliers.getTotalElements());
        map.put("totalPage", suppliers.getTotalPages());
        return map;
    }

    @Override
    public SupplierDTO findById(int id) {
        return new SupplierDTO(supplierRepository.findById(id).get());
    }

    @Override
    public SupplierDTO save(Supplier supplier) {
        supplierRepository.save(supplier);
        return new SupplierDTO(supplier);
    }

    @Override
    public SupplierDTO delete(int id) {
        Supplier supplier = supplierRepository.findById(id).get();
        if (supplier != null) {
            supplierRepository.delete(supplier);
            return new SupplierDTO(supplier);
        }
        return null;
    }
}
