package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
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
        Station station = tank != null ? tank.getStation() : null;
        StationDTO stationDTO = station != null ? StationDTO.builder().id(station.getId()).name(station.getName()).address(station.getAddress()).build() : null;
        TankDTO tankDTO = tank != null ? TankDTO.builder().id(tank.getId()).name(tank.getName()).fuel(fuelDTO).station(stationDTO).build() : null;
        User actor = handOverShift.getActor();
        UserDTO actorDTO = actor != null ? UserDTO.builder().id(actor.getId()).name(actor.getName()).build() : null;
        PumpDTO pumpDTO = pump != null ? PumpDTO.builder()
                .id(handOverShift.getId())
                .name(pump.getName())
                .tank(tankDTO)
                .build() : null;
        return HandOverShiftDTO.builder()
                .id(handOverShift.getId())
                .createdDate(handOverShift.getCreatedDate())
                .closedTime(handOverShift.getClosedTime())
                .shift(shiftDTO)
                .pump(pumpDTO)
                .actor(actorDTO)
                .build();
    }
}
