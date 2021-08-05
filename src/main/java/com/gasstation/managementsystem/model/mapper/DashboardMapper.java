package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.model.FuelStatistic;
import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;

public class DashboardMapper {

    public static FuelStatisticDTO toDashboardDTO(FuelStatistic fuelStatistic) {
        if (fuelStatistic == null) return null;

        FuelDTO fuelDTO = fuelStatistic.getFuelId() != null ? FuelDTO.builder().id(fuelStatistic.getFuelId())
                .name(fuelStatistic.getFuelName()).build() : null;
        StationDTO stationDTO = fuelStatistic.getStationId() != null ? StationDTO.builder()
                .id(fuelStatistic.getStationId())
                .name(fuelStatistic.getStationName())
                .address(fuelStatistic.getStationAddress()).build() : null;
        return FuelStatisticDTO.builder()
                .fuel(fuelDTO)
                .station(stationDTO)
                .totalRevenue(fuelStatistic.getTotalRevenue())
                .totalDebt(fuelStatistic.getTotalDebt())
                .totalVolume(fuelStatistic.getTotalVolume())
                .totalPaid(fuelStatistic.getTotalPaid()).build();
    }
}
