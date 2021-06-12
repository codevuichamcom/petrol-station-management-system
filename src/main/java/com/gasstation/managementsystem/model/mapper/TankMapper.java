package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.FuelCategory;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.FuelCategoryDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class TankMapper {

    public static TankDTO toTankDTO(Tank tank) {
        TankDTO tankDTO = TankDTO.builder()
                .id(tank.getId())
                .volume(tank.getVolume())
                .remain(tank.getRemain()).build();

        Station station = tank.getStation();
        if (station != null) {
            tankDTO.setStation(StationDTO.builder()
                    .id(station.getId())
                    .name(station.getName()).build());
        }

        FuelCategory fuelCategory = tank.getFuelCategory();
        if (tank.getFuelCategory() != null) {
            tankDTO.setCategory(FuelCategoryDTO.builder()
                    .id(fuelCategory.getId())
                    .name(fuelCategory.getName()).build());
        }
        return tankDTO;
    }

    public static Tank toTank(TankDTOCreate tankDTOCreate) {
        return Tank.builder()
                .volume(tankDTOCreate.getVolume())
                .remain(tankDTOCreate.getRemain())
                .station(Station.builder().id(tankDTOCreate.getStationId()).build())
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
