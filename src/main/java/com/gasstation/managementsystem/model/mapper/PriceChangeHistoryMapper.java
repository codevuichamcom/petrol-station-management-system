package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.priceChangeHistory.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;

public class PriceChangeHistoryMapper {

    public static PriceChangeHistoryDTO toPriceChangeHistoryDTO(PriceChangeHistory priceChangeHistory) {
        if (priceChangeHistory == null) return null;
        User editor = priceChangeHistory.getEditor();
        UserDTO editorDTO = editor != null ? UserDTO.builder().id(editor.getId()).name(editor.getName()).build() : null;
        Station station = priceChangeHistory.getStation();
        StationDTO stationDTO = station != null ? StationDTO.builder().id(station.getId()).name(station.getName()).address(station.getAddress()).build() : null;
        Tank tank = priceChangeHistory.getTank();
        TankDTO tankDTO = tank != null ? TankDTO.builder().id(tank.getId()).name(tank.getName()).build() : null;
        return PriceChangeHistoryDTO.builder()
                .id(priceChangeHistory.getId())
                .time(priceChangeHistory.getTime())
                .oldPrice(priceChangeHistory.getOldPrice())
                .newPrice(priceChangeHistory.getNewPrice())
                .editor(editorDTO)
                .station(stationDTO)
                .tank(tankDTO)
                .build();
    }
}
