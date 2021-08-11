package com.gasstation.managementsystem.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FuelStatistic {
    private Integer fuelId;
    private Double totalRevenue;
    private Double totalDebt;
    private Double totalVolume;
    private Double totalCash;
}
