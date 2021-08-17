package com.gasstation.managementsystem.model;

import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelStatistic that = (FuelStatistic) o;
        return Objects.equals(fuelId, that.fuelId) && Objects.equals(totalRevenue, that.totalRevenue) && Objects.equals(totalDebt, that.totalDebt) && Objects.equals(totalVolume, that.totalVolume) && Objects.equals(totalCash, that.totalCash);
    }
}
