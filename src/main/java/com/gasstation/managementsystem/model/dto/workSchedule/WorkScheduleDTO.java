package com.gasstation.managementsystem.model.dto.workSchedule;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class WorkScheduleDTO {
    private int employeeId;
    private int shiftId;
    private String startTime;
    private String endTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkScheduleDTO that = (WorkScheduleDTO) o;
        return employeeId == that.employeeId && shiftId == that.shiftId && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

}
