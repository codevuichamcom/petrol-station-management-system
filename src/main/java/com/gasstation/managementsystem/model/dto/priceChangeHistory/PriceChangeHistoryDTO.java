package com.gasstation.managementsystem.model.dto.priceChangeHistory;

import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PriceChangeHistoryDTO {
    private int id;
    private String date;
    private Double price;
    private UserDTO editor;
    private StationDTO station;
    private TankDTO tank;
}
