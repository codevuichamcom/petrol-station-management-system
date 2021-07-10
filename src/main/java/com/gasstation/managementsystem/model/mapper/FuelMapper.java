package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class FuelMapper {
    public static FuelDTO toFuelDTO(Fuel fuel) {
        if (fuel == null) return null;
        return FuelDTO.builder()
                .id(fuel.getId())
                .name(fuel.getName())
                .unit(fuel.getUnit())
                .price(fuel.getPrice())
                .type(fuel.getType()).build();
    }

    public static Fuel toFuel(FuelDTOCreate fuelDTOCreate) {
        if (fuelDTOCreate == null) return null;
        return Fuel.builder()
                .name(fuelDTOCreate.getName())
                .unit(fuelDTOCreate.getUnit())
                .price(fuelDTOCreate.getPrice())
                .type(fuelDTOCreate.getType()).build();
    }

    public static void copyNonNullToFuel(Fuel fuel, FuelDTOUpdate fuelDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(fuel, fuelDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
