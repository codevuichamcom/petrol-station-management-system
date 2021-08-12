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
        if (station == null) return null;
        User owner = station.getOwner();
        UserDTO userDTO = owner != null ? UserDTO.builder()
                .id(owner.getId())
                .name(owner.getName()).build() : null;

        return StationDTO.builder()
                .id(station.getId())
                .name(station.getName())
                .address(station.getAddress())
                .longitude(station.getLongitude())
                .latitude(station.getLatitude())
                .owner(userDTO)
                .build();
    }

    public static Station toStation(StationDTOCreate stationDTOCreate) {
        if (stationDTOCreate == null) return null;
        return Station.builder()
                .name(stationDTOCreate.getName())
                .address(stationDTOCreate.getAddress())
                .longitude(stationDTOCreate.getLongitude())
                .latitude(stationDTOCreate.getLatitude())
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
