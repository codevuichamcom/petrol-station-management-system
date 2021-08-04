package com.gasstation.managementsystem.model.dto.dashboard;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardDTOFilter {
    private Long startTime;
    private Long endTime;
    private Integer stationId;
}
