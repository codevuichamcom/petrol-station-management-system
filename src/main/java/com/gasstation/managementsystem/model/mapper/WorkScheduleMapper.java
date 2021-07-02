package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.WorkSchedule;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class WorkScheduleMapper {

    public static WorkScheduleDTO toWorkScheduleDTO(WorkSchedule workSchedule) {
        if (workSchedule == null) return null;
        return WorkScheduleDTO.builder()
                .employeeId(workSchedule.getEmployee().getId())
                .shiftId(workSchedule.getShift().getId())
                .startTime(DateTimeHelper.formatDate(workSchedule.getStartDate(), "yyyy-MM-dd"))
                .endTime(DateTimeHelper.formatDate(workSchedule.getEndDate(), "yyyy-MM-dd")).build();
    }

    public static WorkSchedule toWorkSchedule(WorkScheduleDTOCreate workScheduleDTOCreate) {
        if (workScheduleDTOCreate == null) return null;
        return WorkSchedule.builder()
                .startDate(workScheduleDTOCreate.getStartTime())
                .endDate(workScheduleDTOCreate.getEndTime()).build();
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
