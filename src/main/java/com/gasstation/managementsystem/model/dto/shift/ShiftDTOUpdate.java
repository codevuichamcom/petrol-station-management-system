package com.gasstation.managementsystem.model.dto.shift;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTOUpdate {
    private Long startTime;
    private Long endTime;
}
