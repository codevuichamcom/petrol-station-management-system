package com.gasstation.managementsystem.model.dto.dashboard;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TankStatisticDTOFilter {
    private Long startTime;
    private Long endTime;
    private Integer[] stationIds;
}
