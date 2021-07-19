package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;

public class HandOverShiftMapper {
    public static HandOverShiftDTO toHandOverShiftDTO(HandOverShift handOverShift) {
        Shift shift = handOverShift.getShift();
        Pump pump = handOverShift.getPump();
        ShiftDTO shiftDTO = shift != null ? ShiftDTO.builder().id(shift.getId()).name(shift.getName()).build() : null;
        PumpDTO pumpDTO = pump != null ? PumpDTO.builder().id(handOverShift.getId()).name(pump.getName()).build() : null;
        return HandOverShiftDTO.builder()
                .id(handOverShift.getId())
                .createdDate(handOverShift.getCreatedDate())
                .closeShiftDate(handOverShift.getClosedTime())
                .note(handOverShift.getNote())
                .shift(shiftDTO)
                .pump(pumpDTO)
                .build();
    }

    public static HandOverShift toHandOverShift(HandOverShiftDTOCreate handOverShiftDTOCreate) {
        if (handOverShiftDTOCreate == null) return null;
        return HandOverShift.builder()
                .note(handOverShiftDTOCreate.getNote()).build();
    }
}
