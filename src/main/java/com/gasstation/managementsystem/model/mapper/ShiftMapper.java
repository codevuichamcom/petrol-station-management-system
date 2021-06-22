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
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class ShiftMapper {
    private static UserDTO userToUserDTO(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName()).build();
    }

    public static ShiftDTO toShiftDTO(Shift shift) {
        if (shift == null) return null;
        UserDTO employee = userToUserDTO(shift.getEmployee());
        UserDTO owner = userToUserDTO(shift.getOwner());

        Station s = shift.getStation();
        StationDTO station = s != null ? StationDTO.builder()
                .id(s.getId())
                .name(s.getName()).build() : null;
        ShiftDTO shiftDTO = ShiftDTO.builder()
                .id(shift.getId())
                .startTime(DateTimeHelper.toUnixTime(shift.getStartTime()))
                .endTime(DateTimeHelper.toUnixTime(shift.getEndTime()))
                .employee(employee)
                .owner(owner)
                .station(station)
                .build();
        return shiftDTO;
    }

    public static Shift toShift(ShiftDTOCreate shiftDTOCreate) {
        if (shiftDTOCreate == null) return null;
        return Shift.builder()
                .startTime(DateTimeHelper.toDate(shiftDTOCreate.getStartTime()))
                .endTime(DateTimeHelper.toDate(shiftDTOCreate.getEndTime()))
                .build();
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
