package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class WorkScheduleMapper {

    public static WorkScheduleDTO toWorkScheduleDTO(WorkSchedule workSchedule) {
        if (workSchedule == null) return null;
        Employee employee = workSchedule.getEmployee();
        Shift shift = workSchedule.getShift();
        EmployeeDTO employeeDTO = null;
        if (employee != null) {
            Station station = employee.getStation();
            User owner = station.getOwner();
            employeeDTO = EmployeeDTO.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .station(StationDTO.builder()
                            .id(station.getId())
                            .name(station.getName())
                            .address(station.getAddress())
                            .owner(UserDTO.builder()
                                    .id(owner.getId())
                                    .name(owner.getName()).build())
                            .build())
                    .build();
        }
        ShiftDTO shiftDTO = shift != null ? ShiftDTO.builder()
                .id(shift.getId())
                .name(shift.getName())
                .startTime(DateTimeHelper.formatTime(shift.getStartTime()))
                .endTime(DateTimeHelper.formatTime(shift.getEndTime())).build() : null;
        return WorkScheduleDTO.builder()
                .id(workSchedule.getId())
                .employee(employeeDTO)
                .shift(shiftDTO)
                .startDate(workSchedule.getStartDate())
                .endDate(workSchedule.getEndDate()).build();
    }

    public static WorkSchedule toWorkSchedule(WorkScheduleDTOCreate workScheduleDTOCreate) {
        if (workScheduleDTOCreate == null) return null;
        return WorkSchedule.builder()
                .startDate(workScheduleDTOCreate.getStartDate())
                .endDate(workScheduleDTOCreate.getEndDate()).build();
    }

    public static void copyNonNullToWorkSchedule(WorkSchedule workSchedule, WorkScheduleDTOUpdate workScheduleDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(workSchedule, workScheduleDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
