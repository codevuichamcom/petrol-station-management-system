package com.gasstation.managementsystem.model.dto.workSchedule;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkScheduleDTOUpdate {
    private Integer shiftId;
    private Long startDate;
    private Long endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkScheduleDTOUpdate that = (WorkScheduleDTOUpdate) o;
        return Objects.equals(shiftId, that.shiftId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }
}
