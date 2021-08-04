package com.gasstation.managementsystem.model.dto.dashboard;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardDTOFilter {
    private Integer fuelId;
    private String fuelName;
    private Integer stationId;
    private String stationName;
    private String stationAddress;
    private Double totalRevenue;
    private Double totalDebt;
    private Double totalMoney;
}
