package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.model.FuelStatistic;
import com.gasstation.managementsystem.model.TankStatistic;
import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTO;
import com.gasstation.managementsystem.model.dto.dashboard.TankStatisticDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;

public class DashboardMapper {

    public static FuelStatisticDTO toFuelStatisticDTO(FuelStatistic fuelStatistic) {
        if (fuelStatistic == null) return null;

        FuelDTO fuelDTO = fuelStatistic.getFuelId() != null ? FuelDTO.builder().id(fuelStatistic.getFuelId())
                .name(fuelStatistic.getFuelName()).build() : null;
        StationDTO stationDTO = fuelStatistic.getStationId() != null ? StationDTO.builder()
                .id(fuelStatistic.getStationId())
                .name(fuelStatistic.getStationName()).build() : null;
        return FuelStatisticDTO.builder()
                .fuel(fuelDTO)
                .station(stationDTO)
                .totalVolume(fuelStatistic.getTotalVolume())
                .totalRevenue(Math.ceil(fuelStatistic.getTotalRevenue()))
                .totalDebt(Math.ceil(fuelStatistic.getTotalDebt()))
                .totalCash(Math.ceil(fuelStatistic.getTotalCash())).build();
    }

    public static TankStatisticDTO toTankStatisticDTO(TankStatistic tankStatistic) {
        if (tankStatistic == null) return null;
        FuelDTO fuelDTO = tankStatistic.getFuelId() != null ? FuelDTO.builder()
                .id(tankStatistic.getFuelId())
                .name(tankStatistic.getFuelName()).build() : null;
        StationDTO stationDTO = tankStatistic.getStationId() != null ? StationDTO.builder()
                .id(tankStatistic.getStationId())
                .name(tankStatistic.getStationName()).build() : null;
        TankDTO tankDTO = tankStatistic.getTankId() != null ? TankDTO.builder()
                .id(tankStatistic.getTankId())
                .name(tankStatistic.getTankName())
                .remain(tankStatistic.getTankRemain())
                .fuel(fuelDTO)
                .station(stationDTO).build() : null;
        return TankStatisticDTO.builder()
                .tank(tankDTO)
                .totalImport(tankStatistic.getTotalImport())
                .totalExport(tankStatistic.getTotalExport()).build();
    }
}
