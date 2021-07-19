package com.gasstation.managementsystem.model.dto.handOverShift;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HandOverShiftDTOUpdate {
    private Long createdDate;
    private Long closeShiftDate;
    private String note;
    private Integer shiftId;
    private Integer pumpId;

}
