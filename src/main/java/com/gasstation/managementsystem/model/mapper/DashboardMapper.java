package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.model.FuelStatistic;
import com.gasstation.managementsystem.model.TankStatistic;
import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTO;
import com.gasstation.managementsystem.model.dto.dashboard.TankStatisticDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;

public class DashboardMapper {

    public static FuelStatisticDTO toFuelStatisticDTO(FuelStatistic fuelStatistic) {
        if (fuelStatistic == null) return null;

        FuelDTO fuelDTO = fuelStatistic.getFuelId() != null ? FuelDTO.builder().id(fuelStatistic.getFuelId()).build() : null;
        return FuelStatisticDTO.builder()
                .fuel(fuelDTO)
                .totalVolume(fuelStatistic.getTotalVolume())
                .totalRevenue(Math.ceil(fuelStatistic.getTotalRevenue()))
                .totalDebt(Math.ceil(fuelStatistic.getTotalDebt()))
                .totalCash(Math.ceil(fuelStatistic.getTotalCash())).build();
    }

    public static TankStatisticDTO toTankStatisticDTO(TankStatistic tankStatistic) {
        if (tankStatistic == null) return null;
        TankDTO tankDTO = TankMapper.toTankDTO(tankStatistic.getTank());
        return TankStatisticDTO.builder()
                .tank(tankDTO)
                .totalImport(tankStatistic.getTotalImport())
                .totalExport(tankStatistic.getTotalExport()).build();
    }
}
