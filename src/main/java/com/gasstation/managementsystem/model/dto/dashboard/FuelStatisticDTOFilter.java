package com.gasstation.managementsystem.model.dto.dashboard;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FuelStatisticDTOFilter {
    private Long startTime;
    private Long endTime;
    private Integer[] stationIds;
}
