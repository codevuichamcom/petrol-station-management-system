package com.gasstation.managementsystem.model.dto.shift;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTOUpdate {
    private String name;
    private Date startTime;
    private Date endTime;
}
