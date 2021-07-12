package com.gasstation.managementsystem.model.dto.shift;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTOUpdate {
    private String name;
    private String startTime;
    private String endTime;
}
