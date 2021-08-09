package com.gasstation.managementsystem.model.dto.workSchedule;

import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkScheduleDTO {
    private int id;
    private EmployeeDTO employee;
    private ShiftDTO shift;
    private Long startDate;
    private Long endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkScheduleDTO that = (WorkScheduleDTO) o;
        return id == that.id && Objects.equals(employee, that.employee) && Objects.equals(shift, that.shift) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }
}
