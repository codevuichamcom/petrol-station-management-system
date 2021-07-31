package com.gasstation.managementsystem.model.dto.workSchedule;

import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class WorkScheduleDTO {
    private int id;
    private EmployeeDTO employee;
    private ShiftDTO shift;
    private Long startDate;
    private Long endDate;


}
