package com.gasstation.managementsystem.model.dto.workSchedule;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkScheduleDTO {
    private int employeeId;
    private int shiftId;
    private String startTime;
    private String endTime;
}
