package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;

public class ShiftMapper {
    public static ShiftDTO toShiftDTO(Shift shift) {
        if (shift == null) return null;
        Station station = shift.getStation();
        User owner = station != null ? station.getOwner() : null;
        UserDTO ownerDTO = owner != null ? UserDTO.builder().id(owner.getId()).name(owner.getName()).build() : null;
        StationDTO stationDTO = station != null ? StationDTO.builder()
                .id(station.getId()).name(station.getName())
                .address(station.getAddress())
                .owner(ownerDTO)
                .build() : null;
        return ShiftDTO.builder()
                .id(shift.getId())
                .name(shift.getName())
                .station(stationDTO)
                .startTime(DateTimeHelper.formatTime(shift.getStartTime()))
                .endTime(DateTimeHelper.formatTime(shift.getEndTime()))
                .build();
    }

    public static Shift toShift(ShiftDTOCreate shiftDTOCreate) {
        if (shiftDTOCreate == null) return null;
        return Shift.builder()
                .name(shiftDTOCreate.getName())
                .startTime(DateTimeHelper.toMilliSecond(shiftDTOCreate.getStartTime()))
                .endTime(DateTimeHelper.toMilliSecond(shiftDTOCreate.getEndTime())).build();
    }

    public static void copyNonNullToShift(Shift shift, ShiftDTOUpdate shiftDTOUpdate) {
        String name = shiftDTOUpdate.getName();
        String startTime = shiftDTOUpdate.getStartTime();
        String endTime = shiftDTOUpdate.getEndTime();
        if (name != null) {
            shift.setName(name);
        }
        if (startTime != null) {
            shift.setStartTime(DateTimeHelper.toMilliSecond(shiftDTOUpdate.getStartTime()));
        }
        if (endTime != null) {
            shift.setEndTime(DateTimeHelper.toMilliSecond(shiftDTOUpdate.getEndTime()));
        }
    }
}
