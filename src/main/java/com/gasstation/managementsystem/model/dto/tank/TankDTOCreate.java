package com.gasstation.managementsystem.model.dto.tank;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TankDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Volume is mandatory")
    private Double volume;
    @NotNull(message = "Current price is mandatory")
    private Double currentPrice;
    @NotNull(message = "stationId is mandatory")
    private Integer stationId;
    @NotNull(message = "fuelId  is mandatory")
    private Integer fuelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankDTOCreate that = (TankDTOCreate) o;
        return Objects.equals(name, that.name) && Objects.equals(volume, that.volume) && Objects.equals(currentPrice, that.currentPrice) && Objects.equals(stationId, that.stationId) && Objects.equals(fuelId, that.fuelId);
    }
}
