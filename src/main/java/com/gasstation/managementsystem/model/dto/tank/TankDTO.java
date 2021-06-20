package com.gasstation.managementsystem.model.dto.tank;

import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TankDTO {
    private int id;
    private double volume;
    private double remain;
    private StationDTO station;
}
