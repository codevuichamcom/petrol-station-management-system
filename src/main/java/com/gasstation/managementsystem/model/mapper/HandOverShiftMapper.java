package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;

public class HandOverShiftMapper {
    public static HandOverShiftDTO toHandOverShiftDTO(HandOverShift handOverShift) {
        Shift shift = handOverShift.getShift();
        Pump pump = handOverShift.getPump();
        ShiftDTO shiftDTO = shift != null ? ShiftDTO.builder()
                .id(shift.getId())
                .name(shift.getName())
                .startTime(DateTimeHelper.formatTime(shift.getStartTime()))
                .endTime(DateTimeHelper.formatTime(shift.getEndTime())).build() : null;
        Tank tank = pump != null ? pump.getTank() : null;
        Fuel fuel = tank != null ? tank.getFuel() : null;
        FuelDTO fuelDTO = fuel != null ? FuelDTO.builder().id(fuel.getId()).name(fuel.getName()).build() : null;
        TankDTO tankDTO = tank != null ? TankDTO.builder().id(tank.getId()).name(tank.getName()).fuel(fuelDTO).build() : null;

        PumpDTO pumpDTO = pump != null ? PumpDTO.builder()
                .id(handOverShift.getId())
                .name(pump.getName())
                .tank(tankDTO)
                .build() : null;
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
