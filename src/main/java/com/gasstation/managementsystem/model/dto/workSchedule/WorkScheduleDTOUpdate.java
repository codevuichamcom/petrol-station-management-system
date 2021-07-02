package com.gasstation.managementsystem.model.dto.workSchedule;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkScheduleDTOUpdate {
    private Integer employeeId;
    private Integer shiftId;
    private Date startTime;
    private Date endTime;
}
