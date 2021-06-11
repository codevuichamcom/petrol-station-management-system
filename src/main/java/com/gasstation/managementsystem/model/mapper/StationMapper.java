package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class StationMapper {

    public static StationDTO toStationDTO(Station station) {
        return StationDTO.builder()
                .id(station.getId())
                .name(station.getName())
                .address(station.getAddress())
                .owner(UserDTO.builder()
                        .id(station.getOwner().getId())
                        .name(station.getOwner().getName()).build())
                .build();
    }

    public static Station toStation(StationDTOCreate stationDTOCreate) {
        return Station.builder()
                .name(stationDTOCreate.getName())
                .address(stationDTOCreate.getAddress())
                .owner(User.builder().id(stationDTOCreate.getOwnerId()).build())
                .build();
    }

    public static void copyNonNullToStation(Station station, StationDTOUpdate stationDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(station, stationDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
