package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class FuelImportMapper {

    public static FuelImportDTO toFuelImportDTO(FuelImport fuelImport) {
        if (fuelImport == null) return null;
        Tank tank = fuelImport.getTank();
        Supplier supplier = fuelImport.getSupplier();
        User creator = fuelImport.getCreator();
        Fuel fuel = fuelImport.getFuel();
        UserDTO creatorDTO = creator != null ? UserDTO.builder().id(creator.getId()).name(creator.getName()).build() : null;
        FuelDTO fuelDTO = fuel != null ? FuelDTO.builder().id(fuel.getId()).name(fuel.getName()).build() : null;
        Station station = tank != null ? tank.getStation() : null;
        User owner = station != null ? station.getOwner() : null;
        UserDTO ownerDTO = owner != null ? UserDTO.builder().id(owner.getId()).name(owner.getName()).build() : null;
        StationDTO stationDTO = station != null ? StationDTO.builder().id(station.getId())
                .name(station.getName())
                .owner(ownerDTO).build() : null;
        TankDTO tankDTO = tank != null ? TankDTO.builder()
                .id(tank.getId())
                .name(tank.getName())
                .station(stationDTO).build() : null;
        SupplierDTO supplierDTO = supplier != null ? SupplierDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .phone(supplier.getPhone()).build() : null;
        return FuelImportDTO.builder()
                .id(fuelImport.getId())
                .name(fuelImport.getName())
                .createdDate(fuelImport.getCreatedDate())
                .importDate(fuelImport.getImportDate())
                .volume(fuelImport.getVolume())
                .unitPrice(fuelImport.getUnitPrice())
                .amountPaid(fuelImport.getAmountPaid())
                .vatPercent(fuelImport.getVatPercent())
                .note(fuelImport.getNote())
                .tank(tankDTO)
                .supplier(supplierDTO)
                .creator(creatorDTO)
                .fuel(fuelDTO).build();
    }

    public static FuelImport toFuelImport(FuelImportDTOCreate fuelImportDTOCreate) {
        if (fuelImportDTOCreate == null) return null;
        return FuelImport.builder()
                .name(fuelImportDTOCreate.getName())
                .createdDate(DateTimeHelper.getCurrentDate())
                .importDate(fuelImportDTOCreate.getImportDate())
                .volume(fuelImportDTOCreate.getVolume())
                .unitPrice(fuelImportDTOCreate.getUnitPrice())
                .amountPaid(fuelImportDTOCreate.getAmountPaid())
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
