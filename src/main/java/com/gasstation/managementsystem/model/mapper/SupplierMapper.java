package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOCreate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class SupplierMapper {

    public static SupplierDTO toSupplierDTO(Supplier supplier) {
        if (supplier == null) return null;
        return SupplierDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .note(supplier.getNote()).build();
    }

    public static Supplier toSupplier(SupplierDTOCreate supplierDTOCreate) {
        if (supplierDTOCreate == null) return null;
        return Supplier.builder()
                .name(supplierDTOCreate.getName())
                .phone(supplierDTOCreate.getPhone())
                .address(supplierDTOCreate.getAddress())
                .note(supplierDTOCreate.getNote()).build();
    }

    public static void copyNonNullToSupplier(Supplier supplier, SupplierDTOUpdate supplierDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(supplier, supplierDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
