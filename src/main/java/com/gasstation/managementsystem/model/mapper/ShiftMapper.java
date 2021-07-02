package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class ShiftMapper {
    public static ShiftDTO toShiftDTO(Shift shift) {
        if (shift == null) return null;

        return ShiftDTO.builder()
                .id(shift.getId())
                .name(shift.getName())
                .startTime(DateTimeHelper.formatDate(shift.getStartTime(), "hh:mm"))
                .endTime(DateTimeHelper.formatDate(shift.getEndTime(), "hh:mm"))
                .build();
    }

    public static Shift toShift(ShiftDTOCreate shiftDTOCreate) {
        if (shiftDTOCreate == null) return null;
        return Shift.builder()
                .startTime(shiftDTOCreate.getStartTime())
                .endTime(shiftDTOCreate.getEndTime()).build();
    }

    public static void copyNonNullToTank(Shift shift, ShiftDTOUpdate shiftDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(shift, shiftDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
