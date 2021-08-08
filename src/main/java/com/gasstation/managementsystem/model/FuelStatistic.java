package com.gasstation.managementsystem.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FuelStatistic {
    private Integer fuelId;
    private String fuelName;
    private Integer stationId;
    private String stationName;
    private Double totalRevenue;
    private Double totalDebt;
    private Double totalVolume;
    private Double totalCash;
}
