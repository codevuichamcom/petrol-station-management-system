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
    private double volume;
    @NotNull(message = "Remain is mandatory")
    private double remain;
    @NotNull(message = "Current price is mandatory")
    private double currentPrice;
    @NotNull(message = "stationId is mandatory")
    private int stationId;
    @NotNull(message = "fuelId  is mandatory")
    private int fuelId;
}
