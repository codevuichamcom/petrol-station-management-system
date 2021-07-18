package com.gasstation.managementsystem.model.dto.priceChangeHistory;

import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PriceChangeHistoryDTO {
    private int id;
    private long time;
    private Double oldPrice;
    private Double newPrice;
    private UserDTO editor;
    private StationDTO station;
    private TankDTO tank;
}
