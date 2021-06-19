package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class PumpMapper {
    public static PumpDTO toPumpDTO(Pump pump) {
        return PumpDTO.builder()
                .id(pump.getId())
                .name(pump.getName())
                .note(pump.getNote()).build();
    }

    public static Pump toPump(PumpDTOCreate pumpDTOCreate) {
        return Pump.builder()
                .name(pumpDTOCreate.getName())
                .note(pumpDTOCreate.getNote()).build();
    }

    public static void copyNonNullToFuel(Pump pump, PumpDTOUpdate pumpDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(pump, pumpDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
