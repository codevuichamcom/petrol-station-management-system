package com.gasstation.managementsystem.model.dto.workSchedule;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkScheduleDTOCreate {
    @NotNull(message = "Employee id is mandatory")
    private Integer employeeId;
    @NotNull(message = "Shift id is mandatory")
    private Integer shiftId;
    @NotNull(message = "Start time id is mandatory")
    private Long startDate;
    @NotNull(message = "End Time id is mandatory")
    private Long endDate;
}
