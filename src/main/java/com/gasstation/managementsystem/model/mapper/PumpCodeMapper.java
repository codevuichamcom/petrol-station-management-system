package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.PumpCode;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTO;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOCreate;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class PumpCodeMapper {
    public static PumpCodeDTO toPumpCodeDTO(PumpCode pumpCode) {
        return PumpCodeDTO.builder()
                .id(pumpCode.getId())
                .time(DateTimeHelper.toUnixTime(pumpCode.getTime()))
                .numberOfLiters(pumpCode.getNumberOfLiters())
                .pricePerLiter(pumpCode.getPricePerLiter()).build();
    }

    public static PumpCode toPumpCode(PumpCodeDTOCreate pumpCodeDTOCreate) {
        return PumpCode.builder()
                .time(DateTimeHelper.toDate(pumpCodeDTOCreate.getTime()))
                .numberOfLiters(pumpCodeDTOCreate.getNumberOfLiters())
                .pricePerLiter(pumpCodeDTOCreate.getPricePerLiter()).build();
    }

    public static void copyNonNullToFuel(PumpCode pumpCode, PumpCodeDTOUpdate pumpCodeDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(pumpCode, pumpCodeDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
