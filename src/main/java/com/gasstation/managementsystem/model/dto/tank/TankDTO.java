package com.gasstation.managementsystem.model.dto.tank;

import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TankDTO {
    private int id;
    private String name;
    private Double volume;
    private Double remain;
    private Double currentPrice;
    private StationDTO station;
    private FuelDTO fuelDTO;
}
