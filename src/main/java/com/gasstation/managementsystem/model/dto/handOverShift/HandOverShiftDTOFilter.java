package com.gasstation.managementsystem.model.dto.handOverShift;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HandOverShiftDTOFilter {
    private Long createdDate;
    private Long closedTime;
    private Integer[] shiftIds;
    private Integer[] pumpIds;
    private Integer[] stationIds;
    private Integer actorName;

}
