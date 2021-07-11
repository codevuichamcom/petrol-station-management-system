package com.gasstation.managementsystem.model.dto.workSchedule;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkScheduleDTOUpdate {
    private Integer shiftId;
    private Date startDate;
    private Date endDate;
}
