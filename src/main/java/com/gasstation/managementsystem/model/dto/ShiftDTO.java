package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Shift;
import lombok.*;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTO {
    private int id;

    private Time startTime;
    private Time endTime;

    public ShiftDTO(Shift shift) {
        this.id = shift.getId();
        this.startTime = shift.getStartTime();
        this.endTime = shift.getEndTime();
    }
}
