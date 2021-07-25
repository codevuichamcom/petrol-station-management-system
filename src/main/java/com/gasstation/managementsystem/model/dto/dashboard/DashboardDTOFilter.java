package com.gasstation.managementsystem.model.dto.dashboard;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardDTOFilter {
    private int stationId;
    private long startTime;
    private long endTime;
}
