package com.gasstation.managementsystem.model.dto.dashboard;

import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FuelStatisticDTO {
    private FuelDTO fuel;
    private StationDTO station;
    private Double totalRevenue;
    private Double totalDebt;
    private Double totalVolume;
    private Double totalCash;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelStatisticDTO that = (FuelStatisticDTO) o;
        return Objects.equals(fuel, that.fuel) && Objects.equals(station, that.station) && Objects.equals(totalRevenue, that.totalRevenue) && Objects.equals(totalDebt, that.totalDebt) && Objects.equals(totalVolume, that.totalVolume) && Objects.equals(totalCash, that.totalCash);
    }
}
