package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;

public class ShiftMapper {
    public static ShiftDTO toShiftDTO(Shift shift) {
        if (shift == null) return null;

        return ShiftDTO.builder()
                .id(shift.getId())
                .name(shift.getName())
                .startTime(DateTimeHelper.formatDate(shift.getStartTime(), "HH:mm"))
                .endTime(DateTimeHelper.formatDate(shift.getEndTime(), "HH:mm"))
                .build();
    }

    public static Shift toShift(ShiftDTOCreate shiftDTOCreate) {
        if (shiftDTOCreate == null) return null;
        return Shift.builder()
                .name(shiftDTOCreate.getName())
                .startTime(DateTimeHelper.toDate(shiftDTOCreate.getStartTime(), "HH:mm"))
                .endTime(DateTimeHelper.toDate(shiftDTOCreate.getEndTime(), "HH:mm")).build();
    }

    public static void copyNonNullToShift(Shift shift, ShiftDTOUpdate shiftDTOUpdate) {
        String name = shiftDTOUpdate.getName();
        String startTime = shiftDTOUpdate.getStartTime();
        String endTime = shiftDTOUpdate.getEndTime();
        if (name != null) {
            shift.setName(name);
        }
        if (startTime != null) {
            shift.setStartTime(DateTimeHelper.toDate(startTime, "HH:mm"));
        }
        if (endTime != null) {
            shift.setEndTime(DateTimeHelper.toDate(endTime, "HH:mm"));
        }
    }
}
