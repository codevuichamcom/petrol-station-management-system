package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class TankMapper {

    public static TankDTO toTankDTO(Tank tank) {
        if (tank == null) return null;
        TankDTO tankDTO = TankDTO.builder()
                .id(tank.getId())
                .name(tank.getName())
                .volume(tank.getVolume())
                .remain(tank.getRemain())
                .currentPrice(tank.getCurrentPrice()).build();

        Station station = tank.getStation();
        if (station != null) {
            tankDTO.setStation(StationDTO.builder()
                    .id(station.getId())
                    .name(station.getName())
                    .address(station.getAddress()).build());
        }
        Fuel fuel = tank.getFuel();
        if (fuel != null) {
            tankDTO.setFuel(FuelDTO.builder()
                    .id(fuel.getId())
                    .name(fuel.getName()).build());
        }
        return tankDTO;
    }

    public static Tank toTank(TankDTOCreate tankDTOCreate) {
        if (tankDTOCreate == null) return null;
        return Tank.builder()
                .name(tankDTOCreate.getName())
                .volume(tankDTOCreate.getVolume())
                .remain(tankDTOCreate.getRemain())
                .currentPrice(tankDTOCreate.getCurrentPrice())
                .build();
    }

    public static void copyNonNullToTank(Tank tank, TankDTOUpdate tankDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(tank, tankDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
