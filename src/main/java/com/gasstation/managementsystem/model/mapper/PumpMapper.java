package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class PumpMapper {
    public static PumpDTO toPumpDTO(Pump pump) {
        if (pump == null) return null;
        Tank tank = pump.getTank();
        TankDTO tankDTO = null;
        if (tank != null) {
            tankDTO = TankDTO.builder()
                    .id(tank.getId())
                    .name(tank.getName())
                    .build();
            setStationAndFuel(tank, tankDTO);
        }

        return PumpDTO.builder()
                .id(pump.getId())
                .name(pump.getName())
                .tank(tankDTO)
                .note(pump.getNote()).build();
    }

    static void setStationAndFuel(Tank tank, TankDTO tankDTO) {
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
    }

    public static Pump toPump(PumpDTOCreate pumpDTOCreate) {
        if (pumpDTOCreate == null) return null;
        return Pump.builder()
                .name(pumpDTOCreate.getName())
                .note(pumpDTOCreate.getNote()).build();
    }

    public static void copyNonNullToFuel(Pump pump, PumpDTOUpdate pumpDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(pump, pumpDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
