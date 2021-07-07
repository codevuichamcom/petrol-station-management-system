package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.FuelImport;
import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class FuelImportMapper {

    public static FuelImportDTO toFuelImportDTO(FuelImport fuelImport) {
        if (fuelImport == null) return null;
        Tank tank = fuelImport.getTank();
        Supplier supplier = fuelImport.getSupplier();
        TankDTO tankDTO = tank != null ? TankDTO.builder().id(tank.getId()).name(tank.getName()).build() : null;
        SupplierDTO supplierDTO = supplier != null ? SupplierDTO.builder().id(supplier.getId()).name(supplier.getName()).build() : null;
        return FuelImportDTO.builder()
                .id(fuelImport.getId())
                .name(fuelImport.getName())
                .date(DateTimeHelper.formatDate(fuelImport.getDate(), "yyyy-MM-dd"))
                .volume(fuelImport.getVolume())
                .unitPrice(fuelImport.getUnitPrice())
                .paid(fuelImport.getPaid())
                .liability(fuelImport.getLiability())
                .vatPercent(fuelImport.getVatPercent())
                .note(fuelImport.getNote())
                .tank(tankDTO)
                .supplier(supplierDTO).build();
    }

    public static FuelImport toFuelImport(FuelImportDTOCreate fuelImportDTOCreate) {
        if (fuelImportDTOCreate == null) return null;
        return FuelImport.builder()
                .name(fuelImportDTOCreate.getName())
                .date(fuelImportDTOCreate.getDate())
                .volume(fuelImportDTOCreate.getVolume())
                .unitPrice(fuelImportDTOCreate.getUnitPrice())
                .paid(fuelImportDTOCreate.getPaid())
                .liability(fuelImportDTOCreate.getLiability())
                .vatPercent(fuelImportDTOCreate.getVatPercent())
                .note(fuelImportDTOCreate.getNote())
                .build();
    }

    public static void copyNonNullToFuelImport(FuelImport fuelImport, FuelImportDTOUpdate fuelImportDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(fuelImport, fuelImportDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
