package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOCreate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOUpdate;
import com.gasstation.managementsystem.model.mapper.SupplierMapper;
import com.gasstation.managementsystem.repository.SupplierRepository;
import com.gasstation.managementsystem.service.SupplierService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listSupplierToMap(List<Supplier> suppliers) {
        List<SupplierDTO> supplierDTOS = suppliers.stream().map(SupplierMapper::toSupplierDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", supplierDTOS);
        return map;
    }


    @Override
    public HashMap<String, Object> findAll() {
        return listSupplierToMap(supplierRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    public SupplierDTO findById(int id) throws CustomNotFoundException {
        return SupplierMapper.toSupplierDTO(optionalValidate.getSupplierById(id));
    }

    @Override
    public SupplierDTO create(SupplierDTOCreate supplierDTOCreate) throws CustomDuplicateFieldException {
        checkDuplicate(supplierDTOCreate.getPhone());
        Supplier supplier = SupplierMapper.toSupplier(supplierDTOCreate);
        trimString(supplier);
        supplier = supplierRepository.save(supplier);
        return SupplierMapper.toSupplierDTO(supplier);
    }

    private void trimString(Supplier supplier) {
        supplier.setName(StringUtils.trim(supplier.getName()));
        supplier.setAddress(StringUtils.trim(supplier.getAddress()));
        supplier.setNote(StringUtils.trim(supplier.getNote()));
    }

    private void checkDuplicate(String phone) throws CustomDuplicateFieldException {
        phone = StringUtils.trim(phone);
        if (phone == null) return;
        Optional<Supplier> supplier = supplierRepository.findByPhone(phone);
        if (supplier.isPresent()) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("duplicate").field("phone").message("Phone is duplicate").table("supplier_table").build());
        }
    }

    @Override
    public SupplierDTO update(int id, SupplierDTOUpdate supplierDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Supplier oldSupplier = optionalValidate.getSupplierById(id);
        String phone = StringUtils.trim(supplierDTOUpdate.getPhone());
        if (phone != null && phone.equalsIgnoreCase(oldSupplier.getPhone())) {
            phone = null;
        }
        checkDuplicate(phone);
        SupplierMapper.copyNonNullToSupplier(oldSupplier, supplierDTOUpdate);
        trimString(oldSupplier);
        oldSupplier = supplierRepository.save(oldSupplier);
        return SupplierMapper.toSupplierDTO(oldSupplier);
    }


    @Override
    public SupplierDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        Supplier supplier = optionalValidate.getSupplierById(id);
        try {
            supplierRepository.delete(supplier);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("supplier_id")
                    .message("Supplier in use").build());
        }
        return SupplierMapper.toSupplierDTO(supplier);
    }
}
