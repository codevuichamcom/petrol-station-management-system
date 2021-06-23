package com.gasstation.managementsystem.model.dto.tank;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "Remain is mandatory")
    private Double remain;
    @NotNull(message = "Current price is mandatory")
    private Double currentPrice;
    @NotNull(message = "stationId is mandatory")
    private Integer stationId;
    @NotNull(message = "fuelId  is mandatory")
    private Integer fuelId;
}
