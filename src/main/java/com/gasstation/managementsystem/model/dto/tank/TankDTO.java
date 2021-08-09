package com.gasstation.managementsystem.model.dto.tank;

import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

import java.util.Objects;

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
    private FuelDTO fuel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankDTO tankDTO = (TankDTO) o;
        return id == tankDTO.id && Objects.equals(name, tankDTO.name) && Objects.equals(volume, tankDTO.volume) && Objects.equals(remain, tankDTO.remain) && Objects.equals(currentPrice, tankDTO.currentPrice) && Objects.equals(station, tankDTO.station) && Objects.equals(fuel, tankDTO.fuel);
    }

}
